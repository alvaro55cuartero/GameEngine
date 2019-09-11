package engine.events;

public class EventDispatcher {

	private Event event;

	public EventDispatcher(Event event) {
		this.event = event;
	}

	public boolean dispatch(Event.EventType type, EventHandler eventHandler) {
		if (this.event.getEventType() == type) {
			event.handled = eventHandler.onEvent(event);
			return true;
		}
		return false;
	}
}
