package engine.graphics;

import org.joml.Vector4f;

import engine.main.Api;

public abstract class RendererApi {

	private static Api.GL API;

	public RendererApi() {
		API = Api.instance.gl;
	}

	public abstract void init();

	public abstract void setClearColor(Vector4f color);

	public abstract void clear();

	public abstract void drawIndexed(VertexArray vertexArray);

	public static Api.GL getAPI() {
		return API;
	}

}
