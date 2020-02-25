package sandbox;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import engine.controls.Input;
import engine.controls.KeyCode;
import engine.graphics.RenderCommand;
import engine.graphics.Renderer;
import engine.graphics.VertexArray;
import engine.graphics.buffers.Buffer.ShaderDataType;
import engine.graphics.buffers.BufferElement;
import engine.graphics.buffers.BufferLayout;
import engine.graphics.buffers.IndexBuffer;
import engine.graphics.buffers.VertexBuffer;
import engine.graphics.shaders.Shader;
import engine.graphics.textures.Texture2D;
import engine.main.Timestep;
import engine.main.events.Event;
import engine.main.layer.Layer;
import engine.objects.camera.OrthographicCamera;
import engine.platform.openGL.OpenGLShader;
import engine.tools.FileUtils;

public class ExLayer extends Layer {

	private Shader shader;
	// private VertexArray vertexArray;

	private Shader flatColorShader, textureShader;
	private VertexArray squareVA;

	private Texture2D texture, chernoLogoTexture;

	private OrthographicCamera camera;
	private float cameraMoveSpeed = 5.0f;

	private float cameraRotation = 0.0f;
	private float cameraRotationSpeed = 180.0f;

	private Vector3f squareColor = new Vector3f(0.2f, 0.3f, 0.8f);

	public ExLayer(String name) {
		super(name);
		this.camera = new OrthographicCamera(-1.6f, 1.6f, -0.9f, 0.9f);
		camera.setPosition(new Vector3f(0.0f, 0.0f, 0.0f));
		/*
		 * vertexArray = VertexArray.create();
		 * 
		 * float vertices[] = { -0.5f, -0.5f, 0.0f, 0.8f, 0.2f, 0.8f, 1.0f, 0.5f, -0.5f,
		 * 0.0f, 0.2f, 0.3f, 0.8f, 1.0f, 0.0f, 0.5f, 0.0f, 0.8f, 0.8f, 0.2f, 1.0f };
		 * 
		 * VertexBuffer vertexBuffer = VertexBuffer.create(vertices);
		 * 
		 * BufferLayout layout = new BufferLayout(new ArrayList<BufferElement>());
		 * layout.add(new BufferElement(ShaderDataType.FLOAT3, "a_Position"));
		 * layout.add(new BufferElement(ShaderDataType.FLOAT4, "a_Color"));
		 * vertexBuffer.setLayout(layout); vertexArray.addVertexBuffer(vertexBuffer);
		 * 
		 * int indices[] = { 0, 1, 2 }; IndexBuffer indexBuffer; indexBuffer =
		 * IndexBuffer.create(indices); vertexArray.setIndexBuffer(indexBuffer);
		 */

		squareVA = VertexArray.create();
		float squareVertices[] = { 0.5f, 0.5f, 0.0f, 1.0f, 1.0f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f, -0.5f, -0.5f, 0.0f,
				0.0f, 0.0f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f };

		VertexBuffer squareVB = VertexBuffer.create(squareVertices);
		ArrayList<BufferElement> b = new ArrayList<BufferElement>();
		b.add(new BufferElement("a_Position", ShaderDataType.FLOAT3));
		b.add(new BufferElement("a_TexCoord", ShaderDataType.FLOAT2));
		BufferLayout layout2 = new BufferLayout(b);

		squareVB.setLayout(layout2);
		squareVA.addVertexBuffer(squareVB);

		int squareIndices[] = { 0, 1, 2, 2, 3, 0 };
		IndexBuffer squareIB = IndexBuffer.create(squareIndices);
		squareVA.setIndexBuffer(squareIB);

		String vertexSrc = FileUtils.readTxt("src/sandbox/shaders/vertex.glsl");
		String fragmentSrc = FileUtils.readTxt("src/sandbox/shaders/fragment.glsl");
		shader = Shader.create(vertexSrc, fragmentSrc);

		String flatColorShaderVertexSrc = FileUtils.readTxt("src/sandbox/shaders/flatcolorvertex.glsl");
		String flatColorShaderFragmentSrc = FileUtils.readTxt("src/sandbox/shaders/flatcolorfragment.glsl");
		flatColorShader = Shader.create(flatColorShaderVertexSrc, flatColorShaderFragmentSrc);

		String textureShaderVertexSrc = FileUtils.readTxt("src/sandbox/shaders/texturevertex.glsl");
		String textureShaderFragmentSrc = FileUtils.readTxt("src/sandbox/shaders/texturefragment.glsl");
		textureShader = Shader.create(textureShaderVertexSrc, textureShaderFragmentSrc);

		texture = Texture2D.create("res/png/balcon.png");
		chernoLogoTexture = Texture2D.create("res/png/balcon.png");

		textureShader.bind();
		textureShader.setInt("u_Texture", 0);

	}

	public void onAttach() {

	}

	public void onDetach() {

	}

	public void onUpdate(Timestep timestep) {

		if (Input.isKeyPressed(KeyCode.PRO_KEY_LEFT)) {
			camera.getPosition().x -= cameraMoveSpeed * timestep.getSeconds();
		} else if (Input.isKeyPressed(KeyCode.PRO_KEY_RIGHT)) {
			camera.getPosition().x += cameraMoveSpeed * timestep.getSeconds();
		}

		if (Input.isKeyPressed(KeyCode.PRO_KEY_UP)) {
			camera.getPosition().y += cameraMoveSpeed * timestep.getSeconds();
		} else if (Input.isKeyPressed(KeyCode.PRO_KEY_DOWN)) {
			camera.getPosition().y -= cameraMoveSpeed * timestep.getSeconds();
		}

		if (Input.isKeyPressed(KeyCode.PRO_KEY_A)) {
			cameraRotation += cameraRotationSpeed * timestep.getSeconds();
		}

		if (Input.isKeyPressed(KeyCode.PRO_KEY_D)) {
			cameraRotation -= cameraRotationSpeed * timestep.getSeconds();
		}

		RenderCommand.setClearColor(new Vector4f(0.1f, 0.1f, 0.1f, 1));
		RenderCommand.clear();

		// camera.setRotation(cameraRotation);

		Renderer.beginScene(camera);

		Matrix4f scale = new Matrix4f().identity().scale(new Vector3f(0.1f));

		((OpenGLShader) flatColorShader).bind();
		((OpenGLShader) flatColorShader).uploadUniformFloat3("u_Color", squareColor);

		for (int y = 0; y < 20; y++) {
			for (int x = 0; x < 20; x++) {
				Vector3f pos = new Vector3f(x * 0.11f, y * 0.11f, 0.0f);
				Matrix4f transform = new Matrix4f().identity().translate(pos).mul(scale);
				Renderer.submit((OpenGLShader) flatColorShader, squareVA, transform);
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
