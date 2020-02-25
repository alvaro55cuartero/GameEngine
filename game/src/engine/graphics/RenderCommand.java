package engine.graphics;

import org.joml.Vector4f;

import engine.platform.openGL.OpenGLRendererApi;

public class RenderCommand {

	private static RendererApi rendererAPI = new OpenGLRendererApi();

	public static void init() {
		rendererAPI.init();
	}

	public static void setClearColor(Vector4f color) {
		rendererAPI.setClearColor(color);
	}

	public static void clear() {
		rendererAPI.clear();
	}

	public static void drawIndexed(VertexArray vertexArray) {
		rendererAPI.drawIndexed(vertexArray);
	}

}
