package engine.events.type;

import java.util.EnumSet;

import engine.events.Event;

public abstract class MouseEvent extends Event {

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.INPUT, EventCategory.MOUSE);
	}

}
