package engine.objeto.entities;

import org.joml.Intersectionf;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import engine.controls.old.Input;
import engine.controls.old.InputCursor;
import engine.graphics.old.MasterRenderer;

public class EntityGUIBoton extends EntityGUI {

	boolean visible = true;
	public boolean press = false;
	int boton;
	int count = 0;
	boolean select = false;
	EntityGUI ref;

	public EntityGUIBoton(int texturedModelId, Vector2f position, float rz, float sx, float sy, int boton) {
		super(texturedModelId, position, rz, sx, sy);
		this.boton = boton;
		ref = new EntityGUI(15, new Vector2f(position.x, position.y), rz, sx, sy);
	}

	public EntityGUIBoton(int texturedModelId, Vector2f position, float sx, float sy, int boton) {
		super(texturedModelId, position, sx, sy);
		this.boton = boton;
		ref = new EntityGUI(15, new Vector2f(position.x, position.y), sx, sy);
	}

	public EntityGUIBoton(int texturedModelId, Vector2f position, float sx, float sy) {
		super(texturedModelId, position, sx, sy);
		ref = new EntityGUI(15, new Vector2f(position.x, position.y), sx, sy);
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
		} else if (!press && Intersectionf.testPointAar(InputCursor.coordInPix().x, InputCursor.coordInPix().y,
				position.x, position.y, position.x + sx, position.y + sy) && select && count == 0) {
			press = true;
			count = 20;
		}

	}

	public boolean intersect(Vector2f v) {
		return Intersectionf.testPointAar(v.x, v.y, position.x, position.y, position.x + sx, position.y + sy);
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
