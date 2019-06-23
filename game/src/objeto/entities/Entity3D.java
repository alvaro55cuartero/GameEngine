package objeto.entities;

import org.joml.Vector3f;

public class Entity3D extends Entity {

	public Entity3D(int texturedModelId, Vector3f position, float rx, float ry, float rz, float sx, float sy,
			float sz) {
		super(texturedModelId, "3D", position, rx, ry, rz, sx, sy, sz);
	}

	public String toString() {
		return this.getTexturedModelId() + ":" + this.getPosition().x + ":" + this.getPosition().y + ":"
				+ this.getPosition().z + ":" + this.getRx() + ":" + this.getRy() + ":" + this.getRz() + ":"
				+ this.getSx() + ":" + this.getSy() + ";\n";
	}

}
