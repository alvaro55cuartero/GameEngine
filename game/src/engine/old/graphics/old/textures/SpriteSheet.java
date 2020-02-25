package engine.old.graphics.old.textures;

import engine.old.graphics.old.Loader;

public class SpriteSheet extends ModelTexture {

	public SpriteSheet(String img, String infoRuta) {
		super(Loader.loadTexture(img));
	}

}