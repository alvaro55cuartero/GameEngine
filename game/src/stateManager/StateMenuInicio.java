package stateManager;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import graphics.MasterRenderer;
import main.Const;
import objeto.EntityGUI;
import objeto.EntityGUIBoton;
import stateManager.StateMachine.state;

public class StateMenuInicio extends State {

	EntityGUI background;
	EntityGUIBoton jugar;
	EntityGUIBoton editor;

	boolean freeMode = false;

	public StateMenuInicio() {

		jugar = new EntityGUIBoton(6, new Vector2f(300f, 200f), 400f, 100f, GLFW.GLFW_KEY_J);
		editor = new EntityGUIBoton(6, new Vector2f(300f, 350f), 400f, 100f, GLFW.GLFW_KEY_E);
		background = new EntityGUI(5, new Vector2f(0f, 0f), Const.width, Const.height);

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
