package engine.main.window;

import java.util.function.Consumer;

import engine.main.Api;
import engine.main.events.Event;
import engine.platform.linux.LinuxWindow;
import engine.platform.mac.MacWindow;
import engine.platform.windows.WindowsWindow;

public abstract class Window {

	private String title;
	private int width;
	private int height;
	protected boolean vSync;
	private Consumer<Event> eventFn;

	public Window(String title, int width, int height, boolean vsync) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.vSync = vsync;
	}

	public abstract void onUpdate();

	public abstract void setEventCallback(Consumer<Event> consumer);

	public abstract void setVSync(boolean b);

	public void eventCallback(Event event) {
		eventFn.accept(event);
	}

	public static Window create(String title, int width, int height, boolean vSync) {
		switch (Api.instance.os) {
		case WINDOWS:
			return new WindowsWindow(title, width, height, vSync);
		case LINUX:
			return new LinuxWindow(title, width, height, vSync);
		case MAC:
			return new MacWindow(title, width, height, vSync);
		case NONE:
			return null;
		default:
			return null;
		}
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isVsync() {
		return vSync;
	}

	public void setEventFn(Consumer<Event> eventFn) {
		this.eventFn = eventFn;
	}

}
