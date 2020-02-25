package engine.graphics;

import org.joml.Matrix4f;

import engine.graphics.shaders.Shader;
import engine.main.Api;
import engine.objects.camera.Camera;

public class Renderer {
	private static Scene scene = new Scene();

	public static void init() {
		RenderCommand.init();
	}

	public static void beginScene(Camera camera) {
		scene.viewProjectionMatrix = camera.getViewProjectionMatrix();
	}

	public static void endScene() {

	}

	public static void submit(Shader shader, VertexArray vao, Matrix4f transform) {
		shader.bind();
		shader.setMat4("u_ViewProjection", scene.viewProjectionMatrix);
		shader.setMat4("u_Transform", transform);
		vao.bind();
		RenderCommand.drawIndexed(vao);
	}

	public static void submit(Shader shader, VertexArray vertexArray) {
		submit(shader, vertexArray, new Matrix4f());
	}

	public static Api.GL getApi() {
		return RendererApi.getAPI();
	}
}
