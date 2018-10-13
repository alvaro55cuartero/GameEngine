package stateManager;

import org.lwjgl.glfw.GLFW;

import colision.MasterColisions;
import level.Level;
import main.Input;
import objeto.Jugador;
import stateManager.StateMachine.state;

public class StateJuego extends State {

	Level level;
	Jugador jugador;
	MasterColisions masterColisions;

	public StateJuego() {
		level = new Level(masterColisions, "res/mapa/mapa1.png");
		jugador = new Jugador(7);
		masterColisions = new MasterColisions();
	}

	public void tick(StateMachine stateMachine) {
		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			stateMachine.setCurrentState(state.MENU_PAUSA);
		}

		jugador.move(level);

		camera.moveJuego(jugador);
	}

	public void render() {
		jugador.render(camera);
		level.render(camera);
	}

}
