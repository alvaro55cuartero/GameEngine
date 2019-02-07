package menuManager;

import java.util.ArrayList;

import graphics.MasterRenderer;
import objeto.Entity;

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
