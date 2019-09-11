package engine.shader;

import engine.graphics.Renderer;
import engine.platform.opengl.OpenGLShader;

public abstract class Shader {

	public abstract void bind();

	public abstract void unbind();

	public static Shader create(String vertexSrc, String fragmentSrc) {
		switch (Renderer.getAPI()) {
		case NONE:
			// HZ_CORE_ASSERT(false, "RendererAPI::None is currently not supported!");
			return null;
		case OPENGL:
			return new OpenGLShader(vertexSrc, fragmentSrc);
		}

		return null;
	}
}
