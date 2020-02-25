package engine.old.fontRendering;

import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.old.platform.opengl.old.OpenGLShader;

public class FontShader extends OpenGLShader {

	private static final String VERTEX_FILE = "src/engine/fontRendering/fontVertex.txt";
	private static final String FRAGMENT_FILE = "src/engine/fontRendering/fontFragment.txt";

	private int locationColour;
	private int locationTranslation;

	public FontShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		locationColour = super.getUniformLocation("colour");
		locationTranslation = super.getUniformLocation("translation");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttributes(0, "position");
		super.bindAttributes(1, "textureCoords");
	}

	protected void loadColour(Vector3f colour) {
		super.loadVector3f(locationColour, colour);
	}

	protected void loadTranslation(Vector2f translation) {
		super.loadVector2f(locationTranslation, translation);
	}

}
