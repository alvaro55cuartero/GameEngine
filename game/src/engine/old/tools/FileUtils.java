package engine.old.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUtils {

	public static void crearImagen(int[] data, int width, int height, String ruta) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int ctr = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				image.setRGB(j, i, data[ctr]);
				ctr++;
			}
		}
		try {
			ImageIO.write(image, "png", new File(ruta));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage readImg(String ruta) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File(ruta));

			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String binToHex(String txt) {
		// return Integer.toString(Integer.toBinaryString(txt), 16);
		return null;
	}

}
