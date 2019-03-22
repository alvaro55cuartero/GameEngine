package menuManager;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import objeto.EntityGUI;
import objeto.EntityGUIBoton;

public class MenuDesplegable extends EntityGUI {

	EntityGUIBoton crearMapa;
	EntityGUIBoton abrirMapa;
	EntityGUIBoton guardarMapa;
	EntityGUIBoton menuEditor;
	EntityGUIBoton capaAlante;
	EntityGUIBoton capaAtras;

	public MenuDesplegable(int texturedModelId, Vector2f position, float sx, float sy) {
		super(texturedModelId, position, sx, sy);

		crearMapa = new EntityGUIBoton(10, new Vector2f(20f, 450f), 200f, 60f, GLFW.GLFW_KEY_C);
		abrirMapa = new EntityGUIBoton(11, new Vector2f(20f, 370f), 200f, 60f, GLFW.GLFW_KEY_O);
		guardarMapa = new EntityGUIBoton(12, new Vector2f(20f, 290f), 200f, 60f, GLFW.GLFW_KEY_G);
		menuEditor = new EntityGUIBoton(13, new Vector2f(20f, 210f), 200, 60, GLFW.GLFW_KEY_M);
		capaAlante = new EntityGUIBoton(0, new Vector2f(0, 0), 4, 1, GLFW.GLFW_KEY_O);
		capaAtras = new EntityGUIBoton(0, new Vector2f(0, 0), 1.6f, 0.4f, GLFW.GLFW_KEY_P);
	}

}
