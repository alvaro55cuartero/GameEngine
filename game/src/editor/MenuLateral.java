package editor;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import graphics.MasterRenderer;
import main.Camera;
import main.Const;
import objeto.BotonEntity;
import objeto.Entity;
import stateManager.StateEditor;
import stateManager.StateMachine;
import tools.FileUtils;

public class MenuLateral extends Entity {

	BotonEntity crearMapa;
	BotonEntity abrirMapa;
	BotonEntity guardarMapa;
	BotonEntity menuEditor;
	BotonEntity capaAlante;
	BotonEntity capaAtras;

	BotonEntity atrasSeleccion;

	public MenuList menuList;

	public static final int nomenu = 0;
	public static final int menuPrincipal = 1;
	public static final int menuSeleccion = 2;

	public static int menu = 1;

	int index = 0;

	int count = 0;

	public boolean visible = false;

	public MenuLateral(String ruta) {
		super(9, new Vector3f(-4, -2, -1), 0, 0, 0, 240, Const.height + 10, 1);

		// menu principal
		crearMapa = new BotonEntity(10, new Vector3f(20f, 450f, 0f), 200f, 60f, GLFW.GLFW_KEY_C);
		abrirMapa = new BotonEntity(11, new Vector3f(20f, 370f, 0f), 200f, 60f, GLFW.GLFW_KEY_O);
		guardarMapa = new BotonEntity(12, new Vector3f(20f, 290f, 0f), 200f, 60f, GLFW.GLFW_KEY_G);
		menuEditor = new BotonEntity(13, new Vector3f(20f, 210f, 0f), 200, 60, GLFW.GLFW_KEY_M);
		capaAlante = new BotonEntity(0, new Vector3f(0, 0, 0f), 4, 1, GLFW.GLFW_KEY_O);
		capaAtras = new BotonEntity(0, new Vector3f(0, 0, 0f), 1.6f, 0.4f, GLFW.GLFW_KEY_P);

		// menu seleccion
		atrasSeleccion = new BotonEntity(14, new Vector3f(-2.8f, 1.5f, -1f), 0.4f, 0.4f, GLFW.GLFW_KEY_DELETE);
		menuList = new MenuList(rellenar("res/mapa/list"), new Vector3f(30f, 350f, 0f), Const.width / 8,
				Const.height / 4);
	}

	public void tick(StateMachine stateMachine, StateEditor stateEditor) {
		switch (menu) {
		case nomenu:

			break;
		case menuPrincipal:
			select(index);
			crearMapa.tick();
			abrirMapa.tick();
			guardarMapa.tick();
			menuEditor.tick();

			if (Input.get(GLFW.GLFW_KEY_UP) && count == 0 && index > 0) {
				index--;
				count = 20;
			}
			if (Input.get(GLFW.GLFW_KEY_DOWN) && count == 0 && index < 3) {
				index++;
				count = 20;

			}
			if (crearMapa.press == true) {
				crearMapa();
			}

			if (abrirMapa.press == true) {
				stateEditor.cargarMapa("res/mapa/mapa");
			}

			if (guardarMapa.press == true) {
				stateEditor.guardarMapa();
			}
			if (menuEditor.press == true) {
				menu = menuSeleccion;
			}

			break;
		case menuSeleccion:
			atrasSeleccion.tick();
			menuList.tick();

			if (atrasSeleccion.press == true) {
				menu = menuPrincipal;
			}
			break;
		}
		if (count > 0) {
			count--;
		}
	}

	public void select(int i) {
		switch (i) {
		case 0:
			crearMapa.select();
			break;
		case 1:
			abrirMapa.select();
			break;
		case 2:
			guardarMapa.select();
			break;
		case 3:
			menuEditor.select();
			break;
		}
	}

	public void render(Camera camera) {
		selectMenu(camera);
	}

	public void reset() {
		crearMapa.reset();
		abrirMapa.reset();
		guardarMapa.reset();
		menuEditor.reset();
		atrasSeleccion.reset();
	}

	private void selectMenu(Camera camera) {
		if (visible) {
			switch (menu) {
			case nomenu:

				break;
			case menuPrincipal:
				MasterRenderer.processEntityGUI(this);
				crearMapa.render();
				abrirMapa.render();
				guardarMapa.render();
				menuEditor.render();

				break;
			case menuSeleccion:
				MasterRenderer.processEntityGUI(this);
				atrasSeleccion.render();
				menuList.render();
				break;
			}
		}
	}

	private BotonEntity[] rellenar(String ruta) {
		String txt = FileUtils.readTxt(ruta);
		txt = txt.replaceAll("\n", "");
		String[] frases = txt.split("\\;");
		BotonEntity[] entities = new BotonEntity[frases.length];

		for (int i = 0; i < frases.length; i++) {
			entities[i] = new BotonEntity(MasterRenderer.findId(frases[i]), new Vector3f(40f, 350, 0), Const.width / 8,
					Const.height / 4, -1);
		}

		return entities;
	}

	private void crearMapa() {

	}

	public void change() {
		visible = !visible;
	}

	public void setVisible(boolean b) {
		visible = b;
	}

}
