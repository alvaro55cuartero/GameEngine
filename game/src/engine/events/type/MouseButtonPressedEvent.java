package engine.events.type;

public class MouseButtonPressedEvent extends MouseButtonEvent {

	public MouseButtonPressedEvent(int button) {
		super(button);
	}

	public EventType getEventType() {
		return EventType.MOUSE_BUTTON_PRESSED;
	}

	public String toString() {
		return "Button pressed: " + this.getButton();
	}
}
