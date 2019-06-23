package stateManager;

import org.joml.Vector2f;

import graphics.MasterRenderer;
import main.Const;
import objeto.entities.EntityGUI;
import stateManager.StateMachine.state;

public class StateCarga extends State {

	private EntityGUI background;
	boolean ready = true;

	public StateCarga() {
		background = new EntityGUI(5, new Vector2f(0, 0), Const.width, Const.height);
	}

	public void input() {
	}

	public void tick(StateMachine stateMachine) {
		if (ready) {
			stateMachine.setCurrentState(state.MENU_INICIO);
		}
	}

	public void render() {
		MasterRenderer.processEntityGUI(background);
	}

	public void reset() {
	}

	public void dispose() {
	}
}
