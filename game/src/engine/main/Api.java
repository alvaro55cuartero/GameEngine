package engine.main;

public class Api {

	public static Api instance = create();
	public OS os;
	public GL gl;

	public enum OS {
		NONE, WINDOWS, MAC, LINUX
	}

	public enum GL {
		NONE, OPENGL, VULKAN
	}

	private static Api create() {
		return new Api();
	}

	public Api() {
		selectOS();
		selectGL();
	}

	private void selectOS() {
		String os = System.getProperty("os.name");

		if (os.matches("Linux")) {
			this.os = OS.LINUX;
		} else if (os.matches("mac")) {
			this.os = OS.MAC;
		} else if (os.matches("Windows 10")) {
			this.os = OS.WINDOWS;
		} else {
			this.os = OS.NONE;
			System.out.println("This engine does not support this OS.");
		}
	}

	private void selectGL() {
		switch (this.os) {
		case WINDOWS:
			this.gl = GL.OPENGL;
			break;
		case MAC:
			this.gl = GL.OPENGL;
			break;
		case LINUX:
			this.gl = GL.OPENGL;
		case NONE:
			this.gl = GL.NONE;
			System.out.println("ERROR: Class API SelectGL() This engine does not support any gl");
			break;
		}
	}

}
