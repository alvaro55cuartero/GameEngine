package graphics;

import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import main.Const;
import models.RawModel;
import models.TexturedModel;
import objeto.Entity;
import shader.Shader;
import shader.shader3D.StaticShader;
import shader.shaderGUI.ShaderGUI;

public class Renderer {

	public static void render(Map<Integer, List<Entity>> entities3d, StaticShader shader) {
		for (Integer id : entities3d.keySet()) {
			TexturedModel model = MasterRenderer.getTexturedModel(id);
			prepareTexturedModel(model);
			List<Entity> batch = entities3d.get(id);
			if (batch != null) {
				for (Entity entity : batch) {
					prepareInstance(entity, shader);
					GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
				}
			}
			unbindTexturedModel(3);
		}
	}

	public static void render(List<Entity> entities, StaticShader shader) {
		for (Entity entity : entities) {
			TexturedModel model = MasterRenderer.getTexturedModel(entity.getTexturedModelId());
			prepareTexturedModel(model);
			prepareInstance(entity, shader);
			GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
			unbindTexturedModel(3);
		}
	}

	public static void render(List<Entity> entities, ShaderGUI shader) {
		for (Entity entity : entities) {
			TexturedModel model = MasterRenderer.getTexturedModel(entity.getTexturedModelId());
			RawModel rawModel = model.getRawModel();
			GL30.glBindVertexArray(rawModel.getId());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getModelTexture().getTextureID());
			prepareInstance(entity, shader);
			GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			GL30.glBindVertexArray(0);
		}
	}

	private static void prepareTexturedModel(TexturedModel model) {
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getModelTexture().getTextureID());
	}

	private static void unbindTexturedModel(int num) {
		for (int i = 0; i < num; i++) {
			GL20.glDisableVertexAttribArray(i);
		}
		GL30.glBindVertexArray(0);
	}

	private static void prepareInstance(Entity entity, StaticShader shader) {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.identity().translate(entity.getPosition()).rotateX((float) Math.toRadians(entity.getRx()))
				.rotateY((float) Math.toRadians(entity.getRy())).rotateZ((float) Math.toRadians(entity.getRz()))
				.scale(entity.getSx(), entity.getSy(), entity.getSz());
		shader.loadTransformationMatrix(transformationMatrix);
	}

	private static void prepareInstance(Entity entity, ShaderGUI shader) {
		Matrix4f transformationMatrix = new Matrix4f();
		transformationMatrix.identity()
				.translate(entity.getPosition().x * 2 / Const.width - 1, entity.getPosition().y * 2 / Const.height - 1,
						entity.getPosition().z)
				.rotateX((float) Math.toRadians(entity.getRx())).rotateY((float) Math.toRadians(entity.getRy()))
				.rotateZ((float) Math.toRadians(entity.getRz()))
				.scale(entity.getSx() * 2 / Const.width, entity.getSy() * 2 / Const.height, entity.getSz());
		shader.loadTransformationMatrix(transformationMatrix);
	}

}
