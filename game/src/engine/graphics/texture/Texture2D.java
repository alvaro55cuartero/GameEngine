package engine.graphics.texture;

import engine.graphics.Renderer;
import engine.platform.opengl.OpenGLTexture2D;

public class Texture2D extends Texture {

	public void dispose() {

	}

	public int getWidth() {
		return 0;
	}

	public int getHeight() {
		return 0;
	}

	public static Texture2D create(String path) {
		switch (Renderer.getAPI()) {
		case NONE:
			// HZ_CORE_ASSERT(false, "RendererAPI::None is currently not supported!");
			return null;
		case OPENGL:
			return new OpenGLTexture2D(path);
		}

		// HZ_CORE_ASSERT(false, "Unknown RendererAPI!");
		return null;
	}

	@Override
	public void bind(int slot) {
		// TODO Auto-generated method stub

	}

}
