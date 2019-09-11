package engine.events.type;

public class KeyPressedEvent extends KeyEvent {

	private int repeatCount;

	public KeyPressedEvent(int keyCode, int repeatCount) {
		super(keyCode);
		this.repeatCount = repeatCount;
	}

	public EventType getEventType() {
		return EventType.KEY_PRESSED;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public String toString() {
		return "Key Pressed Event: " + keyCode + " (" + repeatCount + " repeats)";
	}
}
