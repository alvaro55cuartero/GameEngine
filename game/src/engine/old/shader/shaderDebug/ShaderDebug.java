package engine.old.shader.shaderDebug;

import org.joml.Matrix4f;

import engine.old.objeto.camera.Camera;
import engine.old.platform.opengl.old.OpenGLShader;

public class ShaderDebug extends OpenGLShader {

	private static final String VERTEX_FILE = "src/engine/shader/shaderDebug/DebugVertexShader";
	private static final String FRAGMENT_FILE = "src/engine/shader/shaderDebug/DebugFragmentShader";

	private int locationTransformationMatrix;
	private int locationProjectionMatrix;
	private int locationViewMatrix;

	public ShaderDebug() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	public void bindAttributes() {
		super.bindAttributes(0, "position");
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
		matrix.rotate(camera.getRotation());

		matrix.translate(-camera.getPosition().x, -camera.getPosition().y, -camera.getPosition().z);

		this.loadViewMatrix(matrix);
	}

	public void loadViewMatrix(Matrix4f matrix) {
		super.loadMatrix(locationViewMatrix, matrix);
	}
}
