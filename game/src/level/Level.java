package level;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;
import org.joml.Vector3f;

import colision.MasterColisions;
import graphics.MasterRenderer;
import main.Camera;
import main.Const;
import objeto.Entity;
import objeto.PlaneEntity;
import tools.FileUtils;

public class Level {

	List<Entity> entities = new ArrayList<Entity>();
	List<Entity> debugEntities = new ArrayList<Entity>();
	public Vector2i chunkCoord;

	public Level(MasterColisions masterColision, String ruta) {
		this.chunkCoord = new Vector2i();
		rellenarEntities(ruta);

	}

	private void rellenarEntities(String ruta) {
		BufferedImage img = FileUtils.readImg(ruta);
		int[] pixels = new int[Const.chunkSize * Const.chunkSize * Const.anchoDepth * Const.altoDepth];
		img.getRGB(chunkCoord.x, chunkCoord.y, Const.chunkSize * Const.anchoDepth, Const.chunkSize * Const.altoDepth,
				pixels, 0, Const.chunkSize);
		int y;
		int x;

		for (int k = 0; k < Const.altoDepth; k++) {
			for (int l = 0; l < Const.anchoDepth; l++) {
				y = 0;

				for (int i = k * Const.chunkSize; i < (k * Const.chunkSize) + Const.chunkSize; i++) {
					x = 0;
					for (int j = l * Const.chunkSize; j < (l * Const.chunkSize) + Const.chunkSize; j++) {
						int pixel = pixels[i + (j * Const.chunkSize)];
						int id = (pixel & 0x00ff0000) >>> 16;
						int attrib = (pixel & 0x0000ff00) >>> 8;
						// int op = (pixel & 0x0000ffff);

						int anchoEntity = 1;
						int altoEntity = 1;

						if (((attrib & 128) >>> 7) == 1) {
							anchoEntity = (pixels[i + 1] >>> 24);
						}
						if (((attrib & 64) >>> 6) == 1) {
							altoEntity = (pixels[i * Const.chunkSize] >>> 24);
						}

						entities.add(new PlaneEntity(id, new Vector3f(x, y, -(l + (k * Const.anchoDepth))), anchoEntity,
								altoEntity));
						x++;
					}
					y++;
				}
			}
		}
	}

	public void render(Camera camera) {
		MasterRenderer.processEntity3D(entities);
	}

	public List<Entity> getEntities() {
		return entities;
	}

}
