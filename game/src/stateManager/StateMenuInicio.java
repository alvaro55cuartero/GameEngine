package stateManager;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import graphics.MasterRenderer;
import main.Const;
import objeto.BotonEntity;
import objeto.PlaneEntity;
import stateManager.StateMachine.state;

public class StateMenuInicio extends State {

	PlaneEntity background;
	BotonEntity jugar;
	BotonEntity editor;

	boolean freeMode = false;

	public StateMenuInicio() {

		jugar = new BotonEntity(6, new Vector3f(300f, 200f, 0f), 400f, 100f, GLFW.GLFW_KEY_J);
		editor = new BotonEntity(6, new Vector3f(300f, 350f, 0f), 400f, 100f, GLFW.GLFW_KEY_E);
		background = new PlaneEntity(5, new Vector3f(0f, 0f, 0f), Const.width, Const.height);

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

		if (Input.get(GLFW.GLFW_KEY_T)) {
			stateMachine.setCurrentState(state.MENU_OPCIONES);
		}

	}

	public void render() {
		MasterRenderer.processEntityGUI(background);
		MasterRenderer.processEntityGUI(jugar);
		MasterRenderer.processEntityGUI(editor);

	}
}
