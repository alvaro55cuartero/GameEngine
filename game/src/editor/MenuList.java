package editor;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import graphics.MasterRenderer;
import main.Input;
import objeto.BotonEntity;
import objeto.PlaneEntity;

public class MenuList extends PlaneEntity {
	BotonEntity entities[];

	public int current = 0;
	int count = 0;

	public MenuList(BotonEntity entities[], Vector3f position, float sx, float sy) {
		super(7, position, sx, sy);
		this.entities = entities;
	}

	public void tick() {
		select();
		entities[current].tick();
	}

	public void render() {
		MasterRenderer.processEntityGUI(this);

		entities[current].render();
	}

	public void reset() {
		entities[current].reset();
	}

	public void dispose() {

	}

	private void select() {
		if (Input.get(GLFW.GLFW_KEY_W) && count == 0 && current > 0) {
			current--;
			count = 20;
		}
		if (Input.get(GLFW.GLFW_KEY_S) && count == 0 && current < entities.length - 1) {
			current++;
			count = 20;

		}
		if (count > 0) {
			count--;
		}
	}

}
