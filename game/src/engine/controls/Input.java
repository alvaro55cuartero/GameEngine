package engine.controls;

import org.joml.Vector2f;

import engine.platform.linux.LinuxInput;

public abstract class Input {
	private static Input instance = new LinuxInput();
	/*
	 * protected: Input() = default; public: Input(const Input&) = delete; Input&
	 * operator=(const Input&) = delete;
	 */

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
