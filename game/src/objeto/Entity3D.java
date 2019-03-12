package objeto;

import org.joml.Rectanglef;
import org.joml.Vector3f;

public class Entity3D extends Entity {

	private boolean solid;

	public Entity3D(int texturedModelId, Vector3f position, float rx, float ry, float rz, float sx, float sy, float sz,
			boolean solid) {
		super(texturedModelId, "3D", position, rx, ry, rz, sx, sy, sz);
		this.solid = solid;
	}

	public Rectanglef getRectf() {
		return new Rectanglef(position.x, position.y, position.x + sx, position.y + sy);
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

}
