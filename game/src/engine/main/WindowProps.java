package engine.main;

import java.util.function.Consumer;

import engine.events.Event;

public class WindowProps {

	public String title;
	public int width;
	public int height;
	public boolean vSync;
	public Consumer<Event> eventFn;

	public WindowProps(String title, int width, int height, boolean vSync) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.vSync = vSync;
	}

	public WindowProps(String title) {
		this(title, 600, 400, true);
	}

	public void eventCallback(Event event) {
		eventFn.accept(event);
	}
}
