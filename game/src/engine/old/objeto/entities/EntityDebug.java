package engine.old.objeto.entities;

import org.joml.Vector3f;

public class EntityDebug extends Entity {

	public EntityDebug(Vector3f position, float rx, float ry, float rz, float sx, float sy, float sz) {
		super(0, "debug", position, rx, ry, rz, sx, sy, sz);
	}

}
