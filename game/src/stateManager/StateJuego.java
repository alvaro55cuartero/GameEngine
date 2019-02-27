package stateManager;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import colision.MasterColisions;
import controls.Input;
import level.Level;
import objeto.Jugador;
import stateManager.StateMachine.state;

public class StateJuego extends State {

	Level level;
	Jugador jugador;
	MasterColisions masterColisions;
	boolean freeMode = false;

	public StateJuego() {
		level = new Level(masterColisions, "res/mapa/mapa1.png");
		jugador = new Jugador(8);
		masterColisions = new MasterColisions();
	}

	public void tick(StateMachine stateMachine) {
		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			stateMachine.setCurrentState(state.MENU_PAUSA);
		}
		if (Input.get(GLFW.GLFW_KEY_P)) {
			freeMode = true;
		}
		if (Input.get(GLFW.GLFW_KEY_O)) {
			camera.setDir(new Vector3f(0, 0, 0));
			camera.setPosition(new Vector3f(0, 0, 10));
			freeMode = false;
		}
		if (!freeMode) {

			jugador.move(level);

			camera.moveJuego(jugador);
		} else {
			camera.moveFree();
		}
	}

	public void render() {
		jugador.render(camera);
		level.render(camera);
	}

}
