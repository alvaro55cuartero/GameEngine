package engine.platform.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL45;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import engine.graphics.texture.Texture2D;

public class OpenGLTexture2D extends Texture2D {

	private String path;
	private int width;
	private int height;
	private int rendererID;

	/*
	 * public OpenGLTexture2D(String path) { this.path = path; MemoryStack stack =
	 * MemoryStack.stackPush();
	 * 
	 * IntBuffer width = stack.mallocInt(1); IntBuffer height = stack.mallocInt(1);
	 * IntBuffer channels = stack.mallocInt(1); //
	 * stbi_set_flip_vertically_on_load(1); ByteBuffer img =
	 * STBImage.stbi_load(path, width, height, channels, 0); // HZ_CORE_ASSERT(data,
	 * "Failed to load image!"); if (img == null) {
	 * System.out.println(STBImage.stbi_failure_reason() + ": " + path); }
	 * 
	 * this.width = width.get(); this.height = height.get();
	 * 
	 * int internalFormat = 0; int dataFormat = 0; int channel = channels.get(); if
	 * (channel == 4) { internalFormat = GL11.GL_RGBA8; dataFormat = GL11.GL_RGBA; }
	 * else if (channel == 3) { internalFormat = GL11.GL_RGB8; dataFormat =
	 * GL11.GL_RGB; }
	 * 
	 * // HZ_CORE_ASSERT(internalFormat & dataFormat, "Format not supported!");
	 * 
	 * rendererID = GL45.glCreateTextures(GL11.GL_TEXTURE_2D);
	 * GL45.glTextureStorage2D(rendererID, 1, internalFormat, this.width,
	 * this.height);
	 * 
	 * GL45.glTextureParameteri(rendererID, GL11.GL_TEXTURE_MIN_FILTER,
	 * GL11.GL_LINEAR); GL45.glTextureParameteri(rendererID,
	 * GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	 * 
	 * GL45.glTextureSubImage2D(rendererID, 0, 0, 0, width, height, dataFormat,
	 * GL11.GL_UNSIGNED_BYTE, data);
	 * 
	 * stbi_image_free(data);
	 * 
	 * }
	 */

	public OpenGLTexture2D(String file) {
		MemoryStack stack = MemoryStack.stackPush();

		IntBuffer w = stack.mallocInt(1);
		IntBuffer h = stack.mallocInt(1);
		IntBuffer com = stack.mallocInt(1);

		STBImage.stbi_set_flip_vertically_on_load(true);
		ByteBuffer img = STBImage.stbi_load(file.toString(), w, h, com, 4);
		if (img == null) {
			System.out.println(STBImage.stbi_failure_reason() + ": " + file);
		}

		int width = w.get();
		int height = h.get();

		rendererID = GL45.glCreateTextures(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, rendererID);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE,
				img);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		// return textureID;
	}

	public void dispose() {
		GL11.glDeleteTextures(rendererID);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void bind(int slot) {
		GL45.glBindTextureUnit(slot, rendererID);
	}

}
