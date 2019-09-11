package engine.platform.opengl;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL45;

import engine.graphics.buffers.BufferLayout;
import engine.graphics.buffers.VertexBuffer;

public class OpenGLVertexBuffer extends VertexBuffer {
	private int rendererID;
	private BufferLayout layout;

	public OpenGLVertexBuffer(float[] vertices) {
		rendererID = GL45.glCreateBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, rendererID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
	}

	public void bind() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, rendererID);
	}

	public void unbind() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	public BufferLayout getLayout() {
		return layout;
	}

	public void setLayout(BufferLayout layout) {
		this.layout = layout;
	}

	public void dispose() {
		GL15.glDeleteBuffers(rendererID);
	}

}
