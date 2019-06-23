package menuManager;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import controls.InputControler;
import objeto.entities.EntityGUI;
import objeto.entities.EntityGUIBoton;

public class MenuLista {

	private int current = -1;
	private EntityGUIBoton[] list;
	private EntityGUI focus;
	private boolean[] press;
	private int count = 10;

	public MenuLista(EntityGUIBoton[] list) {
		this.list = list;
		press = new boolean[list.length];
	}

	public void input() {
		if (count == 0) {
			if (Input.get(GLFW.GLFW_KEY_W) || InputControler.press(13)) {
				if (current > -1) {
					current--;
				}
			}
			if (Input.get(GLFW.GLFW_KEY_S) || InputControler.press(15)) {
				if (current < list.length - 1) {
					current++;
				}
			}
			if (Input.get(GLFW.GLFW_KEY_ENTER) || InputControler.press(0)) {
				if (current != -1) {
					press[current] = true;
				}
			}
			count = 10;
		}
		if (count > 0) {
			count--;
		}
	}

	public void tick() {
		if (current != -1) {
			Vector3f pos = list[current].getPosition();
			focus = new EntityGUI(15, new Vector2f(pos.x, pos.y), list[current].getSx(), list[current].getSy());
		}
	}

	public void render() {
		for (EntityGUIBoton entity : list) {
			entity.render();
		}
		if (current != -1) {
			focus.render();
		}
	}

	public void reset() {
		for (int i = 0; i < press.length; i++) {
			press[i] = false;
		}
	}

	public boolean[] getPress() {
		return press;
	}

	public void setPress(boolean[] press) {
		this.press = press;
	}

}
