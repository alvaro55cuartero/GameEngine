package engine.graphics.old;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.main.Const;
import engine.models.old.PlaneTexturedModel;
import engine.models.old.RawModel;
import engine.models.old.TexturedModel;
import engine.objeto.entities.Entity;
import engine.platform.opengl.old.OpenGLShader;
import engine.shader.shaderGUI.ShaderGUI;

public class RendererGUI {

	private static ShaderGUI shader;
	private static List<TexturedModel> texturedModels = new ArrayList<TexturedModel>();
	private static Map<Integer, List<Entity>> entities = new HashMap<Integer, List<Entity>>();

	public RendererGUI() {
		shader = new ShaderGUI();
	}

	public void render() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		shader.start();
		shader.loadTextBoolean(false);
		for (Integer id : entities.keySet()) {
			TexturedModel model = getTexturedModel(id);
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(id);
			if (batch != null) {
				if (batch.get(0).getType().matches("GUI")) {
					for (Entity entity : batch) {
						prepareInstance(entity, shader);
						GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
					}
				} else if (batch.get(0).getType().matches("TextGUI")) {
					for (Entity entity : batch) {
						GL11.glEnable(GL11.GL_BLEND);
						GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
						prepareInstance(entity, shader);
						shader.loadTextColour(new Vector3f(0, 1, 0));
						GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
						GL11.glDisable(GL11.GL_BLEND);
					}
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

	private void prepareInstance(Entity entity, OpenGLShader shader) {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.identity()
				.translate(entity.getPosition().x * 2 / Const.width - 1, entity.getPosition().y * 2 / Const.height - 1,
						entity.getPosition().z)
				.rotateX((float) Math.toRadians(entity.getRx())).rotateY((float) Math.toRadians(entity.getRy()))
				.rotateZ((float) Math.toRadians(entity.getRz()))
				.scale(entity.getSx() * 2 / Const.width, entity.getSy() * 2 / Const.height, entity.getSz());
		((ShaderGUI) shader).loadTransformationMatrix(transformationMatrix);

	}

	public void processTexturedModels(String txt) {
		String[] frases = txt.split(";");
		for (int i = 0; i < frases.length; i++) {
			texturedModels.add(new PlaneTexturedModel(frases[i]));
		}
	}

	public void processEntityGUI(Entity entity) {
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

	public void reset() {
		entities.clear();
	}

	public TexturedModel getTexturedModel(int id) {
		TexturedModel texturedModel = texturedModels.get(id);
		if (texturedModel != null) {
			return texturedModel;
		}
		return getTexturedModel(0);
	}
}
