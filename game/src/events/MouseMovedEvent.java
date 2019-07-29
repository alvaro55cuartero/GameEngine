package events;

public class MouseMovedEvent extends MouseEvent {

	private float x;
	private float y;

	public MouseMovedEvent(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public EventType getEventType() {
		return EventType.MOUSE_MOVED;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
