package engine.stateManager.stateJuego;

import org.lwjgl.glfw.GLFW;

import engine.controls.old.Input;
import engine.level.Level;
import engine.stateManager.State;
import engine.stateManager.StateMachine;
import engine.stateManager.StateMachine.state;

public class StateJuego extends State {

	private Level level = new Level();

	public StateJuego() {

	}

	public void input() {
		level.input();
	}

	public void tick(StateMachine stateMachine) {

		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			stateMachine.setCurrentState(state.MENU_PAUSA);
		}

		level.tick(camera);

	}

	public void render() {
		level.render();
	}

	public void reset() {
		level.reset();
	}

	public void dispose() {

	}

}
