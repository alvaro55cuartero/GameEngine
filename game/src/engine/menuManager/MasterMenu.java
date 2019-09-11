package engine.menuManager;

import java.util.ArrayList;

import engine.graphics.old.MasterRenderer;
import engine.objeto.entities.Entity;

public class MasterMenu {

	private static ArrayList<Entity> entities = new ArrayList<Entity>();

	public MasterMenu() {

	}

	public void tick() {

	}

	public void render() {
		MasterRenderer.processEntitiesGUI(entities);
	}

}
