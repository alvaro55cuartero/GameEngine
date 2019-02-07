package main;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

public class InputCursor implements GLFWCursorPosCallbackI {
	public static Vector2f cursorPosition = new Vector2f(0, 0);

	@Override
	public void invoke(long window, double xpos, double ypos) {
		cursorPosition.x = (float) xpos;
		cursorPosition.y = (float) ypos;

		if (Const.debug) {
			System.out.println(coord().toString());
		}
	}

	// DA LAS COORDENADAS DEL RATON EN LOS EJES DE -1 A 1

	public static Vector2f coord() {
		return new Vector2f(((cursorPosition.x / Const.width) * 2) - 1, -(((cursorPosition.y / Const.height) * 2) - 1));
	}

}
