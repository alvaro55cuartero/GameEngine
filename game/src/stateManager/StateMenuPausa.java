package stateManager;

import org.lwjgl.glfw.GLFW;

import controls.Input;
import stateManager.StateMachine.state;

public class StateMenuPausa extends State {

	public StateMenuPausa() {

	}

	public void tick(StateMachine stateMachine) {
		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			stateMachine.setCurrentState(stateMachine.lastState);
		}
		if (Input.get(GLFW.GLFW_KEY_M)) {
			stateMachine.setCurrentState(state.MENU_INICIO);
		}
		if (Input.get(GLFW.GLFW_KEY_P)) {
			stateMachine.setCurrentState(state.MENU_OPCIONES);
		}
	}

	public void render() {
		// MasterRenderer.render(camera);
	}

	public void cleanUp() {

	}
}
