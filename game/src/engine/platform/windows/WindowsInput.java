package engine.platform.windows;

import java.nio.DoubleBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import engine.controls.Input;
import engine.main.Application;

public class WindowsInput extends Input {

	protected boolean isKeyPressedImpl(int keycode) {
		long window = ((WindowsWindow) Application.instance.getWindow()).getNativeWindow();
		int state = GLFW.glfwGetKey(window, keycode);
		return state == GLFW.GLFW_PRESS || state == GLFW.GLFW_REPEAT;
	}

	protected boolean isMouseButtonPressedImpl(int button) {
		long window = ((WindowsWindow) Application.instance.getWindow()).getNativeWindow();
		int state = GLFW.glfwGetMouseButton(window, button);
		return state == GLFW.GLFW_PRESS;
	}

	public Vector2f getMousePositionImpl() {
		long window = ((WindowsWindow) Application.instance.getWindow()).getNativeWindow();
		DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(window, xBuffer, yBuffer);
		double x = xBuffer.get(0);
		double y = yBuffer.get(0);

		return new Vector2f((float) x, (float) y);
	}

	public float getMouseXImpl() {
		return getMousePositionImpl().x;
	}

	public float getMouseYImpl() {
		return getMousePositionImpl().y;
	}
}
