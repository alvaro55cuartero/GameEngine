package engine.stateManager;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import engine.controls.old.Input;
import engine.objeto.entities.Entity3DPlane;

public class StateMenuOpciones extends State {

	private Entity3DPlane background;

	public StateMenuOpciones() {
		background = new Entity3DPlane(5, new Vector3f(), 0, 0, 0, 0, 0);
	}

	public void input() {
	}

	public void tick(StateMachine stateMachine) {
		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			stateMachine.setCurrentState(stateMachine.getLastState());
		}
	}

	public void render() {
	}

	public void reset() {
	}

	public void dispose() {
	}

}
