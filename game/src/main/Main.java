package main;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import controls.Input;
import controls.InputCursor;
import debug.DebugConsole;
import fontRendering.TextMaster;
import graphics.Loader;
import graphics.MasterRenderer;
import stateManager.StateMachine;

public class Main {

	private Ventana ventana = new Ventana();
	private StateMachine stateMachine;
	private boolean bconsole = true;
	private DebugConsole debugConsole;

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	private void start() {
		init();
		while (!glfwWindowShouldClose(ventana.window)) {
			loop();
		}
		dispose();
	}

	private void init() {

		ventana.createWindow();
		GL.createCapabilities();
		MasterRenderer.init();

		stateMachine = new StateMachine();
		TextMaster.init();
	}

	private void loop() {
		if (debugConsole != null) {
			debugConsole.grafica.add();
		}
		input();
		tick();
		render();
		reset();

	}

	private void input() {
		glfwPollEvents();
		if (bconsole && Input.get(GLFW.GLFW_KEY_0)) {
			debugConsole = new DebugConsole();
			bconsole = false;
		}
		stateMachine.input();
	}

	private void tick() {
		stateMachine.tick();
	}

	private void render() {
		stateMachine.render();
		TextMaster.render();
		glfwSwapBuffers(this.ventana.window);
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
