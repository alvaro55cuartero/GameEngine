package engine.platform.openGL;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL45;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import engine.graphics.textures.Texture2D;

public class OpenGLTexture2D extends Texture2D {

	private String path;
	private int width;
	private int height;
	private int textureID;

	public OpenGLTexture2D(String path) {
		MemoryStack stack = MemoryStack.stackPush();

		IntBuffer w = stack.mallocInt(1);
		IntBuffer h = stack.mallocInt(1);
		IntBuffer com = stack.mallocInt(1);

		STBImage.stbi_set_flip_vertically_on_load(true);
		ByteBuffer img = STBImage.stbi_load(path, w, h, com, 4);
		if (img == null) {
			System.out.println(STBImage.stbi_failure_reason() + ": " + path);
		}

		int width = w.get();
		int height = h.get();

		textureID = GL45.glCreateTextures(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE,
				img);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}

	public void bind(int slot) {
		GL45.glBindTextureUnit(slot, textureID);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void dispose() {
		GL11.glDeleteTextures(textureID);
	}
}
