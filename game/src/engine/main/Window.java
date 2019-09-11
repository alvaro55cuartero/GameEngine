package engine.main;

import java.util.function.Consumer;

import engine.events.Event;
import engine.platform.linux.LinuxWindow;
import engine.platform.mac.MacWindow;
import engine.platform.windows.WindowsWindow;

public abstract class Window {

	private WindowProps windowProps;

	public Window(WindowProps windowProps) {
		this.windowProps = windowProps;
	}

	public abstract void onUpdate();

	public abstract void setEventCallback(Consumer<Event> consumer);

	public abstract void setVSync(boolean b);

	public abstract boolean isVSync();

	public static Window create(WindowProps windowProps) {
		String os = System.getProperty("os.name");
		if (os.matches("Linux")) {
			return new LinuxWindow(windowProps);
		} else if (os.matches("mac")) {
			return new MacWindow(windowProps);
		} else if (os.matches("windows")) {
			return new WindowsWindow(windowProps);
		} else {
			System.out.println("This engine does not support this OS.");
			return null;
		}

	}

	public WindowProps getWindowProps() {
		return windowProps;
	}

}