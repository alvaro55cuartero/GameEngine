package engine.level;

import java.util.ArrayList;

import org.joml.Vector3f;

import engine.colision.BoxColider;
import engine.graphics.old.MasterRenderer;
import engine.objeto.entities.Entity3D;
import engine.objeto.entities.Entity3DPlane;

public class Chunk {

	private String text = "";
	private String textEnt = "";
	private String textColX = "";
	private String textColY = "";
	private String textColGrab = "";
	private int x;
	private int y;
	private int z;
	private int size = 16;
	private boolean active = false;
	private ArrayList<Entity3D> entities = new ArrayList<Entity3D>();
	private ArrayList<BoxColider> colidersX = new ArrayList<BoxColider>();
	private ArrayList<BoxColider> colidersY = new ArrayList<BoxColider>();
	private ArrayList<BoxColider> colidersGrab = new ArrayList<BoxColider>();

	public Chunk(String text) {
		load(text);
	}

	public void render() {
		MasterRenderer.processEntities3D(getEntities());
	}

	public void renderDebug() {
		for (int i = 0; i < getColidersX().size(); i++) {
			getColidersX().get(i).render();
		}
		for (int i = 0; i < getColidersY().size(); i++) {
			getColidersY().get(i).render();
		}
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
			entities.add(new Entity3DPlane(ents[i]));
		}
	}

	public void loadCol(String txt) {
		String[] col = txt.split(";");
		for (int i = 0; i < col.length; i++) {
			if (col[i].startsWith("X")) {
				colidersX.add(new BoxColider(col[i]));
			} else if (col[i].startsWith("Y")) {
				colidersY.add(new BoxColider(col[i]));
			} else if (col[i].startsWith("Grab")) {
				colidersGrab.add(new BoxColider(col[i]));
			}
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
		textColX = "";
		textColY = "";
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
		for (BoxColider colider : colidersX) {
			textColX += colider.toString();
		}
		for (BoxColider colider : colidersX) {
			textColY += colider.toString();
		}
		for (BoxColider colider : colidersGrab) {
			textColGrab += colider.toString();
		}
		text += textEnt + "|\n" + textColX + textColY + textColGrab + "";
	}

	public ArrayList<BoxColider> getColidersX() {
		return colidersX;
	}

	public void setColidersX(ArrayList<BoxColider> coliders) {
		this.colidersX = coliders;
	}

	public ArrayList<BoxColider> getColidersY() {
		return colidersY;
	}

	public void setColidersY(ArrayList<BoxColider> coliders) {
		this.colidersY = coliders;
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

	public ArrayList<BoxColider> getColidersGrab() {
		return colidersGrab;
	}

	public void setColidersGrab(ArrayList<BoxColider> colidersGrab) {
		this.colidersGrab = colidersGrab;
	}

}
