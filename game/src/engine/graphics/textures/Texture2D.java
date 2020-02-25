package engine.graphics.textures;

import engine.graphics.Renderer;
import engine.platform.openGL.OpenGLTexture2D;

public abstract class Texture2D extends Texture {

	public static Texture2D create(String path) {
		switch (Renderer.getApi()) {
		case NONE:
			// HZ_CORE_ASSERT(false, "RendererAPI::None is currently not supported!");
			return null;
		case OPENGL:
			return new OpenGLTexture2D(path);
		}

		// HZ_CORE_ASSERT(false, "Unknown RendererAPI!");
		return null;
	}
}
