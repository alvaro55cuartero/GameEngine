package objeto;

import org.joml.Vector3f;

public class Entity {

	protected int texturedModelId;
	protected Vector3f position;
	protected float rx, ry, rz;
	protected float sx, sy, sz;
	protected boolean solid = false;

	public Entity(int texturedModelId, Vector3f position, float rx, float ry, float rz, float sx, float sy, float sz) {
		super();
		this.texturedModelId = texturedModelId;
		this.position = position;
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		this.sx = sx;
		this.sy = sy;
		this.sz = sz;
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}

	public void increaseRotation(float dx, float dy, float dz) {
		this.rx += dx;
		this.ry += dy;
		this.rz += dz;
	}

	public int getTexturedModelId() {
		return texturedModelId;
	}

	public void setTexturedModel(int texturedModelId) {
		this.texturedModelId = texturedModelId;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRx() {
		return rx;
	}

	public void setRx(float rx) {
		this.rx = rx;
	}

	public float getRy() {
		return ry;
	}

	public void setRy(float ry) {
		this.ry = ry;
	}

	public float getRz() {
		return rz;
	}

	public void setRz(float rz) {
		this.rz = rz;
	}

	public float getSx() {
		return sx;
	}

	public float getSy() {
		return sy;
	}

	public float getSz() {
		return sz;
	}

	public void setSx(float sx) {
		this.sx = sx;
	}

	public void setSy(float sy) {
		this.sy = sy;
	}

	public void setSz(float sz) {
		this.sz = sz;
	}

	public void setSolid(boolean b) {
		solid = b;
	}

}
