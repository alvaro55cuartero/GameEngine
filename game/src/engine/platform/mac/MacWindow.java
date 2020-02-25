package engine.platform.mac;

import java.util.function.Consumer;

import engine.main.events.Event;
import engine.main.window.Window;

public class MacWindow extends Window {

	public MacWindow(String title, int width, int height, boolean vsync) {
		super(title, width, height, vsync);
	}

	public void onUpdate() {

	}

	public void setEventCallback(Consumer<Event> consumer) {

	}

	public void setVSync(boolean b) {

	}

}
