package level;

import java.util.ArrayList;

import org.joml.Vector3f;

import objeto.Entity3D;
import objeto.EntityPlane;

public class Chunk {

	private String text;
	private int x;
	private int y;
	private int z;
	private int size = 16;
	private boolean active = false;
	private ArrayList<Entity3D> entities = new ArrayList<Entity3D>();

	public Chunk(String text) {
		this.text = text;
		load();
	}

	public void load() {
		String[] lines = text.split("\\|");
		String[] id = lines[0].split(":");
		x = Integer.parseInt(id[0]);
		y = Integer.parseInt(id[1]);
		z = Integer.parseInt(id[2]);
		if (lines.length > 1) {
			String[] ent = lines[1].split(";");
			for (int i = 0; i < ent.length; i++) {
				entities.add(toEntity(ent[i]));
			}
		}
	}

	public Entity3D toEntity(String txt) {
		String[] arg = txt.split(":");
		int id = Integer.parseInt(arg[0]);
		float x = Float.parseFloat(arg[1]);
		float y = Float.parseFloat(arg[2]);
		float z = Float.parseFloat(arg[3]);
		float ancho = Float.parseFloat(arg[4]);
		float alto = Float.parseFloat(arg[5]);
		boolean solid = Boolean.parseBoolean(arg[6]);

		return new EntityPlane(id, new Vector3f(x, y, z), ancho, alto, solid);
	}

	public ArrayList<Entity3D> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity3D> entities) {
		this.entities = entities;
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
			text += entity.getTexturedModelId() + ":" + entity.getPosition().x + ":" + entity.getPosition().y + ":"
					+ entity.getPosition().z + ":" + entity.getSx() + ":" + entity.getSy() + ":" + entity.isSolid()
					+ ";\n";
		}

		return text;
	}

	public void addEntity(Entity3D entity) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPosition().equals(entity.getPosition(), 0)) {
				return;
			}
		}
		entities.add(entity);
	}

	public void removeEntity(Vector3f position) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPosition().equals(position)) {
				entities.remove(i);
			}

		}
	}

	public void clean() {
		text = x + ":" + y + ":" + z + "|\n";
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

	public void update() {
		clean();
		for (Entity3D entity : entities) {
			text += toString(entity);
		}
	}

	private String toString(Entity3D entity) {
		return entity.getTexturedModelId() + ":" + entity.getPosition().x + ":" + entity.getPosition().y + ":"
				+ entity.getPosition().z + ":" + entity.getSx() + ":" + entity.getSy() + ":" + entity.isSolid() + ";\n";
	}
}
