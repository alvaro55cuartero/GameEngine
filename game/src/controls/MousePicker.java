package controls;

import org.joml.FrustumRayBuilder;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import main.Camera;
import tools.Tools;

public class MousePicker {

	private Vector3f currentRay;

	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;

	private Camera camera;
	private FrustumRayBuilder f;

	public MousePicker(Camera camera, Matrix4f projectionMatrix) {
		this.camera = camera;
		this.projectionMatrix = projectionMatrix;
		this.viewMatrix = Tools.createViewMatrix(camera);

		f = new FrustumRayBuilder(projectionMatrix.mul(viewMatrix));
	}

	public Vector3f getCurrentRay() {
		return currentRay;
	}

	public void tick() {
		currentRay = calculateMouseRay();
	}

	private Vector3f calculateMouseRay() {
		viewMatrix = Tools.createViewMatrix(camera);
		f.set(viewMatrix);
		return (Vector3f) f.dir(0.5f, 0.5f, new Vector3f());

	}

	public Vector3f getPos(int alcance) {
		Vector3f o = (Vector3f) f.dir(0.5f, 0.5f, new Vector3f()).mul(alcance, new Vector3f()).add(camera.getPosition(),
				new Vector3f());
		return o;
	}

}