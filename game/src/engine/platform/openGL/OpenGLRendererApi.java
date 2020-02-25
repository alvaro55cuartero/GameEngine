package engine.platform.openGL;

import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

import engine.graphics.RendererApi;
import engine.graphics.VertexArray;

public class OpenGLRendererApi extends RendererApi {

	public void init() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	public void setClearColor(Vector4f color) {
		GL11.glClearColor(color.x, color.y, color.z, color.w);
	}

	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	public void drawIndexed(VertexArray vertexArray) {
		GL11.glDrawElements(GL11.GL_TRIANGLES, vertexArray.getIndexBuffer().getCount(), GL11.GL_UNSIGNED_INT, 0);
	}

}
