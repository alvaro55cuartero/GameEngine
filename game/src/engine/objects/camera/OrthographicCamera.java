package engine.objects.camera;

import org.joml.Matrix4f;

public class OrthographicCamera extends Camera {

	public OrthographicCamera(float left, float right, float bottom, float top) {
		projectionMatrix = new Matrix4f().ortho(left, right, bottom, top, -1.0f, 1.0f);
		viewMatrix = new Matrix4f();
		projectionMatrix.mul(viewMatrix, viewProjectionMatrix);
	}
}
