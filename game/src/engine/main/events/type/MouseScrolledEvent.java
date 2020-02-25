package engine.main.events.type;

public class MouseScrolledEvent extends MouseEvent {

	private float xOffset;
	private float yOffset;

	public MouseScrolledEvent(float xOffset, float yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public EventType getEventType() {
		return EventType.MOUSE_SCROLLED;
	}

	public float getxOffset() {
		return xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public String toString() {
		return "Scroll: (" + xOffset + "," + yOffset + ")";
	}
}
