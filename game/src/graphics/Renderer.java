package graphics;

import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import models.RawModel;
import models.TexturedModel;
import objeto.Entity;
import shader.StaticShader;

public class Renderer {

	private StaticShader shader;

	public Renderer(StaticShader shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	public void render(Map<Integer, List<Entity>> entities3d) {
		for (Integer id : entities3d.keySet()) {
			TexturedModel model = MasterRenderer.getTexturedModel(id);
			prepareTexturedModel(model);
			List<Entity> batch = entities3d.get(id);
			if (batch != null) {
				for (Entity entity : batch) {
					prepareInstance(entity);
					GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
				}
			}
			unbindTexturedModel();
		}
	}

	public void render(List<Entity> entities) {
		for (Entity entity : entities) {
			TexturedModel model = MasterRenderer.getTexturedModel(entity.getTexturedModelId());
			prepareTexturedModel(model);
			prepareInstance(entity);
			GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
			unbindTexturedModel();
		}
	}

	private void prepareTexturedModel(TexturedModel model) {
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getModelTexture().getTextureID());
	}

	private void unbindTexturedModel() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}

	private void prepareInstance(Entity entity) {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.identity().translate(entity.getPosition()).rotateX((float) Math.toRadians(entity.getRx()))
				.rotateY((float) Math.toRadians(entity.getRy())).rotateZ((float) Math.toRadians(entity.getRz()))
				.scale(entity.getSx(), entity.getSy(), entity.getSz());
		shader.loadTransformationMatrix(transformationMatrix);
	}

}
