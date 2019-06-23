package controls;

import org.joml.FrustumRayBuilder;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import objeto.Camera;

public class MousePicker {

	private Matrix4f projectionMatrix;
	private Camera camera;
	private FrustumRayBuilder f;

	public MousePicker(Camera camera, Matrix4f projectionMatrix) {
		this.camera = camera;
		this.projectionMatrix = projectionMatrix;
		f = new FrustumRayBuilder();
	}

	public Vector3f getPos(int alcance) {
		Matrix4f m = new Matrix4f(projectionMatrix).rotate(camera.getRotation()).translate(camera.getPosition());
		f.set(m);
		Vector2f v = InputCursor.coord01();
		return (Vector3f) f.dir(v.x, v.y, new Vector3f()).mul(alcance, new Vector3f()).add(camera.getPosition(),
				new Vector3f());
	}

}