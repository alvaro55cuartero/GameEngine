package game.layers;

import java.io.File;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import engine.events.Event;
import engine.graphics.RenderCommand;
import engine.graphics.Renderer;
import engine.layer.Layer;
import engine.main.Timestep;
import engine.models.CuboTexturedModel;
import engine.objeto.OrthographicCamera;
import engine.objeto.entities.Entity3DPlane;
import engine.platform.opengl.OpenGLShader;
import engine.shader.Shader;
import engine.tools.FileUtils;

public class MainMenu extends Layer {

	Entity3DPlane plane = new Entity3DPlane(0, new Vector3f(0, 0, 0), 0, 0, 0, 1, 1);
	CuboTexturedModel model = new CuboTexturedModel("balcon");

	public OrthographicCamera camera;
	public Shader shader;

	private float cameraMoveSpeed = 5.0f;

	private float cameraRotation = 0.0f;
	private float cameraRotationSpeed = 180.0f;

	public MainMenu(String name) {
		super(name);

		String vertexSrc = FileUtils.readTxt(new File("src/game/shaders/vertex.glsl"));

		String fragmentSrc = FileUtils.readTxt(new File("src/game/shaders/fragment.glsl"));

		shader = Shader.create(vertexSrc, fragmentSrc);

	}

	public void onAttach() {

	}

	public void onDetach() {

	}

	public void onUpdate(Timestep timestep) {
		RenderCommand.setClearColor(new Vector4f(0.1f, 0.1f, 0.1f, 1));
		RenderCommand.clear();

		camera.setRotation(cameraRotation);

		Renderer.beginScene(camera);

		Matrix4f scale = new Matrix4f().identity().scale(new Vector3f(0.1f));

		((OpenGLShader) shader).bind();
		((OpenGLShader) shader).uploadUniformFloat3("u_Color", new Vector3f(0.1f, 0.5f, 0.3f));

		for (int y = 0; y < 20; y++) {
			for (int x = 0; x < 20; x++) {
				Vector3f pos = new Vector3f(x * 0.11f, y * 0.11f, 0.0f);
				Matrix4f transform = new Matrix4f().identity().translate(pos).mul(scale);
				Renderer.submit((OpenGLShader) shader, squareVA, transform);
			}

		}

		texture.bind();
		Renderer.submit((OpenGLShader) textureShader, squareVA, new Matrix4f().identity().scale(new Vector3f(1.5f)));
		chernoLogoTexture.bind();
		Renderer.submit((OpenGLShader) textureShader, squareVA, new Matrix4f().identity().scale(new Vector3f(1.5f)));

		// Triangle
		// Hazel::Renderer::Submit(m_Shader, m_VertexArray);

		Renderer.endScene();
	}

	public void onEvent(Event event) {

	}
}
