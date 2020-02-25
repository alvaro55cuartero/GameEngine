package engine.main.events.type;

import java.util.EnumSet;

public abstract class MouseButtonEvent extends MouseEvent {

	private int button;

	public MouseButtonEvent(int button) {
		this.button = button;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.INPUT, EventCategory.MOUSE, EventCategory.MOUSE_BUTTON);
	}

	public int getButton() {
		return button;
	}
}
