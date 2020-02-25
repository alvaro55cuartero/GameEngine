package game;

import engine.main.Application;
import game.layers.DebugLayer;

public class App extends Application {

	public App() {
		this.pushLayer(new DebugLayer("MainMenu"));
	}

	public static void main(String[] args) {
		new App().run();
	}

}
