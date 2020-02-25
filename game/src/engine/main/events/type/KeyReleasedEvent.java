package engine.main.events.type;

public class KeyReleasedEvent extends KeyEvent {

	public KeyReleasedEvent(int keyCode) {
		super(keyCode);
	}

	public EventType getEventType() {
		return EventType.KEY_RELEASED;
	}

	public String toString() {
		return "Key Released Event: " + keyCode;
	}

}
