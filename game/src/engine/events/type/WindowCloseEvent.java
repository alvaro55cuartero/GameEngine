package engine.events.type;

import java.util.EnumSet;

import engine.events.Event;

public class WindowCloseEvent extends Event {

	public EventType getEventType() {
		return EventType.WINDOW_CLOSE;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

	public String toString() {
		return "Window Close";
	}

}
