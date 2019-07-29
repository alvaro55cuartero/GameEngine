package events;

import java.util.EnumSet;

public class AppRenderEvent extends Event {

	public EventType getEventType() {
		return EventType.APP_RENDER;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

}
