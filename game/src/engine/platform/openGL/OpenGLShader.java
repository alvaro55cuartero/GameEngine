package engine.platform.openGL;

import java.nio.FloatBuffer;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import engine.graphics.shaders.Shader;

public class OpenGLShader extends Shader {

	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;

	public OpenGLShader(String vertexSrc, String fragmentSrc) {
		vertexShaderID = loadShader(vertexSrc, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentSrc, GL20.GL_FRAGMENT_SHADER);

		programID = GL20.glCreateProgram();

		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);

		GL20.glLinkProgram(programID);

		if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
			GL20.glGetProgramInfoLog(programID, 500);
			GL20.glDeleteProgram(programID);
			GL20.glDeleteShader(vertexShaderID);
			GL20.glDeleteShader(fragmentShaderID);
		}

		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);

	}

	public void bind() {
		GL20.glUseProgram(programID);
	}

	public void unbind() {
		GL20.glUseProgram(0);
	}

	public void dispose() {
		GL20.glDeleteProgram(programID);
	}

	private int loadShader(String src, int type) {
		int shader = GL20.glCreateShader(type);
		GL20.glShaderSource(shader, src);
		GL20.glCompileShader(shader);

		if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shader, 500));
			System.err.println("no ha compilado el shader");
			System.exit(-1);
		}
		return shader;
	}

	public void uploadUniformInt(String name, int value) {
		int location = GL20.glGetUniformLocation(programID, name);
		GL20.glUniform1i(location, value);
	}

	public void uploadUniformFloat(String name, float value) {
		int location = GL20.glGetUniformLocation(programID, name);
		GL20.glUniform1f(location, value);
	}

	public void uploadUniformFloat2(String name, Vector2f value) {
		int location = GL20.glGetUniformLocation(programID, name);
		GL20.glUniform2f(location, value.x, value.y);
	}

	public void uploadUniformFloat3(String name, Vector3f value) {
		int location = GL20.glGetUniformLocation(programID, name);
		GL20.glUniform3f(location, value.x, value.y, value.z);
	}

	public void uploadUniformFloat4(String name, Vector4f value) {
		int location = GL20.glGetUniformLocation(programID, name);
		GL20.glUniform4f(location, value.x, value.y, value.z, value.w);
	}

	public void uploadUniformMat3(String name, Matrix3f matrix) {
		int location = GL20.glGetUniformLocation(programID, name);
		FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(3 * 3);
		matrix.get(matrixBuffer);
		GL20.glUniformMatrix3fv(location, false, matrixBuffer);
	}

	public void uploadUniformMat4(String name, Matrix4f matrix) {
		int location = GL20.glGetUniformLocation(programID, name);
		FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(4 * 4);
		matrix.get(matrixBuffer);
		GL20.glUniformMatrix4fv(location, false, matrixBuffer);
	}

	public void setInt(String name, int value) {
		uploadUniformInt(name, value);
	}

	public void setFloat3(String name, Vector3f value) {
		uploadUniformFloat3(name, value);
	}

	public void setFloat4(String name, Vector4f value) {
		uploadUniformFloat4(name, value);
	}

	public void setMat4(String name, Matrix4f value) {
		uploadUniformMat4(name, value);
	}

}
