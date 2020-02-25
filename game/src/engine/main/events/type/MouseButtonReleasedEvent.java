package engine.main.events.type;

public class MouseButtonReleasedEvent extends MouseButtonEvent {

	public MouseButtonReleasedEvent(int button) {
		super(button);
	}

	public EventType getEventType() {
		return EventType.MOUSE_BUTTON_RELEASED;
	}

	public String toString() {
		return "Mouse Relased " + this.getButton();
	}
}
