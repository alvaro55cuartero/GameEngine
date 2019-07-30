package main;

public abstract class Window {

	private WindowProps windowProps;

	public Window(WindowProps windowProps) {
		this.windowProps = windowProps;
	}

	public abstract void onUpdate();

	public abstract void setEventCallback();

	public abstract void setVsync(boolean b);

	public abstract boolean isVsync();

	public abstract Window create(WindowProps windowProps);

	public WindowProps getWindowProps() {
		return windowProps;
	}

}