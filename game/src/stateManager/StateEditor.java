package stateManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import editor.MenuLateral;
import graphics.MasterRenderer;
import main.Const;
import main.Input;
import objeto.Entity;
import objeto.PlaneEntity;
import stateManager.StateMachine.state;
import tools.FileUtils;

public class StateEditor extends State {

	enum Focus {
		MENULATERAL, VIEWPORT
	}

	List<Entity> entities = new ArrayList<Entity>();

	Vector2f pos;
	int count = 0;

	Level level;
	MenuLateral menuLateral;
	Focus currentFocus = Focus.VIEWPORT;

	PlaneEntity focusEntity;
	PlaneEntity ref;

	int id = 1;

	int[] pixel = new int[Const.sizeInPixels];

	int capa = -1;

	public StateEditor() {

		menuLateral = new MenuLateral("res/mapa/list");
		focusEntity = new PlaneEntity(6, new Vector3f(0, 0, -1f), 1, 1);
		ref = new PlaneEntity(4, new Vector3f(0, 0, 0), 1, 1);

		pos = new Vector2f();
	}

	public void tick(StateMachine stateMachine) {

		if (count > 0) {
			count--;
		}
		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			stateMachine.setCurrentState(state.MENU_PAUSA);
		}
		focus(stateMachine);
	}

	public void render() {
		if (currentFocus == Focus.VIEWPORT) {
			MasterRenderer.processEntityGUI(focusEntity);
		}
		menuLateral.render(camera);
		MasterRenderer.processEntity3D(ref);
		MasterRenderer.processEntity3D(entities);
	}

	public void reset() {
		menuLateral.reset();
	}

	public void focus(StateMachine stateMachine) {
		switch (currentFocus) {
		case VIEWPORT:
			if (Input.get(GLFW.GLFW_KEY_ENTER) && !is(camera.getPosition())) {
				entities.add(
						new PlaneEntity(id, new Vector3f(camera.getPosition().x, camera.getPosition().y, capa), 1, 1));
			}
			if (Input.get(GLFW.GLFW_KEY_BACKSPACE)) {
				entities.remove(get(new Vector3f(camera.getPosition().x, camera.getPosition().y, capa)));
			}
			if (Input.get(GLFW.GLFW_KEY_I) && count == 0) {
				menuLateral.setVisible(true);
				currentFocus = Focus.MENULATERAL;
				count = 20;
			}
			camera.moveEditor();
			break;
		case MENULATERAL:
			menuLateral.tick(stateMachine, this);
			id = menuLateral.menuList.current;
			if (Input.get(GLFW.GLFW_KEY_I) && count == 0) {
				menuLateral.setVisible(false);
				currentFocus = Focus.VIEWPORT;
				count = 20;
			}
			break;
		}
	}

	public Entity get(Vector3f v) {
		for (Entity entity : entities) {
			if (entity.getPosition().equals(v)) {
				return entity;
			}
		}
		return null;
	}

	public boolean is(Vector3f v) {
		for (Entity entity : entities) {
			if (new Vector3f(v.x, v.y, capa).equals(entity.getPosition())) {
				return true;
			}
		}
		return false;
	}

	public void guardarMapa() {
		cargarPixels();
		FileUtils.crearImagen(pixel, Const.ANCHOIMAGENInPixel, Const.ALTOIMAGENInPixel, "res/mapa/mapax.png");
		System.out.println("LINE");
	}

	private void cargarPixels() {
		for (int i : pixel) {
			i = 0xffffffff;
		}

		for (Entity entity : entities) {
			int x = (int) entity.getPosition().x;
			int y = (int) entity.getPosition().y;
			int z = (int) entity.getPosition().z;

			int chunkx = (int) Math.floor(x / Const.chunkSize);
			int chunky = (int) Math.floor(y / Const.chunkSize);

			x = x - (Const.chunkSize * chunkx);

			y = y - (Const.chunkSize * chunky);

			int id = entity.getTexturedModelId();

			int value = 0xff0000ff + (id << 16);

			pixel[((chunkx * Const.anchoDepthInPixel) + x)
					+ (((chunky * Const.altoDepthInPixel) + y) * Const.ANCHOIMAGENInPixel)] = value;
		}
	}

	public void cargarMapa(String ruta) {
		int[] pixels;
		try {
			BufferedImage img = ImageIO.read(new File(ruta));
			int width = img.getWidth();
			int height = img.getHeight();
			pixels = new int[width * height];
			img.getRGB(0, 0, width, height, pixels, 0, width);
			rellenarEntities(pixels);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void rellenarEntities(int[] pixels) {
		for (int chunky = 0; chunky < Const.CHUNKSPERSIDE; chunky++) {
			for (int chunkx = 0; chunkx < Const.CHUNKSPERSIDE; chunkx++) {
				for (int depthy = 0; depthy < Const.altoDepth; depthy++) {
					for (int depthx = 0; depthx < Const.anchoDepth; depthx++) {
						for (int y = 0; y < Const.chunkSize; y++) {
							for (int x = 0; x < Const.chunkSize; x++) {
								int pixel = pixels[x + (depthx * Const.chunkSize) + (chunkx * Const.anchoDepthInPixel)
										+ (y * Const.ANCHOIMAGENInPixel)
										+ (depthy * Const.ANCHOIMAGENInPixel * Const.chunkSize)
										+ (chunky * Const.ANCHOIMAGENInPixel * Const.chunkSize * Const.altoDepth)];
								int id = (pixel & 0x00ff0000) >>> 16;
								int attrib = (pixel & 0x0000ff00) >>> 8;

								int anchoEntity = 1;
								int altoEntity = 1;

								if (((attrib & 128) >>> 7) == 1) {
									// anchoEntity = (pixels[i + 1] >>> 24);
								}
								if (((attrib & 64) >>> 6) == 1) {
									// altoEntity = (pixels[i * Const.chunkSize] >>> 24);
								}

								entities.add(new PlaneEntity(id, new Vector3f(x + (chunkx * Const.chunkSize),
										y + (chunky * Const.chunkSize), -(depthx + (depthy * Const.anchoDepth) + 1)),
										anchoEntity, altoEntity));

							}
						}
					}
				}
			}
		}
	}
}
