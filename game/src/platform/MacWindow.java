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
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowCloseCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

import events.KeyEvent;
import events.KeyPressedEvent;
import events.KeyReleasedEvent;
import events.MouseButtonEvent;
import events.MouseButtonPressedEvent;
import events.MouseButtonReleasedEvent;
import events.MouseMovedEvent;
import events.MouseScrolledEvent;
import events.WindowCloseEvent;
import events.WindowResizeEvent;
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
		glfwMakeContextCurrent(window);

		// GLFW Callbacks.

		glfwSetWindowSizeCallback(window, (window, width, height) -> {
			WindowResizeEvent event = new WindowResizeEvent(width, width);
		});

		glfwSetWindowCloseCallback(window, (window) -> {
			WindowCloseEvent event = new WindowCloseEvent();
		});

		// glfwSetKeyCallback(window, new Input());

		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			KeyEvent event;
			switch (action) {
			case GLFW.GLFW_RELEASE:
				event = new KeyPressedEvent(key, 0);
				break;
			case GLFW.GLFW_PRESS:
				event = new KeyReleasedEvent(key);
				break;
			case GLFW.GLFW_REPEAT:
				event = new KeyPressedEvent(key, 1);
				break;
			}
		});

		// glfwSetMouseButtonCallback(window, new InputClick());

		glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
			MouseButtonEvent event;
			switch (action) {
			case GLFW.GLFW_PRESS:
				event = new MouseButtonPressedEvent(button);
				break;
			case GLFW.GLFW_RELEASE:
				event = new MouseButtonReleasedEvent(button);
				break;
			case GLFW.GLFW_REPEAT:
				break;
			}
		});

		glfwSetScrollCallback(window, (window, xOff, yOff) -> {
			MouseScrolledEvent event = new MouseScrolledEvent((float) xOff, (float) yOff);
		});

		// glfwSetCursorPosCallback(window, new InputCursor());

		glfwSetCursorPosCallback(window, (window, xPos, yPos) -> {
			MouseMovedEvent event = new MouseMovedEvent((float) xPos, (float) yPos);
		});
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
		getWindowProps().vSync = enabled;
	}

	public boolean isVsync() {
		return getWindowProps().vSync;
	}

}
