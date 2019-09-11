package engine.graphics.buffers;

import engine.graphics.Renderer;
import engine.platform.opengl.OpenGLVertexBuffer;

public abstract class VertexBuffer {

	public abstract void bind();

	public abstract void unbind();

	public abstract BufferLayout getLayout();

	public abstract void setLayout(BufferLayout layout);

	public static VertexBuffer create(float[] vertices) {
		switch (Renderer.getAPI()) {
		case NONE:
			// HZ_CORE_ASSERT(false, "RendererAPI::None is currently not supported!");
			return null;
		case OPENGL:
			return new OpenGLVertexBuffer(vertices);
		}

		// HZ_CORE_ASSERT(false, "Unknown RendererAPI!");
		return null;
	}

}
