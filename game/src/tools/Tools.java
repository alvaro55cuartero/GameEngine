package tools;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import objeto.Camera;

public class Tools {

	public static float[] toFloat(Float[] f) {
		float[] r = new float[f.length];
		for (int i = 0; i < f.length; i++) {
			r[i] = f[i];
		}
		return r;
	}

	public static int[] toInt(Integer[] f) {
		int[] r = new int[f.length];
		for (int i = 0; i < f.length; i++) {
			r[i] = f[i];
		}
		return r;
	}

	public static Matrix4f createViewMatrix(Camera camera) {
		Matrix4f matrix = new Matrix4f();
		matrix.rotate((float) Math.toRadians(camera.getRoll()), new Vector3f(1, 0, 0))
				.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(0, 1, 0));

		matrix.translate(-camera.getPosition().x, -camera.getPosition().y, -camera.getPosition().z);
		return matrix;
	}
}
