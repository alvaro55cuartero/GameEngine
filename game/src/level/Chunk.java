package level;

import java.util.ArrayList;

import org.joml.Vector3f;

import colision.BoxColider;
import objeto.Entity3D;
import objeto.EntityPlane;

public class Chunk {

	private String text = "";
	private String textEnt = "";
	private String textCol = "";
	private int x;
	private int y;
	private int z;
	private int size = 16;
	private boolean active = false;
	private ArrayList<Entity3D> entities = new ArrayList<Entity3D>();
	private ArrayList<BoxColider> coliders = new ArrayList<BoxColider>();

	public Chunk(String text) {
		load(text);
	}

	public void load(String text) {
		String[] partes = text.split("\\|");
		String[] args = partes[0].split(":");
		x = Integer.parseInt(args[0]);
		y = Integer.parseInt(args[1]);
		z = Integer.parseInt(args[2]);
		if (partes.length > 1) {
			loadEnt(partes[1]);
			if (partes.length > 2) {
				loadCol(partes[2]);
			}
		}
	}

	public void loadEnt(String txt) {
		String[] ents = txt.split(";");
		for (int i = 0; i < ents.length; i++) {
			entities.add(new EntityPlane(ents[i]));
		}
	}

	public void loadCol(String txt) {
		String[] col = txt.split(";");
		for (int i = 0; i < col.length; i++) {
			coliders.add(new BoxColider(col[i]));
		}

	}

	public boolean check(int x, int y, int z) {
		if (this.x == x && this.y == y && this.z == z) {
			return true;
		}
		return false;
	}

	public String editChunk(Entity3D[] entities) {
		text += x + ":" + y + ":" + z + "|\n";
		for (Entity3D entity : entities) {
			textEnt += entity.toString();
		}

		return textEnt;
	}

	public void clean() {
		text = x + ":" + y + ":" + z + "|\n";
		textEnt = "";
		textCol = "";
	}

	public void addEntity(Entity3D entity) {
		// for (int i = 0; i < entities.size(); i++) {
		// if (entities.get(i).getPosition().equals(entity.getPosition(), 0)) {
		// return;
		// }
		// }
		entities.add(entity);
	}

	public void removeEntity(Vector3f position) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPosition().equals(position)) {
				entities.remove(i);
			}

		}
	}

	public void update() {
		clean();
		for (Entity3D entity : entities) {
			textEnt += entity.toString();
		}
		for (BoxColider colider : coliders) {
			textCol += colider.toString();
		}
		text += textEnt + "|\n" + textCol + "";
	}

	public ArrayList<BoxColider> getColiders() {
		return coliders;
	}

	public void setColiders(ArrayList<BoxColider> coliders) {
		this.coliders = coliders;
	}

	public ArrayList<Entity3D> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity3D> entities) {
		this.entities = entities;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
