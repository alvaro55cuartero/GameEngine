package engine.graphics;

import java.util.ArrayList;

import engine.graphics.buffers.IndexBuffer;
import engine.graphics.buffers.VertexBuffer;
import engine.platform.openGL.OpenGLVertexArray;

public abstract class VertexArray {

	public abstract void bind();

	public abstract void unbind();

	public abstract void addVertexBuffer(VertexBuffer vertexBuffer);

	public abstract void setIndexBuffer(IndexBuffer indexBuffer);

	public abstract ArrayList<VertexBuffer> getVertexBuffers();

	public abstract IndexBuffer getIndexBuffer();

	public static VertexArray create() {
		switch (Renderer.getApi()) {
		case NONE:
			// HZ_CORE_ASSERT(false, "RendererAPI::None is currently not supported!");
			System.out.println("VertexArray class");
			return null;
		case OPENGL:
			return new OpenGLVertexArray();
		default:
			break;
		}

		return null;
	}

}
