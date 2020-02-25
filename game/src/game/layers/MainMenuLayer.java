package game.layers;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import engine.controls.Input;
import engine.graphics.RenderCommand;
import engine.graphics.Renderer;
import engine.graphics.shaders.Shader;
import engine.main.Application;
import engine.main.Timestep;
import engine.main.events.Event;
import engine.main.layer.Layer;
import engine.old.controls.KeyMap;
import engine.old.graphics.texture.Texture2D;
import engine.old.objeto.camera.OrthographicCamera;
import engine.platform.openGL.OpenGLShader;
import engine.tools.FileUtils;
import pressets.models.Plane;

public class MainMenuLayer extends Layer {

	private Plane planeBackground = new Plane();
	private OrthographicCamera camera = new OrthographicCamera(-1.6f, 1.6f, -0.9f, 0.9f);

	private Texture2D texture;
	private Shader textureShader;

	private Application app;

	public MainMenuLayer(String name, Application app) {
		super(name);
		this.app = app;
		camera.setPosition(new Vector3f(0.0f, 0.0f, 0.0f));

		texture = Texture2D.create("res/png/balcon.png");

		String textureShaderVertexSrc = FileUtils.readTxt("src/game/shaders/vertex.glsl");
		String textureShaderFragmentSrc = FileUtils.readTxt("src/game/shaders/fragment.glsl");

		textureShader = Shader.create(textureShaderVertexSrc, textureShaderFragmentSrc);
	}

	public void onAttach() {

	}

	public void onDetach() {

	}

	public void onUpdate(Timestep timestep) {
		if (Input.isKeyPressed(KeyCode.PRO_KEY_0)) {
			app.popLayer();
			app.pushLayer(new EditorLayer("Editor"));
			System.out.println("Editor");
		}

		RenderCommand.setClearColor(new Vector4f(0.1f, 0.1f, 0.1f, 1));
		RenderCommand.clear();
		Renderer.beginScene(camera);
		((OpenGLShader) textureShader).bind();
		texture.bind();

		Renderer.submit((OpenGLShader) textureShader, planeBackground.vertexArray, new Matrix4f().identity());
		Renderer.endScene();

	}

	public void onEvent(Event event) {

	}
}
