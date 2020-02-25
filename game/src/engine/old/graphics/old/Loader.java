package engine.old.graphics.old;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import engine.old.models.RawModel;

public class Loader {

	private static List<Integer> vaos = new ArrayList<Integer>();
	private static List<Integer> vbos = new ArrayList<Integer>();
	private static List<Integer> imgs = new ArrayList<Integer>();

	public static RawModel loadToVao(int[] indices, float[] positions, float[] textureCoords, float[] normals) {
		int vaoId = createVao();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, positions, 3);
		storeDataInAttributeList(1, textureCoords, 2);
		storeDataInAttributeList(2, normals, 3);
		return new RawModel(vaoId, indices.length);
	}

	public static RawModel loadToVao(float[] positions, int[] indices, float[] textureCoords) {
		int vaoId = createVao();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, positions, 3);
		storeDataInAttributeList(1, textureCoords, 2);
		unbind();
		return new RawModel(vaoId, indices.length);
	}

	public static int loadToVao(float[] positions, float[] textureCoords) {
		int vaoId = createVao();
		storeDataInAttributeList(0, positions, 2);
		storeDataInAttributeList(1, textureCoords, 2);
		unbind();
		return vaoId;
	}

	public static void cleanUp() {
		for (int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for (int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for (int img : imgs) {
			GL11.glDeleteTextures(img);
		}
	}

	public static int loadTexture(String file) {

		MemoryStack stack = MemoryStack.stackPush();

		IntBuffer w = stack.mallocInt(1);
		IntBuffer h = stack.mallocInt(1);
		IntBuffer com = stack.mallocInt(1);

		// STBImage.stbi_set_flip_vertically_on_load(true);
		ByteBuffer img = STBImage.stbi_load(file, w, h, com, 4);
		if (img == null) {
			System.out.println(STBImage.stbi_failure_reason() + ": " + file);
		}

		int width = w.get();
		int height = h.get();

		int textureID = GL11.glGenTextures();
		imgs.add(textureID);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE,
				img);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		return textureID;
	}

	public static int createVao() {
		int vaoId = GL30.glGenVertexArrays();
		vaos.add(vaoId);
		GL30.glBindVertexArray(vaoId);
		return vaoId;
	}

	private static void bindIndicesBuffer(int[] indices) {
		int vboId = GL15.glGenBuffers();
		vbos.add(vboId);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}

	private static void storeDataInAttributeList(int AttributeNumber, float[] data, int length) {
		int vboId = GL15.glGenBuffers();
		vbos.add(vboId);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(AttributeNumber, length, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	public static void unbind() {
		GL30.glBindVertexArray(0);
	}

	public static FloatBuffer storeDataInFloatBuffer(float[] data) {

		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();

		return buffer;

	}

	public static IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;

	}

}
