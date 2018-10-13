package main;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.opengl.GL;

import graphics.Loader;
import graphics.MasterRenderer;
import stateManager.StateMachine;

public class Main {

	private Ventana ventana = new Ventana();
	private StateMachine stateMachine;

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
		// TextMaster.init();

		// FontType font = new FontType(Loader.loadTexture("res/Fonts/arial.png"), new
		// File("res/Fonts/arial.fnt"));
		// GUIText text = new GUIText("hola", 1, font, new Vector2f(0, 0), 0.5f, true);
		// TextMaster.loadText(text);

		// int[] data = new int[1200 * 1200];
		//
		// for (int i = 0; i < 1200 * 1200; i++) {
		// data[i] = 0xff0100ff;
		// }
		//
		// FileUtils.crearImagen(data, 1200, 1200, "res/mapa/mapa.png");
	}

	private void loop() {
		input();
		tick();
		render();
		reset();

	}

	private void input() {
		glfwPollEvents();
	}

	private void tick() {
		stateMachine.tick();
	}

	private void render() {
		stateMachine.render();

		// TextMaster.render();
		glfwSwapBuffers(this.ventana.window);
	}

	public void reset() {
		stateMachine.reset();
	}

	private void dispose() {
		// TextMaster.cleanUp();
		stateMachine.cleanUp();
		Loader.cleanUp();
		MasterRenderer.cleanUp();
		glfwTerminate();
	}
}
