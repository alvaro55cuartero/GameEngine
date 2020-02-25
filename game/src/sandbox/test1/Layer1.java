package sandbox.test1;

import java.util.ArrayList;

import org.joml.Vector3f;
import org.joml.Vector4f;

import engine.graphics.RenderCommand;
import engine.graphics.Renderer;
import engine.graphics.VertexArray;
import engine.graphics.buffers.Buffer;
import engine.graphics.buffers.BufferElement;
import engine.graphics.buffers.BufferLayout;
import engine.graphics.buffers.IndexBuffer;
import engine.graphics.buffers.VertexBuffer;
import engine.graphics.shaders.Shader;
import engine.graphics.textures.Texture2D;
import engine.main.Timestep;
import engine.main.events.Event;
import engine.main.layer.Layer;
import engine.objects.camera.Camera;
import engine.objects.camera.OrthographicCamera;
import engine.tools.FileUtils;

public class Layer1 extends Layer {

	public float[] vertex = { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
			1.0f, 1.0f, 0.0f, 1.0f, 1.0f };
	public int[] indices = { 0, 1, 2, 1, 2, 3 };
	public VertexArray vao;
	public VertexBuffer vbo;
	public Texture2D texture;
	public IndexBuffer ibo;
	public Shader shader;
	public OrthographicCamera camera;
	public Vector3f position = new Vector3f();

	public Layer1(String name) {
		super(name);
		RenderCommand.setClearColor(new Vector4f(0.3f, 0.3f, 0.3f, 0.0f));
		vao = VertexArray.create();
		vbo = VertexBuffer.create(vertex);
		ibo = IndexBuffer.create(indices);
		texture = Texture2D.create("./res/png/jugador.png");
		shader = Shader.create(FileUtils.readTxt("./src/sandbox/shaders/texturevertex.glsl"),
				FileUtils.readTxt("./src/sandbox/shaders/texturefragment.glsl"));
		camera = new OrthographicCamera(-1.6f, 1.6f, -0.9f, 0.9f);

		ArrayList<BufferElement> a = new ArrayList<BufferElement>();
		a.add(new BufferElement("vertices", Buffer.ShaderDataType.FLOAT3));
		a.add(new BufferElement("texture", Buffer.ShaderDataType.FLOAT2));
		vbo.setLayout(new BufferLayout(a));

		vao.addVertexBuffer(vbo);
		vao.setIndexBuffer(ibo);

		shader.bind();

	}

	public void onAttach() {

	}

	public void onDetach() {

	}

	public void onUpdate(Timestep timestep) {
		RenderCommand.clear();
		Renderer.beginScene(camera);
		Camera.moveFree(camera);
		texture.bind();
		Renderer.submit(shader, vao);
	}

	public void onEvent(Event event) {

	}

}
