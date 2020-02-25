package engine.old.objeto.entities;

import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.old.graphics.old.MasterRenderer;

public class EntityGUI extends Entity {

	public EntityGUI(int texturedModelId, Vector2f position, float rz, float sx, float sy) {
		super(texturedModelId, "GUI", new Vector3f(position.x, position.y, 0), 0, 0, rz, sx, sy, 0);
	}

	public EntityGUI(int texturedModelId, Vector2f position, float sx, float sy) {
		this(texturedModelId, new Vector2f(position.x, position.y), 0, sx, sy);
	}

	public void render() {
		MasterRenderer.processEntityGUI(this);
	}

}
