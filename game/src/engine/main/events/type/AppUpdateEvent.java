package engine.main.events.type;

import java.util.EnumSet;

import engine.main.events.Event;

public class AppUpdateEvent extends Event {

	public EventType getEventType() {
		return EventType.APP_UPDATE;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

}
