package engine.old.controls.old;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

public class Input implements GLFWKeyCallbackI {

	private static boolean[] keys = new boolean[65536];

	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = action != GLFW.GLFW_RELEASE;
	}

	// public void callback(long args) {
	//
	// GLFWKeyCallbackI.super.callback(args);
	// }
	//
	// public String getSignature() {
	// return GLFWKeyCallbackI.super.getSignature();
	// }

	public static boolean get(int index) {
		if (index == -1) {
			return false;
		}
		return keys[index];
	}

}
