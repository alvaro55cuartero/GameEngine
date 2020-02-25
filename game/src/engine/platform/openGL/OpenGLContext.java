package engine.platform.openGL;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import engine.graphics.GraphicsContext;

public class OpenGLContext extends GraphicsContext {

	private long windowHandle;

	public OpenGLContext(long windowHandle) {
		GL.createCapabilities();
		this.windowHandle = windowHandle;
	}

	public void init() {
		GLFW.glfwMakeContextCurrent(windowHandle);
	}

	public void swapBuffers() {
		GLFW.glfwSwapBuffers(windowHandle);
	}

}
