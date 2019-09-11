package engine.events.type;

import java.util.EnumSet;

import engine.events.Event;

public class AppUpdateEvent extends Event {

	public EventType getEventType() {
		return EventType.APP_UPDATE;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

}
