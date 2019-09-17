package engine.graphics.old;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.models.old.RawModel;
import engine.models.old.RawModelCuboLine;
import engine.objeto.Camera;
import engine.objeto.entities.Entity;
import engine.platform.opengl.old.OpenGLShader;
import engine.shader.shaderDebug.ShaderDebug;

public class RendererDebug {

	private static ShaderDebug shader;
	private static Matrix4f projectionMatrix;

	private static List<RawModel> rawModels = new ArrayList<RawModel>();
	private static Map<Integer, List<Entity>> entities = new HashMap<Integer, List<Entity>>();

	public RendererDebug() {
		shader = new ShaderDebug();
		projectionMatrix = MasterRenderer.projectionMatrixPerspective();
		this.processRawModel(new RawModelCuboLine());
	}

	public void render(Camera camera) {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.loadViewMatrix(camera);
		render(entities, shader);
		shader.stop();
	}

	private void render(Map<Integer, List<Entity>> entities3d, OpenGLShader shader) {
		for (Integer id : entities3d.keySet()) {
			RawModel model = getRawModel(id);
			prepareRawModel(model);
			List<Entity> batch = entities3d.get(id);
			if (batch != null) {
				for (Entity entity : batch) {
					prepareInstance(entity, shader);
					GL11.glDrawElements(GL11.GL_LINE_STRIP, model.getCount(), GL11.GL_UNSIGNED_INT, 0);
				}
			}
			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			GL20.glDisableVertexAttribArray(2);
			GL30.glBindVertexArray(0);
		}
	}

	private void prepareRawModel(RawModel model) {
		GL30.glBindVertexArray(model.getId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
	}

	private void prepareInstance(Entity entity, OpenGLShader shader) {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.identity().translate(entity.getPosition()).rotateX((float) Math.toRadians(entity.getRx()))
				.rotateY((float) Math.toRadians(entity.getRy())).rotateZ((float) Math.toRadians(entity.getRz()))
				.scale(entity.getSx(), entity.getSy(), entity.getSz());
		((ShaderDebug) shader).loadTransformationMatrix(transformationMatrix);

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

	public void processRawModel(RawModel rawModel) {
		rawModels.add(rawModel);
	}

	public void reset() {
		entities.clear();
	}

	public void resetRawModels() {
		rawModels.clear();
	}

	public void clear() {
		reset();
		resetRawModels();
	}

	public RawModel getRawModel(int id) {
		RawModel rawModel = rawModels.get(id);
		if (rawModel != null) {
			return rawModel;
		}
		return getRawModel(0);
	}

}
