package engine.platform.windows;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import engine.graphics.GraphicsContext;
import engine.main.events.Event;
import engine.main.events.type.KeyEvent;
import engine.main.events.type.KeyPressedEvent;
import engine.main.events.type.KeyReleasedEvent;
import engine.main.events.type.KeyTypedEvent;
import engine.main.events.type.MouseButtonEvent;
import engine.main.events.type.MouseButtonPressedEvent;
import engine.main.events.type.MouseButtonReleasedEvent;
import engine.main.events.type.MouseMovedEvent;
import engine.main.events.type.MouseScrolledEvent;
import engine.main.events.type.WindowCloseEvent;
import engine.main.events.type.WindowResizeEvent;
import engine.main.window.Window;
import engine.platform.openGL.OpenGLContext;

public class WindowsWindow extends Window {

	static boolean GLFWInitialized = false;
	public long window;
	private GraphicsContext context;

	public WindowsWindow(String title, int width, int height, boolean vsync) {
		super(title, width, height, vsync);
		init();
	}

	public void init() {
		if (!GLFWInitialized) {
			// TODO: glfwTerminate on system shutdown
			if (!GLFW.glfwInit()) {
				System.out.println("ERROR");
			}
			// HZ_CORE_ASSERT(success, "Could not intialize GLFW!");
			// nose
			// GLFW.glfwSetErrorCallback(new GLFWErrorCallback());
			GLFWInitialized = true;
		}

		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
		glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
		glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		window = GLFW.glfwCreateWindow(this.getWidth(), this.getHeight(), this.getTitle(), 0, 0);

		if (window == 0) {
			System.out.println("Error");
		}

		glfwMakeContextCurrent(window);
		context = new OpenGLContext(window);
		context.init();

		// GLFW.glfwSetWindowUserPointer(window, windowProps);

		setVSync(true);

		// Set GLFW callbacks
		GLFW.glfwSetWindowSizeCallback(window, (long window, int width, int height) -> {
			// WindowProps data = (WindowProps) glfwGetWindowUserPointer(window);
			this.setWidth(width);
			this.setHeight(height);

			WindowResizeEvent event = new WindowResizeEvent(width, height);
			this.eventCallback(event);
		});

		GLFW.glfwSetWindowCloseCallback(window, (long window) -> {
			// WindowProps data = (WindowProps) glfwGetWindowUserPointer(window);
			WindowCloseEvent event = new WindowCloseEvent();
			this.eventCallback(event);
		});

		GLFW.glfwSetKeyCallback(window, (long window, int key, int scancode, int action, int mods) -> {
			// WindowProps data = GLFW.glfwGetWindowUserPointer(window);
			KeyEvent event = null;
			switch (action) {
			case GLFW.GLFW_PRESS:
				event = new KeyPressedEvent(key, 0);
				break;
			case GLFW.GLFW_RELEASE:
				event = new KeyReleasedEvent(key);
				break;
			case GLFW.GLFW_REPEAT:
				event = new KeyPressedEvent(key, 1);
				break;
			}
			this.eventCallback(event);
		});

		GLFW.glfwSetCharCallback(window, (long window, int keycode) -> {
			// WindowData& data = *(WindowData*)glfwGetWindowUserPointer(window);
			KeyTypedEvent event = new KeyTypedEvent(keycode);
			this.eventCallback(event);
		});

		GLFW.glfwSetMouseButtonCallback(window, (long window, int button, int action, int mods) -> {
			// WindowData& data = *(WindowData*)glfwGetWindowUserPointer(window);
			MouseButtonEvent event = null;
			switch (action) {
			case GLFW.GLFW_PRESS:
				event = new MouseButtonPressedEvent(button);
				break;
			case GLFW.GLFW_RELEASE:
				event = new MouseButtonReleasedEvent(button);
				break;
			}
			this.eventCallback(event);
		});

		GLFW.glfwSetScrollCallback(window, (long window, double xOffset, double yOffset) -> {
			// WindowData& data = *(WindowData*)glfwGetWindowUserPointer(window);

			MouseScrolledEvent event = new MouseScrolledEvent((float) xOffset, (float) yOffset);
			this.eventCallback(event);
		});

		GLFW.glfwSetCursorPosCallback(window, (long window, double xPos, double yPos) -> {
			// WindowData& data = *(WindowData*)glfwGetWindowUserPointer(window);
			MouseMovedEvent event = new MouseMovedEvent((float) xPos, (float) yPos);
			this.eventCallback(event);
		});

	}

	public void onUpdate() {
		GLFW.glfwPollEvents();
		context.swapBuffers();
	}

	public void setEventCallback(Consumer<Event> consumer) {
		this.setEventFn(consumer);
	}

	public void setVSync(boolean enabled) {
		this.vSync = enabled;
		if (enabled) {
			GLFW.glfwSwapInterval(1);
		} else {
			GLFW.glfwSwapInterval(0);
		}
	}

	public long getNativeWindow() {
		return window;
	}

	public void GLFWErrorCallback(int error, String description) {
		// HZ_CORE_ERROR("GLFW Error ({0}): {1}", error, description);
	}

	public void dispose() {
		GLFW.glfwDestroyWindow(window);
	}

}
