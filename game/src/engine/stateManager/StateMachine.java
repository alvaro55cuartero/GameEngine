package engine.stateManager;

import engine.graphics.old.MasterRenderer;
import engine.stateManager.stateEditor.StateEditor;
import engine.stateManager.stateJuego.StateJuego;

public class StateMachine {

	private int count = 0;
	private state currentState;
	private state lastState;
	private State mainState;
	private boolean change;

	public enum state {
		CARGA, MENU_INICIO, JUEGO, MENU_PAUSA, MENU_INVENTARIO, EDITOR, MENU_OPCIONES
	};

	public StateMachine() {
		setCurrentState(state.CARGA);
		MasterRenderer.processTexturedModels("res/mapa/list");
	}

	public void input() {
		loopState();
		mainState.input();
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

	public void dispose() {
		mainState.dispose();
	}

	private void loopState() {
		if (change) {
			switch (currentState) {
			case CARGA:
				// debug("cargando");
				mainState = new StateCarga();
				break;
			case MENU_INICIO:
				// debug("menu inicio");
				mainState = new StateMenuInicio();
				break;
			case JUEGO:
				// debug("juego");
				mainState = new StateJuego();
				break;
			case MENU_PAUSA:
				// debug("menu pausa");
				mainState = new StateMenuPausa();

				break;
			case MENU_INVENTARIO:
				// debug("menu inventario");
				break;
			case EDITOR:
				// debug("editor");
				mainState = new StateEditor();
				break;
			case MENU_OPCIONES:
				// debug("menu opciones");
				mainState = new StateMenuOpciones();
				break;
			default:
				break;
			}
			change = false;
		}
	}

	public void setCurrentState(state state) {
		if (count == 0) {
			setLastState(currentState);
			currentState = state;
			change = true;
			count = 30;
		}
	}

	public state getLastState() {
		return lastState;
	}

	public void setLastState(state lastState) {
		this.lastState = lastState;
	}
}
