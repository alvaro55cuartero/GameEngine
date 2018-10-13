package stateManager;

import org.joml.Vector3f;

import graphics.MasterRenderer;
import objeto.PlaneEntity;
import stateManager.StateMachine.state;

public class StateCarga extends State {

	PlaneEntity background;
	boolean ready = true;

	public StateCarga() {
		background = new PlaneEntity(5, new Vector3f(-5f, -5f, -5), 100, 100);

	}

	public void tick(StateMachine stateMachine) {
		if (ready) {
			stateMachine.setCurrentState(state.MENU_INICIO);
		}
	}

	public void render() {
		MasterRenderer.processEntityGUI(background);
	}
}
