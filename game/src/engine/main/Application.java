package engine.main;

import org.lwjgl.glfw.GLFW;

import engine.main.events.Event;
import engine.main.events.EventDispatcher;
import engine.main.events.EventHandler;
import engine.main.events.type.WindowCloseEvent;
import engine.main.layer.Layer;
import engine.main.layer.LayerStack;
import engine.main.window.Window;

public class Application {

	public static Application instance;
	public Window window;
	public LayerStack layerStack;
	public boolean running = true;
	public float lastFrameTime = 0;

	public Application() {
		instance = this;
		window = Window.create("", 600, 400, true);
		layerStack = new LayerStack();

		window.setEventCallback((Event e) -> onEvent(e));
		// Renderer.init();
		// m_ImGuiLayer = new ImGuiLayer();
		// PushOverlay(m_ImGuiLayer);
	}

	public void run() {
		while (running) {
			// porque usa glfw?
			float time = (float) GLFW.glfwGetTime();
			Timestep timestep = new Timestep(time - lastFrameTime);
			lastFrameTime = time;

			for (int i = 0; i < layerStack.size(); i++) {
				layerStack.getLayer(i).onUpdate(timestep);
			}
			/*
			 * m_ImGuiLayer->Begin(); for (Layer layer : layerStack) layer->OnImGuiRender();
			 * m_ImGuiLayer->End();
			 */
			window.onUpdate();
		}
	}

	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.EventType.WINDOW_CLOSE, new EventHandler() {
			public boolean onEvent(Event event) {
				return onWindowClose((WindowCloseEvent) event);
			}
		});
		for (int i = layerStack.size() - 1; i >= 0; i--) {
			layerStack.getLayer(i).onEvent(event);
			if (event.handled) {
				break;
			}
		}
	}

	public void pushLayer(Layer layer) {
		layerStack.pushLayer(layer);
	}

	public void pushOverlay(Layer layer) {
		layerStack.pushOverlay(layer);
	}

	public void popLayer() {
		layerStack.popLayer();
	}

	public void popOverlay() {
		layerStack.popOverlay();
	}

	public boolean onWindowClose(WindowCloseEvent e) {
		running = false;
		return true;
	}

	public Window getWindow() {
		return window;
	}

}
