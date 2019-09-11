package engine.graphics;

import org.joml.Vector4f;

import engine.platform.opengl.OpenGLRendererAPI;

public class RenderCommand {

	private static RendererAPI rendererAPI = new OpenGLRendererAPI();

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
