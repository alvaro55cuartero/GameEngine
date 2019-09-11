package engine.platform.opengl;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL45;

import engine.graphics.buffers.IndexBuffer;

public class OpenGLIndexBuffer extends IndexBuffer {

	private int rendererID;
	private int count;

	public OpenGLIndexBuffer(int[] indices) {
		this.count = indices.length;
		rendererID = GL45.glCreateBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, rendererID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
	}

	public void bind() {
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, rendererID);
	}

	public void unbind() {
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public void dispose() {
		GL15.glDeleteBuffers(rendererID);
	}

	public int getCount() {
		return count;
	}

}
