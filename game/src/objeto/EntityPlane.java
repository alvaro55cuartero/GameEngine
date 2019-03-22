package objeto;

import org.joml.Vector3f;

public class EntityPlane extends Entity3D {

	public EntityPlane(int texturedModelId, Vector3f position, float rx, float ry, float rz, float sx, float sy) {
		super(texturedModelId, position, rx, ry, rz, sx, sy, 1);
	}

	public EntityPlane(int texturedModelId, Vector3f position, float sx, float sy) {
		super(texturedModelId, position, 0, 0, 0, sx, sy, 1);
	}

	public EntityPlane(EntityPlane entity) {
		this(entity.texturedModelId, entity.position, entity.rx, entity.ry, entity.rz, entity.sx, entity.sy);
	}

	public EntityPlane(String txt) {
		this(toEntity(txt));
	}

	private static EntityPlane toEntity(String txt) {
		String[] arg = txt.split(":");
		int id = Integer.parseInt(arg[0]);
		float x = Float.parseFloat(arg[1]);
		float y = Float.parseFloat(arg[2]);
		float z = Float.parseFloat(arg[3]);
		float rx = Float.parseFloat(arg[4]);
		float ry = Float.parseFloat(arg[5]);
		float rz = Float.parseFloat(arg[6]);
		float ancho = Float.parseFloat(arg[7]);
		float alto = Float.parseFloat(arg[8]);

		return new EntityPlane(id, new Vector3f(x, y, z), rx, ry, rz, ancho, alto);
	}
}
