package stateManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import editor.MenuLateral;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import graphics.Loader;
import graphics.MasterRenderer;
import main.Const;
import objeto.Entity;
import objeto.PlaneEntity;
import stateManager.StateMachine.state;
import tools.Batch;
import tools.Lector;

public class StateEditor extends State {

	enum Focus {
		MENULATERAL, VIEWPORT
	}

	List<Entity> entities = new ArrayList<Entity>();

	Vector2f pos;
	int count = 0;

	boolean freeMode = false;

	Level level;
	MenuLateral menuLateral;
	Focus currentFocus = Focus.VIEWPORT;

	PlaneEntity focusEntity;

	int id = 1;

	int[] pixel = new int[Const.sizeInPixels];

	int capa = 0;
	GUIText text;

	public StateEditor() {

		menuLateral = new MenuLateral("res/mapa/list");
		focusEntity = new PlaneEntity(15, new Vector3f(Const.width / 2, Const.height / 2, -1f), Const.width / 8,
				Const.height / 4);

		pos = new Vector2f();
		FontType font = new FontType(Loader.loadTexture("res/Fonts/arial.png"), new File("res/Fonts/arial.fnt"));
		text = new GUIText("" + capa, 1, font, new Vector2f(0, 0), 0.5f, true);
		TextMaster.loadText(text);
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
		rend();
	}

	private void rend() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPosition().z <= capa) {
				MasterRenderer.processEntity3D(entities.get(i));
			}
		}
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

			if (Input.get(GLFW.GLFW_KEY_N) && count == 0) {
				capa--;
				count = 20;
				text.setText("" + capa);
			}

			if (Input.get(GLFW.GLFW_KEY_M) && count == 0) {
				capa++;
				count = 20;
				text.setText("" + capa);
			}

			if (Input.get(GLFW.GLFW_KEY_P) && count == 0) {
				freeMode = true;
				count = 20;
			}
			if (Input.get(GLFW.GLFW_KEY_O) && count == 0) {
				camera.setDir(new Vector3f(0, 0, 0));
				camera.setPosition(new Vector3f(0, 0, 10));
				freeMode = false;
				count = 20;
			}
			if (!freeMode) {
				camera.moveEditor();
			} else {
				camera.moveFree();
			}

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
		ArrayList<Batch> batch = new ArrayList<Batch>();
		boolean find = false;
		// cargarPixels();
		// FileUtils.crearImagen(pixel, Const.ANCHOIMAGENInPixel,
		// Const.ALTOIMAGENInPixel, "res/mapa/mapax.png");
		int count = 0;
		for (int i = 0; i < entities.size(); i++) {

			for (Batch b : batch) {
				if (b.id.matches(find(entities.get(i).getTexturedModelId()))) {
					b.add(entities.get(i).getPosition().x, entities.get(i).getPosition().y,
							entities.get(i).getPosition().z);
					find = true;
				}

			}

			if (!find) {
				int ids = entities.get(i).getTexturedModelId();
				batch.add(new Batch(find(ids), "" + entities.get(i).getSx(), "" + entities.get(i).getSy()));
				batch.get(count).add(entities.get(i).getPosition().x, entities.get(i).getPosition().y,
						entities.get(i).getPosition().z);
				count++;
			}
			find = false;
		}
		String txt = "";
		for (Batch b : batch) {
			txt = txt + b.toString() + "\n\n";
		}
		try {
			Lector.createFile("res/mapa/mapax", txt);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("LINE");
	}

	private String find(int id) {
		String[] nombre;
		try {
			nombre = Lector.leerArchivoTexto("res/mapa/list").split(";");
			for (int i = 0; i < nombre.length; i++) {
				if (id == i) {
					return nombre[i];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
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
		try {
			String txt = Lector.leerArchivoTexto(ruta).replaceAll("\n", "");
			String[] batches = txt.split("#");
			for (int i = 0; i < batches.length; i++) {
				if (!batches[i].matches("")) {
					int id = 0;
					int anchoEntity = 1;
					int altoEntity = 1;
					String[] ele = batches[i].split(";");
					for (int j = 0; j < ele.length; j++) {
						String[] arg = ele[j].split(":");
						if (j == 0) {
							id = MasterRenderer.findId(arg[0]);
							anchoEntity = (int) Float.parseFloat(arg[1]);
							altoEntity = (int) Float.parseFloat(arg[2]);
						} else {
							entities.add(new PlaneEntity(id, new Vector3f(Float.parseFloat(arg[0]),
									Float.parseFloat(arg[1]), Float.parseFloat(arg[2])), anchoEntity, altoEntity));
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// int[] pixels;
		// try {
		// BufferedImage img = ImageIO.read(new File(ruta));
		// int width = img.getWidth();
		// int height = img.getHeight();
		// pixels = new int[width * height];
		// img.getRGB(0, 0, width, height, pixels, 0, width);
		// rellenarEntities(pixels);

		// } catch (IOException e) {
		// e.printStackTrace();
		// }

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
