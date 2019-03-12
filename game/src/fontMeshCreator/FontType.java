package fontMeshCreator;

import java.io.File;

import textures.ModelTexture;

public class FontType extends ModelTexture {

	private TextMeshCreator loader;

	public FontType(int textureID, File fontFile) {
		super(textureID);
		this.loader = new TextMeshCreator(fontFile);
	}

	public TextMeshData loadText(EntityGUIText text) {
		return loader.createTextMesh(text);
	}

}
