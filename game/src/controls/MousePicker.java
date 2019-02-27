package controls;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import main.Camera;
import tools.Tools;

public class MousePicker {

	private Vector3f currentRay;

	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;

	private Camera camera;

	public MousePicker(Camera camera, Matrix4f projectionMatrix) {
		this.camera = camera;
		this.projectionMatrix = projectionMatrix;
		this.viewMatrix = Tools.createViewMatrix(camera);
	}

	public Vector3f getCurrentRay() {
		return currentRay;
	}

	public void tick() {
		viewMatrix = Tools.createViewMatrix(camera);
		currentRay = calculateMouseRay();
	}

	private Vector3f calculateMouseRay() {
		Vector4f clipCoords = new Vector4f(0, 0, -1f, 1f);
		Vector4f eyeCoords = toEyeCoords(clipCoords);
		// System.out.println(eyeCoords);
		Vector3f worldRay = toWorldCoords(eyeCoords);
		return worldRay;
	}

	private Vector3f toWorldCoords(Vector4f eyeCoords) {
		Matrix4f invertedView = viewMatrix.invert();
		Vector4f worldCoords = invertedView.transform(eyeCoords);
		return new Vector3f(worldCoords.x, worldCoords.y, worldCoords.z).normalize();
	}

	private Vector4f toEyeCoords(Vector4f clipCoords) {
		Matrix4f invertedProjection = projectionMatrix.invert();
		Vector4f eyeCoords = invertedProjection.transform(clipCoords);
		return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);
	}
}