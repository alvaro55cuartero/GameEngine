package engine.platform.windows;

import java.util.function.Consumer;

import engine.main.Window;
import engine.main.WindowProps;

public class WindowsWindow extends Window {

	public WindowsWindow(WindowProps windowProps) {
		super(windowProps);
	}

	public void onUpdate() {

	}

	public void setEventCallback(Consumer<?> consumer) {

	}

	public void setVsync(boolean b) {

	}

	public boolean isVsync() {
		return false;
	}
}
