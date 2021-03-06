package engine.old.shader.shaderGUI;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import engine.old.platform.opengl.old.OpenGLShader;

public class ShaderGUI extends OpenGLShader {

	private static final String VERTEX_FILE = "src/engine/shader/shaderGUI/GUIVertexShader";
	private static final String FRAGMENT_FILE = "src/engine/shader/shaderGUI/GUIFragmentShader";

	private int locationTransformationMatrix;
	private int locationTextBoolean;
	private int locationColorText;

	public ShaderGUI() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	protected void getAllUniformLocations() {
		locationTransformationMatrix = super.getUniformLocation("transformationMatrix");
		locationTextBoolean = super.getUniformLocation("text");
		locationColorText = super.getUniformLocation("colour");
	}

	protected void bindAttributes() {
		super.bindAttributes(0, "position");
		super.bindAttributes(1, "textureCoords");
	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(locationTransformationMatrix, matrix);
	}

	public void loadTextBoolean(boolean bool) {
		super.loadBoolean(locationTextBoolean, bool);
	}

	public void loadTextColour(Vector3f colour) {
		super.loadVector3f(locationColorText, colour);
	}

}
