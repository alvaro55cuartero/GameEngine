package objeto;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import graphics.MasterRenderer;

public class BotonEntity extends EntityPlane {

	boolean visible = true;
	public boolean press = false;
	int boton;
	int count = 0;
	boolean select = false;
	EntityPlane ref;

	public BotonEntity(int texturedModelId, Vector3f position, float sx, float sy, int boton) {
		super(texturedModelId, position, sx, sy);
		this.boton = boton;
		ref = new EntityPlane(15, new Vector3f(position.x, position.y, -1), sx, sy);
	}

	public void tick() {
		if (count > 0) {
			count--;
		}
		if (!press && Input.get(boton) && count == 0) {
			press = true;
			count = 20;
		} else if (!press && Input.get(GLFW.GLFW_KEY_ENTER) && select && count == 0) {
			press = true;
			count = 20;
		} // else if (!press && ColisionUtils.inside(this.position.x, this.position.y,
			// InputCursor.coord().x, yp, x2, y2)
			// && select && count == 0) {
			// press = true;
			// count = 20;
			// }

	}

	public void render() {
		if (visible) {
			MasterRenderer.processEntityGUI(this);
			if (select) {
				MasterRenderer.processEntityGUI(ref);
			}
		}
	}

	public void reset() {
		press = false;
		select = false;
	}

	public void select() {
		select = true;
	}

	public void setVisible(boolean b) {
		visible = b;
	}
}
