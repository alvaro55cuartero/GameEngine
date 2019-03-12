package stateManager;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import colision.MasterColisions;
import controls.Input;
import level.Level;
import level.MasterChunk;
import objeto.Jugador;
import stateManager.StateMachine.state;

public class StateJuego extends State {

	Level level;
	Jugador jugador;
	MasterColisions masterColisions;
	boolean freeMode = false;
	MasterChunk masterChunk = new MasterChunk();

	public StateJuego() {
		masterColisions = new MasterColisions();
		// level = new Level(masterColisions, "res/mapa/mapa1.png");
		jugador = new Jugador(8);

	}

	public void tick(StateMachine stateMachine) {
		jugador.tick();

		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			stateMachine.setCurrentState(state.MENU_PAUSA);
		}
		if (Input.get(GLFW.GLFW_KEY_P)) {
			freeMode = true;
		}
		if (Input.get(GLFW.GLFW_KEY_O)) {
			camera.setDir(new Vector3f(0, 0, 0));
			camera.setPosition(new Vector3f(0, 0, 20));
			freeMode = false;
		}

		if (!freeMode) {

			jugador.move(level);

			camera.moveJuego(jugador);
		} else {
			camera.moveFree();
		}

		// masterChunk.setActive();
		masterChunk.setActive5(Math.floorDiv((int) jugador.getPosition().x, 16),
				Math.floorDiv((int) jugador.getPosition().y, 16), Math.floorDiv((int) jugador.getPosition().z, 16));

		if (!jugador.onGround) {
			masterColisions.testJugador(jugador, masterChunk.getEntities());
		}

	}

	public void render() {
		masterColisions.render();
		masterChunk.render();
		jugador.render(camera);
		// level.render(camera);
	}

}
