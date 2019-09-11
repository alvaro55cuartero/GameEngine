package engine.events.type;

import java.util.EnumSet;

import engine.events.Event;

public abstract class KeyEvent extends Event {

	protected int keyCode;

	public KeyEvent(int keyCode) {
		this.keyCode = keyCode;
	}

	public int GetKeyCode() {
		return keyCode;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.KEYBOARD, EventCategory.INPUT);
	}

}
