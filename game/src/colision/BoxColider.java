package colision;

import org.joml.AABBf;
import org.joml.Vector3f;

import graphics.MasterRenderer;
import objeto.EntityDebug;

public class BoxColider {

	private AABBf colider;
	private EntityDebug debug;

	public BoxColider(float x, float y, float z, float sx, float sy, float sz) {
		this.colider = new AABBf(x, y, z, x + sx, y + sy, z + sz);
		debug = new EntityDebug(new Vector3f(x, y, z), 0, 0, 0, sx, sy, sz);

	}

	public BoxColider(String txt) {
		String[] arg = txt.split(":");
		float x = Float.parseFloat(arg[0]);
		float y = Float.parseFloat(arg[1]);
		float z = Float.parseFloat(arg[2]);
		float sx = Float.parseFloat(arg[3]);
		float sy = Float.parseFloat(arg[4]);
		float sz = Float.parseFloat(arg[5]);
		this.colider = new AABBf(x, y, z, x + sx, y + sy, z + sz);
		debug = new EntityDebug(new Vector3f(x, y, z), 0, 0, 0, sx, sy, sz);
	}

	public void render() {
		MasterRenderer.processEntityDebug(debug);
	}

	public AABBf getColider() {
		return colider;
	}

	public void setColider(AABBf colider) {
		this.colider = colider;
	}

	public String toString() {
		return colider.minX + ":" + colider.minY + ":" + colider.minZ + ":" + (colider.maxX - colider.minX) + ":"
				+ (colider.maxY - colider.minY) + ":" + (colider.maxZ - colider.minZ) + ";\n";
	}
}
