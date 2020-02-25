package engine.old.menuManager;

import java.util.ArrayList;

import engine.old.graphics.old.MasterRenderer;
import engine.old.objeto.entities.Entity;

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
