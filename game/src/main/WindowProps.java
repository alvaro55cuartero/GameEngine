package main;

public class WindowProps {

	public String title;
	public int width;
	public int height;
	public boolean vSync;

	public WindowProps(String title, int width, int height, boolean vSync) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.vSync = vSync;
	}

	public WindowProps(String title) {
		this(title, 1280, 720, true);
	}
}
