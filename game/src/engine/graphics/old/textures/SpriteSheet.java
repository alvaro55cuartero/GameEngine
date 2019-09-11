package engine.graphics.old.textures;

import engine.graphics.old.Loader;

public class SpriteSheet extends ModelTexture {

	public SpriteSheet(String img, String infoRuta) {
		super(Loader.loadTexture(img));
	}

}