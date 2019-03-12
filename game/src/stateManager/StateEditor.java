package stateManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import editor.MenuLateral;
import fontMeshCreator.EntityGUIText;
import fontMeshCreator.FontType;
import fontRendering.TextMaster;
import graphics.Loader;
import graphics.MasterRenderer;
import level.MasterChunk;
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

	private boolean freeMode = false;
	private boolean capaUnica = false;
	private boolean solid = false;

	private List<Entity> entities = new ArrayList<Entity>();
	private MenuLateral menuLateral;
	private Focus currentFocus = Focus.VIEWPORT;
	private EntityPlane focusEntity;
	private EntityGUIText text;
	private MasterChunk masterChunk;

	public StateEditor() {
		masterChunk = new MasterChunk();
		menuLateral = new MenuLateral("res/mapa/list");
		focusEntity = new EntityPlane(15, new Vector3f(camera.getPosition().x, camera.getPosition().y, capa + 0.1f), 1,
				1, false);

		FontType font = new FontType(Loader.loadTexture("res/Fonts/arial.png"), new File("res/Fonts/arial.fnt"));
		text = new EntityGUIText(10, "capa: " + capa, 1, font, new Vector2f(0, 0), 0.5f, 10f, true);
		MasterRenderer.processEntityGUI(text);
		TextMaster.loadText(text);
		masterChunk.setActive3(0, 0, 0);

	}

	public void tick(StateMachine stateMachine) {
		MasterRenderer.tick();
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
		focusEntity.setPosition(new Vector3f(camera.getPosition().x, camera.getPosition().y, capa + 0.1f));
		masterChunk.setActive5(x, y, z);
	}

	public void render() {
		if (currentFocus == Focus.VIEWPORT) {
			MasterRenderer.processEntity3D(focusEntity);
		}
		menuLateral.render(camera);

		masterChunk.renderCapa(capa, capaUnica);

	}

	public void reset() {
		menuLateral.reset();
	}

	public void focus(StateMachine stateMachine) {
		switch (currentFocus) {
		case VIEWPORT:
			if (Input.get(GLFW.GLFW_KEY_ENTER) && !is(camera.getPosition()) && !freeMode) {
				int xChunk = Math.floorDiv((int) camera.getPosition().x, 16);
				int yChunk = Math.floorDiv((int) camera.getPosition().y, 16);
				int zChunk = Math.floorDiv((int) capa, 16);
				masterChunk.getChunk(xChunk, yChunk, zChunk).addEntity(new EntityPlane(id,
						new Vector3f(camera.getPosition().x, camera.getPosition().y, capa), ancho, alto, solid));
			}
			if (Input.get(GLFW.GLFW_KEY_BACKSPACE)) {
				int xChunk = Math.floorDiv((int) camera.getPosition().x, 16);
				int yChunk = Math.floorDiv((int) camera.getPosition().y, 16);
				int zChunk = Math.floorDiv((int) capa, 16);
				masterChunk.getChunk(xChunk, yChunk, zChunk)
						.removeEntity(new Vector3f(camera.getPosition().x, camera.getPosition().y, capa));

			}
			if (Input.get(GLFW.GLFW_KEY_C) && count == 0) {
				menuLateral.setVisible(true);
				currentFocus = Focus.MENULATERAL;
				count = 20;
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
			if (Input.get(GLFW.GLFW_KEY_O) && count == 0) {
				capaUnica = !capaUnica;
				count = 20;
			}

			if (Input.get(GLFW.GLFW_KEY_P) && count == 0) {
				if (freeMode) {
					MasterRenderer.changeView(MasterRenderer.TYPE_ORTHO);
					camera.setDir(new Vector3f(0, 0, 0));
					camera.setPosition(new Vector3f(0, 0, 10));
					freeMode = false;
					count = 20;
				} else {
					MasterRenderer.changeView(MasterRenderer.TYPE_PERSPECTIVE);
					freeMode = true;
					count = 20;
				}
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
			if (Input.get(GLFW.GLFW_KEY_H) && count == 0) {
				solid = !solid;
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
			if (Input.get(GLFW.GLFW_KEY_C) && count == 0) {
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
		masterChunk.guardar();
	}

	public void cargarMapa() {

	}

}
