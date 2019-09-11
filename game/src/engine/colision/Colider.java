package engine.colision;

import org.joml.Vector3f;

import engine.objeto.entities.Entity;

public class Colider extends Entity {

	public Colider(int texturedModelId, String type, Vector3f position, float rx, float ry, float rz, float sx,
			float sy, float sz) {
		super(texturedModelId, type, position, rx, ry, rz, sx, sy, sz);
	}

}
