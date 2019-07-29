package events;

import java.util.EnumSet;

public class WindowResizeEvent extends Event {

	private int width;
	private int height;

	public WindowResizeEvent(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public EventType getEventType() {
		return EventType.WINDOW_RESIZE;
	}

	public EnumSet<EventCategory> getCategory() {
		return EnumSet.of(EventCategory.APP);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
