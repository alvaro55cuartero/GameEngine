package engine.main;

import static org.lwjgl.glfw.GLFW.glfwTerminate;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import engine.controls.old.Input;
import engine.controls.old.InputCursor;
import engine.debug.DebugConsole;
import engine.events.EventDispatcher;
import engine.events.EventHandler;
import engine.fontRendering.TextMaster;
import engine.graphics.old.Loader;
import engine.graphics.old.MasterRenderer;
import engine.platform.mac.MacWindow;
import engine.stateManager.StateMachine;

public class Main {

	private Window window = new MacWindow(new WindowProps("hola"));
	private StateMachine stateMachine;
	private boolean bconsole = true;
	private DebugConsole debugConsole;

	public boolean running;

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	private void start() {
		init();
		while (running) {
			loop();
		}
		// dispose();
	}

	private void init() {
		GL.createCapabilities();
		MasterRenderer.init();

		stateMachine = new StateMachine();
		TextMaster.init();
	}

	private void loop() {
		if (debugConsole != null) {
			debugConsole.grafica.add();
		}
		window.onUpdate();
		input();
		tick();
		render();
		reset();

	}

	private void input() {

		if (bconsole && Input.get(GLFW.GLFW_KEY_0)) {
			debugConsole = new DebugConsole();
			bconsole = false;
		}
		stateMachine.input();
	}

	public void OnEvent(EventHandler eventHandler) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(eventHandler);
	}

	private void tick() {
		stateMachine.tick();
	}

	private void render() {
		stateMachine.render();
		TextMaster.render();

	}

	public void reset() {
		stateMachine.reset();
		InputCursor.reset();
	}

	private void dispose() {
		TextMaster.cleanUp();
		stateMachine.dispose();
		Loader.cleanUp();
		MasterRenderer.cleanUp();
		glfwTerminate();
		System.exit(0);
	}
}
