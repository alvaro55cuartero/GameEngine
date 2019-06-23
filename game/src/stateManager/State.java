package stateManager;

import objeto.Camera;

public abstract class State {

	protected Camera camera = new Camera();

	public State() {
	}

	public abstract void input();

	public abstract void tick(StateMachine stateMachine);

	public abstract void render();

	public abstract void reset();

	public abstract void dispose();
}
