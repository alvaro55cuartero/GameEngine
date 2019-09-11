package engine.objeto;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class OrthographicCamera extends Camera {

	private float rotation = 0.0f;

	public OrthographicCamera(float left, float right, float bottom, float top) {
		projectionMatrix = new Matrix4f().ortho(left, right, bottom, top, -1.0f, 1.0f);
		viewMatrix = new Matrix4f();
		projectionMatrix.mul(viewMatrix, viewProjectionMatrix);
	}

	public void setPosition(Vector3f position) {
		this.position = position;
		recalculateViewMatrix();
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
		recalculateViewMatrix();
	}

	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}

	public Matrix4f getViewProjectionMatrix() {
		return viewProjectionMatrix;
	}

}
