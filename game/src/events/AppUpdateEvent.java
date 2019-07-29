package events;

import java.util.EnumSet;

public class AppUpdateEvent extends Event {

	public EventType getEventType() {
		return EventType.APP_UPDATE;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

}