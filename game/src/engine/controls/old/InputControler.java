package engine.controls.old;

import org.lwjgl.glfw.GLFW;

public class InputControler {

	public static boolean press(int i) {
		if (GLFW.glfwJoystickPresent(GLFW.GLFW_JOYSTICK_1)) {
			return GLFW.glfwGetJoystickButtons(GLFW.GLFW_JOYSTICK_1).get(i) == GLFW.GLFW_PRESS;
		}
		return false;
	}

	public static void all() {
		for (int i = 0; i < 16; i++) {
			boolean present = GLFW.glfwJoystickPresent(i);
			System.out.println("Controller " + i + ": " + present + GLFW.glfwGetJoystickName(i));
		}
	}

	public static float axis(int i) {
		if (GLFW.glfwJoystickPresent(GLFW.GLFW_JOYSTICK_1)) {
			return GLFW.glfwGetJoystickAxes(GLFW.GLFW_JOYSTICK_1).get(i);
		}
		return 0;
	}

	public static boolean axis(int i, float max, float min) {
		if (GLFW.glfwJoystickPresent(GLFW.GLFW_JOYSTICK_1)) {
			float value = GLFW.glfwGetJoystickAxes(GLFW.GLFW_JOYSTICK_1).get(i);
			return value <= max && value >= min;
		}
		return false;
	}

}
