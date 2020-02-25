package engine.main.events.type;

import java.util.EnumSet;

import engine.main.events.Event;

public class AppRenderEvent extends Event {

	public EventType getEventType() {
		return EventType.APP_RENDER;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

}
