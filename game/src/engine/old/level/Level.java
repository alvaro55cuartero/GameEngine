package engine.old.level;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import engine.old.colision.MasterColisions;
import engine.old.controls.old.Input;
import engine.old.objeto.camera.Camera;
import engine.old.objeto.jugador.Jugador;

public class Level {

	private MasterChunk masterChunk = new MasterChunk();
	private MasterColisions masterColisions = new MasterColisions();
	private Jugador jugador = new Jugador(8);
	private boolean freeMode;

	public Level() {

	}

	public void input() {
		jugador.input();
	}

	public void tick(Camera camera) {
		masterColisions.testJugado(jugador, masterChunk);
		jugador.tick();

		if (Input.get(GLFW.GLFW_KEY_P)) {
			freeMode = true;
		}
		if (Input.get(GLFW.GLFW_KEY_O)) {
			camera.setPosition(new Vector3f(0, 0, 20));
			freeMode = false;
		}

		if (!freeMode) {
			Camera.moveJuego(jugador, camera);
		} else {
			Camera.moveFree(camera);
		}

		masterChunk.setActive5(Math.floorDiv((int) jugador.getPosition().x, 16),
				Math.floorDiv((int) jugador.getPosition().y, 16), Math.floorDiv((int) jugador.getPosition().z, 16));
	}

	public void render() {
		// masterColisions.render();
		masterChunk.render();
		masterChunk.renderDebug();
		jugador.render();
	}

	public void reset() {
		jugador.reset();
	}

	public void dispose() {

	}

}
