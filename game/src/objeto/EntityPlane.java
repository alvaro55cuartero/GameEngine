package objeto;

import org.joml.Vector3f;

public class EntityPlane extends Entity3D {

	public EntityPlane(int texturedModelId, Vector3f position, float sx, float sy, boolean solid) {
		super(texturedModelId, position, 0, 0, 0, sx, sy, 1, solid);
	}
}
