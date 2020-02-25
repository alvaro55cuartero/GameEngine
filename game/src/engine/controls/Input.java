package engine.controls;

import org.joml.Vector2f;

import engine.main.Api;
import engine.platform.windows.WindowsInput;

public abstract class Input {

	public static Input instance = create();

	public static Input create() {
		switch (Api.instance.os) {
		case WINDOWS:
			return new WindowsInput();
		case LINUX:
			return null;
		case MAC:
			return null;
		case NONE:
			return null;
		default:
			return null;
		}
	}

	public static boolean isKeyPressed(int keycode) {
		return instance.isKeyPressedImpl(keycode);
	}

	public static boolean isMouseButtonPressed(int button) {
		return instance.isMouseButtonPressedImpl(button);
	}

	public static Vector2f getMousePosition() {
		return instance.getMousePositionImpl();
	}

	public static float getMouseX() {
		return instance.getMouseXImpl();
	}

	public static float getMouseY() {
		return instance.getMouseYImpl();
	}

	protected abstract boolean isKeyPressedImpl(int keycode);

	protected abstract boolean isMouseButtonPressedImpl(int button);

	protected abstract Vector2f getMousePositionImpl();

	protected abstract float getMouseXImpl();

	protected abstract float getMouseYImpl();
}
