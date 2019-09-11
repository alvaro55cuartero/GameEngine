package sandbox;

import engine.main.Application;

public class GameApp extends Application {

	public GameApp() {
		pushLayer(new ExLayer(""));
	}

	public Application createApplication() {
		return new GameApp();
	}
}
