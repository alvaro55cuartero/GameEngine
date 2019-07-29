package events;

import java.util.EnumSet;

public class AppTickEvent extends Event {

	public EventType getEventType() {
		return EventType.APP_TICK;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

}
