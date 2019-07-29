package events;

import java.util.EnumSet;

public abstract class MouseEvent extends Event {

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.INPUT, EventCategory.MOUSE);
	}

}
