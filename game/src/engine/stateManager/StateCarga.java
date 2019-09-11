package engine.stateManager;

import org.joml.Vector2f;

import engine.graphics.old.MasterRenderer;
import engine.main.Const;
import engine.objeto.entities.EntityGUI;
import engine.stateManager.StateMachine.state;

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
