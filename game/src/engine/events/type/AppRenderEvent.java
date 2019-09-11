package engine.events.type;

import java.util.EnumSet;

import engine.events.Event;

public class AppRenderEvent extends Event {

	public EventType getEventType() {
		return EventType.APP_RENDER;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

}
