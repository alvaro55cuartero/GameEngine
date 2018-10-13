package objeto;

import org.joml.Vector3f;

public class PlaneEntity extends Entity {

	public PlaneEntity(int texturedModelId, Vector3f position, float sx, float sy) {
		super(texturedModelId, position, 0, 0, 0, sx, sy, 1);
	}

	public boolean isColision(Jugador jugador) {
		return false;
	}

}
