package stateManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import controls.MousePicker;
import fontMeshCreator.EntityGUIText;
import fontMeshCreator.FontType;
import fontRendering.TextMaster;
import graphics.Loader;
import graphics.MasterRenderer;
import level.MasterChunk;
import menuManager.MenuLateral;
import objeto.Entity;
import objeto.EntityPlane;
import stateManager.StateMachine.state;

public class StateEditor extends State {

	enum Focus {
		MENULATERAL, VIEWPORT
	}

	private int id = 1;
	private int count = 0;
	private int capa = 0;
	private int ancho = 1;
	private int alto = 1;
	private int alcance = 5;
	private float rx = 0;
	private float ry = 0;
	private float rz = 0;

	private boolean freeMode = false;
	private boolean capaUnica = false;

	private List<Entity> entities = new ArrayList<Entity>();
	private MenuLateral menuLateral;
	private Focus currentFocus = Focus.VIEWPORT;
	private EntityPlane previewEntity;
	private EntityGUIText text;
	private MasterChunk masterChunk;
	private MousePicker mousePicker;

	public StateEditor() {
		masterChunk = new MasterChunk();
		menuLateral = new MenuLateral("res/mapa/list");

		previewEntity = new EntityPlane(id,
				new Vector3f(Math.round(camera.getPosition().x), Math.round(camera.getPosition().y), capa), rx, ry, rz,
				ancho, alto);
		mousePicker = new MousePicker(camera, MasterRenderer.projectionMatrixPerspective());

		FontType font = new FontType(Loader.loadTexture("res/Fonts/arial.png"), new File("res/Fonts/arial.fnt"));
		text = new EntityGUIText(10, "capa: " + capa, 1, font, new Vector2f(0, 0), 0.5f, 10f, true);
		MasterRenderer.processEntityGUI(text);
		TextMaster.loadText(text);
		masterChunk.setActive3(0, 0, 0);

	}

	public void tick(StateMachine stateMachine) {
		menuLateral.tick(true);
		mousePicker.tick();
		if (count > 0) {
			count--;
		}
		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			stateMachine.setCurrentState(state.MENU_PAUSA);
		}
		focus(stateMachine);
		int x = Math.floorDiv((int) this.camera.getPosition().x, 16);
		int y = Math.floorDiv((int) this.camera.getPosition().y, 16);
		int z = Math.floorDiv(capa, 16);
		Vector3f pos = mousePicker.getPos(alcance);
		previewEntity = new EntityPlane(id, new Vector3f(Math.round(pos.x), Math.round(pos.y), Math.round(pos.z)), rx,
				ry, rz, ancho, alto);
		masterChunk.setActive5(x, y, z);
	}

	public void render() {

		MasterRenderer.processEntity3D(previewEntity);
		menuLateral.render(camera);

		masterChunk.renderCapa(capa, capaUnica);

	}

	public void reset() {
		menuLateral.reset();
	}

	public void focus(StateMachine stateMachine) {
		switch (currentFocus) {
		case VIEWPORT:
			if (Input.get(GLFW.GLFW_KEY_ENTER)) {
				int xChunk = Math.floorDiv((int) previewEntity.getPosition().x, 16);
				int yChunk = Math.floorDiv((int) previewEntity.getPosition().y, 16);
				int zChunk = Math.floorDiv((int) previewEntity.getPosition().z, 16);
				masterChunk.getChunk(xChunk, yChunk, zChunk).addEntity(previewEntity);
			}
			if (Input.get(GLFW.GLFW_KEY_BACKSPACE)) {
				int xChunk = Math.floorDiv((int) previewEntity.getPosition().x, 16);
				int yChunk = Math.floorDiv((int) previewEntity.getPosition().y, 16);
				int zChunk = Math.floorDiv((int) previewEntity.getPosition().z, 16);
				masterChunk.getChunk(xChunk, yChunk, zChunk).removeEntity(previewEntity.getPosition());

			}

			if (Input.get(GLFW.GLFW_KEY_N) && count == 0 && capa > 0) {
				capa--;
				count = 20;
				text.setText("" + capa);
			}

			if (Input.get(GLFW.GLFW_KEY_M) && count == 0) {
				capa++;
				count = 20;
				text.setText("" + capa);
			}
			if (Input.get(GLFW.GLFW_KEY_B) && count == 0 && id < 24) {
				id++;
				count = 10;
			}

			if (Input.get(GLFW.GLFW_KEY_V) && count == 0 && id > 0) {
				id--;
				count = 10;
			}
			if (Input.get(GLFW.GLFW_KEY_O) && count == 0) {
				capaUnica = !capaUnica;
				count = 20;
			}
			if (Input.get(GLFW.GLFW_KEY_I) && count == 0) {
				alto++;
				count = 20;
			}
			if (Input.get(GLFW.GLFW_KEY_K) && count == 0) {
				alto--;
				count = 20;
			}
			if (Input.get(GLFW.GLFW_KEY_J) && count == 0) {
				ancho--;
				count = 20;
			}
			if (Input.get(GLFW.GLFW_KEY_L) && count == 0) {
				ancho++;
				count = 20;
			}
			if (Input.get(GLFW.GLFW_KEY_G) && count == 0) {
				rx += 90;
				count = 20;
			}
			if (Input.get(GLFW.GLFW_KEY_H) && count == 0) {
				ry += 90;
				count = 20;
			}
			if (Input.get(GLFW.GLFW_KEY_F) && count == 0) {
				rz += 90;
				count = 20;
			}
			if (Input.get(GLFW.GLFW_KEY_Y) && count == 0) {
				guardarMapa();
				System.out.println("save");
				count = 20;
			}
			if (!freeMode) {
				camera.moveEditor();
			}
			break;
		case MENULATERAL:
			menuLateral.tick(true);
			if (Input.get(GLFW.GLFW_KEY_C) && count == 0) {
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
			if (new Vector3f(v.x, v.y, v.z).equals(entity.getPosition()) && entity.getRx() == rx && entity.getRy() == ry
					&& entity.getRz() == rz) {

				return true;
			}
		}
		return false;
	}

	public void guardarMapa() {
		masterChunk.guardar();
	}

	public void cargarMapa() {

	}
}