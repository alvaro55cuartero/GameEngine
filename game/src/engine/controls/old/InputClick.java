package engine.controls.old;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

public class InputClick implements GLFWMouseButtonCallbackI {

	public static boolean[] mouseButtons = new boolean[16];


	public void callback(long args) {

		GLFWMouseButtonCallbackI.super.callback(args);
	}

	public void invoke(long window, int button, int action, int mods) {
		mouseButtons[button] = action != GLFW.GLFW_RELEASE;
	}

	public String getSignature() {
		return GLFWMouseButtonCallbackI.super.getSignature();
	}	
}