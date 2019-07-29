package events;

import java.util.EnumSet;

public class WindowCloseEvent extends Event {

	public EventType getEventType() {
		return EventType.WINDOW_CLOSE;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

}
