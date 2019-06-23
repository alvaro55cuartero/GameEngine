package stateManager;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import graphics.MasterRenderer;
import main.Const;
import menuManager.MenuLista;
import objeto.entities.EntityGUI;
import objeto.entities.EntityGUIBoton;
import stateManager.StateMachine.state;

public class StateMenuInicio extends State {

	private boolean freeMode = false;

	private EntityGUI background;
	private EntityGUIBoton jugar;
	private EntityGUIBoton editor;
	private EntityGUIBoton[] list = new EntityGUIBoton[2];
	private MenuLista menuLista;

	public StateMenuInicio() {
		jugar = new EntityGUIBoton(6, new Vector2f(300f, 200f), 400f, 100f, GLFW.GLFW_KEY_J);
		editor = new EntityGUIBoton(6, new Vector2f(300f, 350f), 400f, 100f, GLFW.GLFW_KEY_E);
		background = new EntityGUI(5, new Vector2f(0f, 0f), Const.width, Const.height);
		list[0] = editor;
		list[1] = jugar;
		menuLista = new MenuLista(list);
	}

	public void input() {
		menuLista.input();
	}

	public void tick(StateMachine stateMachine) {
		menuLista.tick();
		if (jugar.press || menuLista.getPress()[1]) {
			stateMachine.setCurrentState(state.JUEGO);
		}
		if (editor.press || menuLista.getPress()[0]) {
			stateMachine.setCurrentState(state.EDITOR);
		}
		if (Input.get(GLFW.GLFW_KEY_T)) {
			stateMachine.setCurrentState(state.MENU_OPCIONES);
		}

	}

	public void render() {
		MasterRenderer.processEntityGUI(background);
		menuLista.render();
	}

	public void reset() {
		menuLista.reset();
	}

	public void dispose() {
	}
}
