package engine.events.type;

public class KeyTypedEvent extends KeyEvent {

	public KeyTypedEvent(int keyCode) {
		super(keyCode);
	}

	public EventType getEventType() {
		return EventType.KEY_TYPED;
	}

	public String toString() {
		return "Key Released Event: " + keyCode;
	}

}
