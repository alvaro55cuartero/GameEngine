package engine.old.platform.linux;

import java.nio.DoubleBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import engine.old.controls.Input;
import engine.old.main.Application;

public class LinuxInput extends Input {

	public LinuxInput() {
		instance = new LinuxInput();
	}

	protected boolean isKeyPressedImpl(int keycode) {
		long window = ((LinuxWindow) Application.get().getWindow()).getNativeWindow();
		int state = GLFW.glfwGetKey(window, keycode);
		return state == GLFW.GLFW_PRESS || state == GLFW.GLFW_REPEAT;
	}

	protected boolean isMouseButtonPressedImpl(int button) {
		long window = ((LinuxWindow) Application.get().getWindow()).getNativeWindow();
		int state = GLFW.glfwGetMouseButton(window, button);
		return state == GLFW.GLFW_PRESS;
	}

	public Vector2f getMousePositionImpl() {
		long window = ((LinuxWindow) Application.get().getWindow()).getNativeWindow();
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
