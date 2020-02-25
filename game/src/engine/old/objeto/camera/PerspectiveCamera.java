package engine.old.objeto.camera;

import org.joml.Matrix4f;

public class PerspectiveCamera extends Camera {

	public PerspectiveCamera(float fovy, float aspect, float zNear, float zFar) {
		projectionMatrix = new Matrix4f().perspective(fovy, aspect, zNear, zFar, false);
		viewMatrix = new Matrix4f();
		projectionMatrix.mul(viewMatrix, viewProjectionMatrix);
	}
}
