package engine.graphics;

import org.joml.Matrix4f;

import engine.objeto.OrthographicCamera;
import engine.platform.opengl.OpenGLShader;

public class Renderer {

	private static SceneData sceneData = new SceneData();

	public static void init() {
		RenderCommand.init();
	}

	public static void beginScene(OrthographicCamera camera) {
		sceneData.viewProjectionMatrix = camera.getViewProjectionMatrix();
	}

	public static void endScene() {
	}

	public static void submit(OpenGLShader shader, VertexArray vertexArray, Matrix4f transform) {
		shader.bind();
		shader.uploadUniformMat4("u_ViewProjection", sceneData.viewProjectionMatrix);
		shader.uploadUniformMat4("u_Transform", transform);

		vertexArray.bind();
		RenderCommand.drawIndexed(vertexArray);
	}

	public static void submit(OpenGLShader shader, VertexArray vertexArray) {
		submit(shader, vertexArray, new Matrix4f());
	}

	public static RendererAPI.APIType getAPI() {
		return RendererAPI.getAPI();
	}

}
