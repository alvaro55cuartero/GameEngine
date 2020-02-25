package engine.main.events.type;

import java.util.EnumSet;

import engine.main.events.Event;

public class AppTickEvent extends Event {

	public EventType getEventType() {
		return EventType.APP_TICK;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

}
