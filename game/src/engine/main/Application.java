package engine.main;

import org.lwjgl.glfw.GLFW;

import engine.events.Event;
import engine.events.EventDispatcher;
import engine.events.type.WindowCloseEvent;
import engine.graphics.Renderer;
import engine.layer.Layer;
import engine.layer.LayerStack;

public abstract class Application {

	public Window window;
	public LayerStack layerStack = new LayerStack();
	public float lastFrameTime = 0;
	public boolean running = true;
	private static Application instance;

	public Application() {
		instance = this;
		WindowProps windowProps = new WindowProps("game");
		windowProps.eventFn = (Event e) -> onEvent(e);
		window = Window.create(windowProps);

		Renderer.init();

		// m_ImGuiLayer = new ImGuiLayer();
		// PushOverlay(m_ImGuiLayer);
	}

	public void pushLayer(Layer layer) {
		layerStack.pushLayer(layer);
	}

	public void pushOverlay(Layer layer) {
		layerStack.pushOverlay(layer);
	}

	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.EventType.WINDOW_CLOSE, (Event e) -> (onWindowClose((WindowCloseEvent) e)));
		System.out.println(event.toString());
		for (int i = layerStack.end() - 1; i != 0; i--) {
			layerStack.getLayer(i).onEvent(event);
			if (event.handled) {
				break;
			}
		}
	}

	public void run() {
		while (running) {
			// porque usa glfw?
			float time = (float) GLFW.glfwGetTime();
			Timestep timestep = new Timestep(time - lastFrameTime);
			lastFrameTime = time;

			for (int i = 0; i < layerStack.end(); i++) {
				layerStack.getLayer(i).onUpdate(timestep);
			}
			/*
			 * m_ImGuiLayer->Begin(); for (Layer layer : layerStack) layer->OnImGuiRender();
			 * m_ImGuiLayer->End();
			 */
			window.onUpdate();
		}
	}

	public boolean onWindowClose(WindowCloseEvent e) {
		running = false;
		return true;
	}

	public abstract Application createApplication();

	public Window getWindow() {
		return window;
	}

	public static Application get() {
		return instance;
	}
}
