package stateManager;

import org.lwjgl.glfw.GLFW;

import main.Input;

public class StateMenuOpciones extends State {

	public StateMenuOpciones() {

	}

	public void tick(StateMachine stateMachine) {
		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			stateMachine.setCurrentState(stateMachine.lastState);
		}
	}

	public void render() {

	}

}
