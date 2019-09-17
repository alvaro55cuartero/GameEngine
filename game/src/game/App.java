package game;

import engine.main.Application;
import game.layers.MainMenu;

public class App extends Application {

	public App() {
		this.pushLayer(new MainMenu("MainMenu"));
	}

	public Application createApplication() {
		return this;
	}

}
