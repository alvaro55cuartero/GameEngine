package textures;

import graphics.Loader;

public class SpriteSheet extends ModelTexture {

	public SpriteSheet(String img, String infoRuta) {
		super(Loader.loadTexture(img));
	}

}