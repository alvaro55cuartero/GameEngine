package game.layers;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import engine.graphics.RenderCommand;
import engine.graphics.Renderer;
import engine.graphics.shaders.Shader;
import engine.main.Timestep;
import engine.main.events.Event;
import engine.main.layer.Layer;
import engine.old.graphics.texture.Texture2D;
import engine.old.main.Const;
import engine.old.objeto.camera.Camera;
import engine.old.objeto.camera.PerspectiveCamera;
import engine.tools.FileUtils;
import pressets.models.Plane;

public class EditorLayer extends Layer {

	private Plane planeBackground = new Plane();
	private PerspectiveCamera camera = new PerspectiveCamera((float) Math.toRadians(60), Const.aspectRatio, 0.1f,
			100.0f);

	private Texture2D texture;
	private Shader textureShader;

	public EditorLayer(String name) {
		super(name);
		camera.setPosition(new Vector3f(0.0f, 0.0f, 0.0f));

		texture = Texture2D.create("res/png/ventana.png");

		String textureShaderVertexSrc = FileUtils.readTxt("src/game/shaders/vertex.glsl");
		String textureShaderFragmentSrc = FileUtils.readTxt("src/game/shaders/fragment.glsl");

		textureShader = Shader.create(textureShaderVertexSrc, textureShaderFragmentSrc);
	}

	public void onAttach() {

	}

	public void onDetach() {

	}

	public void onUpdate(Timestep timestep) {
		Camera.move(camera);
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
