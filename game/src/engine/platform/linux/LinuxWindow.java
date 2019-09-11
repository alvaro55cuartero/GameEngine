package engine.platform.linux;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import engine.events.Event;
import engine.events.type.KeyEvent;
import engine.events.type.KeyPressedEvent;
import engine.events.type.KeyReleasedEvent;
import engine.events.type.KeyTypedEvent;
import engine.events.type.MouseButtonEvent;
import engine.events.type.MouseButtonPressedEvent;
import engine.events.type.MouseButtonReleasedEvent;
import engine.events.type.MouseMovedEvent;
import engine.events.type.MouseScrolledEvent;
import engine.events.type.WindowCloseEvent;
import engine.events.type.WindowResizeEvent;
import engine.graphics.GraphicsContext;
import engine.main.Window;
import engine.main.WindowProps;
import engine.platform.opengl.OpenGLContext;

public class LinuxWindow extends Window {

	static boolean GLFWInitialized = false;
	public long window;
	private GraphicsContext context;
	WindowProps windowProps = new WindowProps("");

	public LinuxWindow(WindowProps windowProps) {
		super(windowProps);
		init(windowProps);
	}

	public void init(WindowProps windowProps) {
		this.windowProps.title = windowProps.title;
		this.windowProps.width = windowProps.width;
		this.windowProps.height = windowProps.height;

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
		window = GLFW.glfwCreateWindow(windowProps.width, windowProps.height, windowProps.title, 0, 0);

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
			windowProps.width = width;
			windowProps.height = height;

			WindowResizeEvent event = new WindowResizeEvent(width, height);
			windowProps.eventCallback(event);
		});

		GLFW.glfwSetWindowCloseCallback(window, (long window) -> {
			// WindowProps data = (WindowProps) glfwGetWindowUserPointer(window);
			WindowCloseEvent event = new WindowCloseEvent();
			windowProps.eventCallback(event);
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
			windowProps.eventCallback(event);
		});

		GLFW.glfwSetCharCallback(window, (long window, int keycode) -> {
			// WindowData& data = *(WindowData*)glfwGetWindowUserPointer(window);
			KeyTypedEvent event = new KeyTypedEvent(keycode);
			windowProps.eventCallback(event);
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
			windowProps.eventCallback(event);
		});

		GLFW.glfwSetScrollCallback(window, (long window, double xOffset, double yOffset) -> {
			// WindowData& data = *(WindowData*)glfwGetWindowUserPointer(window);

			MouseScrolledEvent event = new MouseScrolledEvent((float) xOffset, (float) yOffset);
			windowProps.eventCallback(event);
		});

		GLFW.glfwSetCursorPosCallback(window, (long window, double xPos, double yPos) -> {
			// WindowData& data = *(WindowData*)glfwGetWindowUserPointer(window);
			MouseMovedEvent event = new MouseMovedEvent((float) xPos, (float) yPos);
			windowProps.eventCallback(event);
		});
	}

	public void dispose() {
		GLFW.glfwDestroyWindow(window);
	}

	public long getNativeWindow() {
		return window;
	}

	public void onUpdate() {
		GLFW.glfwPollEvents();
		context.swapBuffers();
	}

	public void setEventCallback(Consumer<Event> consumer) {
		windowProps.eventFn = consumer;
	}

	public void GLFWErrorCallback(int error, String description) {
		// HZ_CORE_ERROR("GLFW Error ({0}): {1}", error, description);
	}

	public void setVSync(boolean enabled) {
		if (enabled) {
			GLFW.glfwSwapInterval(1);
		} else {
			GLFW.glfwSwapInterval(0);
			windowProps.vSync = enabled;
		}
	}

	public boolean isVSync() {
		return windowProps.vSync;

	}

	int getWidth() {
		return windowProps.width;
	}

	int getHeight() {
		return windowProps.height;
	}

}
