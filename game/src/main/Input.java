package main;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

public class Input implements GLFWKeyCallbackI, GLFWMouseButtonCallbackI {

	private static boolean[] keys = new boolean[65536];

	public static boolean[] mouseButtons = new boolean[16];

	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = action != GLFW.GLFW_RELEASE;
	}

	public void callback(long args) {

		GLFWKeyCallbackI.super.callback(args);
	}

	public void invoke(long window, int button, int action, int mods) {
		mouseButtons[button] = action != GLFW.GLFW_RELEASE;
	}

	public static boolean get(int index) {
		if (index == -1) {
			return false;
		}
		return keys[index];
	}

	@Override
	public String getSignature() {
		// TODO Auto-generated method stub
		return GLFWKeyCallbackI.super.getSignature();
	}

}
