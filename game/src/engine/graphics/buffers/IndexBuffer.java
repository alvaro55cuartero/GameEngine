package engine.graphics.buffers;

import engine.graphics.Renderer;
import engine.platform.openGL.OpenGLIndexBuffer;

public abstract class IndexBuffer {

	public abstract void bind();

	public abstract void unbind();

	public abstract int getCount();

	public static IndexBuffer create(int[] indices) {
		switch (Renderer.getApi()) {
		case NONE:
			// HZ_CORE_ASSERT(false, "RendererAPI::None is currently not supported!");
			return null;
		case OPENGL:
			return new OpenGLIndexBuffer(indices);
		default:
			break;
		}

		// HZ_CORE_ASSERT(false, "Unknown RendererAPI!");
		return null;

	}
}
