package graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import main.Camera;
import models.PlaneTexturedModel;
import models.RawModel;
import models.TexturedModel;
import objeto.Entity;
import objeto.light.AmbientLight;
import shader.Shader;
import shader.shader3D.Shader3D;

public class Renderer3D {

	private static Shader3D shader;
	private static Matrix4f projectionMatrix;
	private static List<TexturedModel> texturedModels = new ArrayList<TexturedModel>();
	private static Map<Integer, List<Entity>> entities = new HashMap<Integer, List<Entity>>();

	public Renderer3D() {
		shader = new Shader3D();
		projectionMatrix = MasterRenderer.projectionMatrixPerspective();
	}

	public void render(Camera camera, AmbientLight light) {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.loadLightPosition(light.getPosition());
		shader.loadViewMatrix(camera);
		render(entities, shader);
		shader.stop();
	}

	private void render(Map<Integer, List<Entity>> entities3d, Shader shader) {
		for (Integer id : entities3d.keySet()) {
			TexturedModel model = getTexturedModel(id);
			prepareTexturedModel(model);
			List<Entity> batch = entities3d.get(id);
			if (batch != null) {
				for (Entity entity : batch) {
					prepareInstance(entity, shader);
					GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
				}
			}
			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			GL20.glDisableVertexAttribArray(2);
			GL30.glBindVertexArray(0);
		}
	}

	private void prepareTexturedModel(TexturedModel model) {
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getModelTexture().getTextureID());
	}

	private void prepareInstance(Entity entity, Shader shader) {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.identity().translate(entity.getPosition()).rotateX((float) Math.toRadians(entity.getRx()))
				.rotateY((float) Math.toRadians(entity.getRy())).rotateZ((float) Math.toRadians(entity.getRz()))
				.scale(entity.getSx(), entity.getSy(), entity.getSz());
		((Shader3D) shader).loadTransformationMatrix(transformationMatrix);

	}

	public void processEntity(Entity entity) {
		if (entity != null) {
			Integer id = entity.getTexturedModelId();
			List<Entity> batch = entities.get(id);
			if (batch == null) {
				batch = new ArrayList<Entity>();
			}
			batch.add(entity);
			entities.put(id, batch);
		}
	}

	public void processTexturedModels(String txt) {
		String[] frases = txt.split(";");
		for (int i = 0; i < frases.length; i++) {
			texturedModels.add(new PlaneTexturedModel(frases[i]));
		}
	}

	public static void processTexturedModel(TexturedModel texturedModel) {
		texturedModels.add(texturedModel);
	}

	public void reset() {
		entities.clear();
	}

	public void resetTexturedModels() {
		texturedModels.clear();
	}

	public void clear() {
		reset();
		resetTexturedModels();
	}

	public TexturedModel getTexturedModel(int id) {
		TexturedModel texturedModel = texturedModels.get(id);
		if (texturedModel != null) {
			return texturedModel;
		}
		return getTexturedModel(0);
	}

	public int findId(String string) {
		for (int i = 0; i < texturedModels.size(); i++) {
			if (texturedModels.get(i).getName().matches(string)) {
				return i;
			}
		}
		return 0;
	}
}
