package engine.controls.old;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

import engine.main.Const;

public class InputCursor implements GLFWCursorPosCallbackI {
	public static Vector2f cursorPosition = new Vector2f(0, 0);
	private static float dx;
	private static float dy;

	public void invoke(long window, double xpos, double ypos) {
		dx = (float) xpos - cursorPosition.x;
		dy = (float) ypos - cursorPosition.y;
		cursorPosition.x = (float) xpos;
		cursorPosition.y = (float) ypos;

		// if (Const.debug) {
		// System.out.println(coord().toString());
		// }
	}

	// DA LAS COORDENADAS DEL RATON EN LOS EJES DE -1 A 1

	public static Vector2f coord() {
		return new Vector2f(((cursorPosition.x / Const.width) * 2) - 1, -(((cursorPosition.y / Const.height) * 2) - 1));
	}

	public static Vector2f coord01() {
		return new Vector2f(cursorPosition.x / Const.width, -(cursorPosition.y / Const.height) + 1);

	}

	public static Vector2f coordInPix() {
		return new Vector2f(cursorPosition.x, -cursorPosition.y + Const.height);
	}

	public static float getDx() {
		return dx;
	}

	public static float getDy() {

		return dy;
	}

	public static void reset() {
		dx = 0;
		dy = 0;
	}

}
