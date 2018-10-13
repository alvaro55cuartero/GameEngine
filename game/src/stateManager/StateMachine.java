package stateManager;

import graphics.MasterRenderer;
import main.Const;

public class StateMachine {

	private int count = 0;

	public enum state {
		CARGA, MENU_INICIO, JUEGO, MENU_PAUSA, MENU_INVENTARIO, EDITOR, MENU_OPCIONES
	};

	private state currentState;
	public state lastState;
	State mainState;

	public StateMachine() {
		setCurrentState(state.CARGA);
	}

	public void tick() {
		if (count != 0) {
			count--;
		}
		mainState.tick(this);
	}

	public void render() {
		mainState.render();
		MasterRenderer.render(mainState.camera);
	}

	public void reset() {
		mainState.reset();
	}

	private void loopState() {
		switch (currentState) {
		case CARGA:
			debug("cargando");
			MasterRenderer.resetTexturedModels();
			MasterRenderer.processTexturedModels("res/mapa/list");
			MasterRenderer.changeView(MasterRenderer.TYPE_ORTHO);
			mainState = new StateCarga();
			break;
		case MENU_INICIO:
			debug("menu inicio");
			MasterRenderer.resetTexturedModels();
			MasterRenderer.processTexturedModels("res/mapa/list");
			MasterRenderer.changeView(MasterRenderer.TYPE_ORTHO);
			mainState = new StateMenuInicio();
			break;
		case JUEGO:
			debug("juego");
			MasterRenderer.resetTexturedModels();
			MasterRenderer.processTexturedModels("res/mapa/list");
			MasterRenderer.changeView(MasterRenderer.TYPE_PERSPECTIVE);
			mainState = new StateJuego();
			break;
		case MENU_PAUSA:
			debug("menu pausa");
			MasterRenderer.resetTexturedModels();
			MasterRenderer.processTexturedModels("res/mapa/list");
			MasterRenderer.changeView(MasterRenderer.TYPE_ORTHO);
			mainState = new StateMenuPausa();

			break;
		case MENU_INVENTARIO:
			debug("menu inventario");
			MasterRenderer.resetTexturedModels();
			MasterRenderer.processTexturedModels("res/mapa/list");
			MasterRenderer.changeView(MasterRenderer.TYPE_ORTHO);
			break;
		case EDITOR:
			debug("editor");
			MasterRenderer.resetTexturedModels();
			MasterRenderer.processTexturedModels("res/png/ListaEditor");
			MasterRenderer.changeView(MasterRenderer.TYPE_ORTHO);
			mainState = new StateEditor();
			break;
		case MENU_OPCIONES:
			debug("menu opciones");
			MasterRenderer.resetTexturedModels();
			MasterRenderer.processTexturedModels("res/mapa/list");
			MasterRenderer.changeView(MasterRenderer.TYPE_ORTHO);
			mainState = new StateMenuOpciones();
			break;
		default:
			break;
		}

	}

	private void debug(String txt) {
		if (Const.debugState) {
			System.out.println(txt);
		}
	}

	public void setCurrentState(state state) {
		if (count == 0) {
			lastState = currentState;
			currentState = state;
			loopState();
			count = 30;
		}
	}

	public void cleanUp() {
		mainState.cleanUp();
	}

}
