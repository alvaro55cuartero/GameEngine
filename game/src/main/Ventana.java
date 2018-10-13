package main;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

import org.lwjgl.glfw.GLFWVidMode;

public class Ventana {

	public long window;

	public void createWindow() {
		if (!glfwInit()) {
			throw new IllegalStateException("GLFW no se pudo inicializar");
		}

		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
		window = glfwCreateWindow(Const.width, Const.height, Const.tiltle, 0, 0);

		if (window == 0) {
			throw new IllegalStateException("la ventana no se pudo inicializar");
		}

		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (videoMode.width() - Const.width) / 2, (videoMode.height() - Const.height) / 2);
		glfwShowWindow(window);
		glfwSetKeyCallback(window, new Input());
		glfwSetCursorPosCallback(window, new InputCursor());
		glfwMakeContextCurrent(window);

	}

}