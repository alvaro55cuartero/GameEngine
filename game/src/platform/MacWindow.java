package platform;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

import controls.Input;
import controls.InputClick;
import controls.InputCursor;
import main.Const;
import main.Window;
import main.WindowProps;

public class MacWindow extends Window {

	public long window;

	public MacWindow(WindowProps windowProps) {
		super(windowProps);
		init();
	}

	public void init() {
		if (!glfwInit()) {
			throw new IllegalStateException("GLFW no se pudo inicializar");
		}

		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
		glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
		glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		window = glfwCreateWindow(Const.width, Const.height, Const.tiltle, 0, 0);

		if (window == 0) {
			throw new IllegalStateException("la ventana no se pudo inicializar");
		}

		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (videoMode.width() - this.getWindowProps().width) / 2,
				(videoMode.height() - this.getWindowProps().height) / 2);
		glfwShowWindow(window);
		glfwSetKeyCallback(window, new Input());
		glfwSetCursorPosCallback(window, new InputCursor());
		glfwSetMouseButtonCallback(window, new InputClick());

		glfwMakeContextCurrent(window);
		// disableCursor();
	}

	public void shutdown() {
		glfwDestroyWindow(window);
	}

	public Window create(WindowProps windowProps) {
		return new MacWindow(windowProps);
	}

	public void disableCursor() {
		glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
	}

	public void enableCursor() {
		glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
	}

	public void onUpdate() {
		glfwPollEvents();
		glfwSwapBuffers(window);
	}

	public void setEventCallback() {

	}

	public void setVsync(boolean enabled) {
		if (enabled) {
			glfwSwapInterval(1);
		} else {
			glfwSwapInterval(0);
		}
	}

	public boolean isVsync() {

		return false;
	}

}
