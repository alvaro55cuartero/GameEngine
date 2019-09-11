package engine.menuManager;

import org.joml.Vector2f;

import engine.controls.old.InputCursor;
import engine.main.Const;
import engine.objeto.Camera;
import engine.objeto.entities.EntityGUI;
import engine.objeto.entities.EntityGUIBoton;

public class MenuLateral extends EntityGUI {

	private EntityGUIBoton menuBoton1;
	private EntityGUIBoton menuBoton2;
	private EntityGUIBoton menuBoton3;

	public MenuLateral(String ruta) {
		super(9, new Vector2f(0, 0), 40, Const.height);

		menuBoton1 = new EntityGUIBoton(10, new Vector2f(0, Const.height - 40f), 40f, 40f);
		menuBoton2 = new EntityGUIBoton(11, new Vector2f(0, Const.height - 80f), 40f, 40f);
		menuBoton3 = new EntityGUIBoton(12, new Vector2f(0, Const.height - 120f), 40f, 40f);

	}

	public void tick(boolean focus) {
		if (menuBoton1.intersect(InputCursor.coordInPix())) {
			menuBoton1.tick();
		}

	}

	public void render(Camera camera) {
		this.render();
		menuBoton1.render();
		menuBoton2.render();
		menuBoton3.render();
	}

	public void reset() {

	}

}
