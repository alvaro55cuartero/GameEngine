package sandbox.test1;

import engine.main.Application;
import sandbox.ExLayer;

public class GameApp extends Application {

	Layer1 layer = new Layer1("layer1");
	ExLayer layer1 = new ExLayer("ksad");

	public GameApp() {
		this.layerStack.pushLayer(layer1);
	}

	public static void main(String args[]) {
		GameApp app = new GameApp();
		app.run();
	}
}
