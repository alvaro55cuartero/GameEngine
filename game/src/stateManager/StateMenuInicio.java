package stateManager;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import graphics.MasterRenderer;
import main.Input;
import objeto.BotonEntity;
import objeto.PlaneEntity;
import stateManager.StateMachine.state;

public class StateMenuInicio extends State {

	PlaneEntity background;
	BotonEntity jugar;
	BotonEntity editor;

	public StateMenuInicio() {

		jugar = new BotonEntity(6, new Vector3f(-2f, -0f, -4f), 6f * 0.5f, 1f * 0.5f, GLFW.GLFW_KEY_J);
		editor = new BotonEntity(6, new Vector3f(-2f, -1f, -4f), 6f * 0.5f, 1f * 0.5f, GLFW.GLFW_KEY_E);
		background = new PlaneEntity(5, new Vector3f(-5f, -5f, -5f), 10, 10);

	}

	public void tick(StateMachine stateMachine) {
		jugar.tick();
		editor.tick();
		if (jugar.press) {
			stateMachine.setCurrentState(state.JUEGO);
		}
		if (editor.press) {
			stateMachine.setCurrentState(state.EDITOR);
		}

		if (Input.get(GLFW.GLFW_KEY_P)) {
			stateMachine.setCurrentState(state.MENU_OPCIONES);
		}
	}

	public void render() {
		MasterRenderer.processEntityGUI(background);
		MasterRenderer.processEntityGUI(jugar);
		MasterRenderer.processEntityGUI(editor);

	}
}
