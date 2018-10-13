package shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import main.Camera;

public class StaticShader extends Shader {

	private static final String VERTEX_FILE = "src/shader/vertexShader";
	private static final String FRAGMENT_FILE = "src/shader/fragmentShader";

	private int locationTransformationMatrix;
	private int locationProjectionMatrix;
	private int locationViewMatrix;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	public void bindAttributes() {
		super.bindAttributes(0, "position");
		super.bindAttributes(1, "textureCoords");
	}

	protected void getAllUniformLocations() {
		locationTransformationMatrix = super.getUniformLocation("transformationMatrix");
		locationProjectionMatrix = super.getUniformLocation("projectionMatrix");
		locationViewMatrix = super.getUniformLocation("viewMatrix");
	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(locationTransformationMatrix, matrix);
	}

	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(locationProjectionMatrix, matrix);
	}

	public void loadViewMatrix(Camera camera) {
		Matrix4f matrix = new Matrix4f();
		matrix.rotate((float) Math.toRadians(camera.getRoll()), new Vector3f(1, 0, 0))
				.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(0, 1, 0));

		matrix.translate(-camera.getPosition().x, -camera.getPosition().y, -camera.getPosition().z);

		this.loadViewMatrix(matrix);
	}

	public void loadViewMatrix(Matrix4f matrix) {
		super.loadMatrix(locationViewMatrix, matrix);
	}

}
