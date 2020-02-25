package engine.main.events.type;

import java.util.EnumSet;

import engine.main.events.Event;

public abstract class MouseEvent extends Event {

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.INPUT, EventCategory.MOUSE);
	}

}
