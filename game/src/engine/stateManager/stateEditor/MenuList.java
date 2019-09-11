package engine.stateManager.stateEditor;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import engine.controls.old.Input;
import engine.objeto.entities.EntityGUI;

public class MenuList extends EntityGUI {
	EntityGUI entities[];

	public int current = 0;
	int count = 0;

	public MenuList(EntityGUI entities[], Vector2f position, float sx, float sy) {
		super(16, position, sx, sy);
		this.entities = entities;
	}

	public void tick() {
		select();
	}

	public void render() {
		// MasterRenderer.processEntityGUI(this);

		entities[current].render();
	}

	public void reset() {
	}

	public void dispose() {

	}

	private void select() {
		if (Input.get(GLFW.GLFW_KEY_W) && count == 0 && current > 0) {
			current--;
			count = 10;
		}
		if (Input.get(GLFW.GLFW_KEY_S) && count == 0 && current < entities.length - 1) {
			current++;
			count = 10;

		}
		if (count > 0) {
			count--;
		}
	}

}
