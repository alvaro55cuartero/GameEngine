package engine.old.shader.shader3D;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import engine.old.objeto.camera.Camera;
import engine.old.platform.opengl.old.OpenGLShader;

public class Shader3D extends OpenGLShader {

	private static final String VERTEX_FILE = "src/engine/shader/shader3D/vertexShader";
	private static final String FRAGMENT_FILE = "src/engine/shader/shader3D/fragmentShader";

	private int locationTransformationMatrix;
	private int locationProjectionMatrix;
	private int locationViewMatrix;
	private int locationLightPosition;

	public Shader3D() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	public void bindAttributes() {
		super.bindAttributes(0, "position");
		super.bindAttributes(1, "textureCoords");
		super.bindAttributes(2, "normals");
	}

	protected void getAllUniformLocations() {
		locationTransformationMatrix = super.getUniformLocation("transformationMatrix");
		locationProjectionMatrix = super.getUniformLocation("projectionMatrix");
		locationViewMatrix = super.getUniformLocation("viewMatrix");
		locationLightPosition = super.getUniformLocation("lightPos");
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

	public void loadLightPosition(Vector3f position) {
		super.loadVector3f(locationLightPosition, position);
	}

}
