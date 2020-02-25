package engine.platform.openGL;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL45;

import engine.graphics.buffers.IndexBuffer;

public class OpenGLIndexBuffer extends IndexBuffer {

	private int ibo;
	private int count;

	public OpenGLIndexBuffer(int[] indices) {
		this.count = indices.length;
		ibo = GL45.glCreateBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);

	}

	public void bind() {
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
	}

	public void unbind() {
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public int getCount() {
		return count;
	}
}
