package engine.graphics.shaders;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import engine.graphics.Renderer;
import engine.platform.openGL.OpenGLShader;

public abstract class Shader {

	public abstract void bind();

	public abstract void unbind();

	public abstract void setInt(String name, int value);

	public abstract void setFloat3(String name, Vector3f value);

	public abstract void setFloat4(String name, Vector4f value);

	public abstract void setMat4(String name, Matrix4f value);

	public static Shader create(String vertexSrc, String fragmentSrc) {
		switch (Renderer.getApi()) {
		case NONE:
			// HZ_CORE_ASSERT(false, "RendererAPI::None is currently not supported!");
			return null;
		case OPENGL:
			return new OpenGLShader(vertexSrc, fragmentSrc);
		default:
			break;
		}

		return null;
	}

}
