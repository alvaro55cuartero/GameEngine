package events;

import java.util.EnumSet;

public abstract class Event {

	public boolean handled = false;

	public enum EventType {
		NONE, WINDOW_CLOSE, WINDOW_RESIZE, WINDOW_FOCUS, WINDOW_LOST_FOCUS, WINDOW_MOVED, APP_TICK, APP_UPDATE, APP_RENDER, KEY_PRESSED, KEY_RELEASED, MOUSE_BUTTON_PRESSED, MOUSE_BUTTON_RELEASED, MOUSE_MOVED, MOUSE_SCROLLED
	};

	public enum EventCategory {
		NONE, APP, INPUT, KEYBOARD, MOUSE, MOUSE_BUTTON
	};

	public boolean isInCategory(EventCategory e) {
		return getCategory().contains(e);
	}

	public abstract EventType getEventType();

	public abstract EnumSet<EventCategory> getCategory();
}
