package engine.old.stateManager;

import org.lwjgl.glfw.GLFW;

import engine.old.controls.old.Input;
import engine.old.stateManager.StateMachine.state;

public class StateMenuPausa extends State {

	public StateMenuPausa() {
	}

	public void input() {
	}

	public void tick(StateMachine stateMachine) {
		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			stateMachine.setCurrentState(stateMachine.getLastState());
		}
		if (Input.get(GLFW.GLFW_KEY_M)) {
			stateMachine.setCurrentState(state.MENU_INICIO);
		}
		if (Input.get(GLFW.GLFW_KEY_P)) {
			stateMachine.setCurrentState(state.MENU_OPCIONES);
		}
	}

	public void render() {
	}

	public void reset() {
	}

	public void dispose() {
	}
}
