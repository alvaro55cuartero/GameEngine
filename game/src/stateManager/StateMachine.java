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
		MasterRenderer.processTexturedModels("res/mapa/list");

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
			mainState = new StateCarga();
			break;
		case MENU_INICIO:
			debug("menu inicio");
			mainState = new StateMenuInicio();
			break;
		case JUEGO:
			debug("juego");
			mainState = new StateJuego();
			break;
		case MENU_PAUSA:
			debug("menu pausa");
			mainState = new StateMenuPausa();

			break;
		case MENU_INVENTARIO:
			debug("menu inventario");
			break;
		case EDITOR:
			debug("editor");
			mainState = new StateEditor();
			break;
		case MENU_OPCIONES:
			debug("menu opciones");
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
