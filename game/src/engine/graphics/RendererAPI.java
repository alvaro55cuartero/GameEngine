package engine.graphics;

import org.joml.Vector4f;

public abstract class RendererAPI {
	private static APIType API;

	public enum APIType {
		NONE, OPENGL
	};

	public RendererAPI() {
		API = RendererAPI.APIType.OPENGL;
	}

	public abstract void init();

	public abstract void setClearColor(Vector4f color);

	public abstract void clear();

	public abstract void drawIndexed(VertexArray vertexArray);

	public static APIType getAPI() {
		return API;
	}
}
