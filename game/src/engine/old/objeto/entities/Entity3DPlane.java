package engine.old.objeto.entities;

import org.joml.Vector3f;

public class Entity3DPlane extends Entity3D {

	public Entity3DPlane(int texturedModelId, Vector3f position, float rx, float ry, float rz, float sx, float sy) {
		super(texturedModelId, position, rx, ry, rz, sx, sy, 1);
	}

	public Entity3DPlane(int texturedModelId, Vector3f position, float sx, float sy) {
		super(texturedModelId, position, 0, 0, 0, sx, sy, 1);
	}

	public Entity3DPlane(Entity3DPlane entity) {
		this(entity.texturedModelId, entity.position, entity.rx, entity.ry, entity.rz, entity.sx, entity.sy);
	}

	public Entity3DPlane(String txt) {
		this(toEntity(txt));
	}

	private static Entity3DPlane toEntity(String txt) {
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

		return new Entity3DPlane(id, new Vector3f(x, y, z), rx, ry, rz, ancho, alto);
	}
}
