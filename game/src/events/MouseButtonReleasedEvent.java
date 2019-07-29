package events;

public class MouseButtonReleasedEvent extends MouseButtonEvent {

	public MouseButtonReleasedEvent(int button) {
		super(button);
	}

	public EventType getEventType() {
		return EventType.MOUSE_BUTTON_RELEASED;
	}

}
