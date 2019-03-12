package stateManager;

import org.joml.Vector2f;

import graphics.MasterRenderer;
import objeto.EntityGUI;
import stateManager.StateMachine.state;

public class StateCarga extends State {

	EntityGUI background;
	boolean ready = true;

	public StateCarga() {
		background = new EntityGUI(5, new Vector2f(-5f, -5f), 100, 100);

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
