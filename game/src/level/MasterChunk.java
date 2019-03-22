package level;

import java.io.IOException;
import java.util.ArrayList;

import org.joml.Vector3f;

import colision.BoxColider;
import graphics.MasterRenderer;
import objeto.Entity3D;
import tools.Lector;

public class MasterChunk {

	private int size = 16;
	private Chunk[] chunks = new Chunk[size * size * size];

	public MasterChunk() {
		load("res/mapa/mapa6X");
	}

	private void load(String ruta) {
		try {
			String[] parrafos = Lector.leerArchivoTexto(ruta).replaceAll(" ", "").replaceAll("\n", "").split("#");

			for (int i = 0; i < parrafos.length; i++) {
				chunks[i] = new Chunk(parrafos[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render() {
		for (Chunk chunk : chunks) {
			if (chunk.isActive()) {
				MasterRenderer.processEntities3D(chunk.getEntities());
			}
		}
	}

	public void renderDebug() {
		for (Chunk chunk : chunks) {
			if (chunk.isActive()) {
				for (int i = 0; i < chunk.getColiders().size(); i++) {
					chunk.getColiders().get(i).render();
				}
			}
		}
	}

	public void renderCapa(int capa, boolean unaCapa) {
		for (Chunk chunk : chunks) {
			if (chunk.isActive()) {
				ArrayList<Entity3D> entities = chunk.getEntities();
				for (int i = 0; i < entities.size(); i++) {
					if (!unaCapa) {
						if (entities.get(i).getPosition().z <= capa) {
							MasterRenderer.processEntity3D(entities.get(i));

						}
					} else {
						if (entities.get(i).getPosition().z == capa) {
							MasterRenderer.processEntity3D(entities.get(i));
						}
					}
				}
			}
		}
	}

	public ArrayList<Entity3D> cargarChunk(int x, int y, int z) {
		return getChunk(x, y, z).getEntities();
	}

	public Chunk getChunk(int x, int y, int z) {
		if (x >= 0 && x <= size - 1 && y >= 0 && y <= size - 1 && z >= 0 && z <= size - 1) {
			return chunks[z + y * size + x * size * size];
		} else {
			return null;
		}
	}

	public Chunk getChunk(Vector3f v) {
		return getChunk((int) v.x, (int) v.y, (int) v.z);
	}

	public Chunk[] getActiveChunks() {
		ArrayList<Chunk> chunks = new ArrayList<Chunk>();
		for (int i = 0; i < this.chunks.length; i++) {
			if (this.chunks[i].isActive()) {
				chunks.add(this.chunks[i]);
			}
		}
		return chunks.toArray(new Chunk[chunks.size()]);
	}

	public ArrayList<Entity3D> getEntities() {
		ArrayList<Entity3D> entities = new ArrayList<Entity3D>();
		Chunk[] chunks = getActiveChunks();
		for (Chunk chunk : chunks) {
			entities.addAll(chunk.getEntities());
		}
		return entities;
	}

	public ArrayList<BoxColider> getColision() {
		ArrayList<BoxColider> coliders = new ArrayList<BoxColider>();
		Chunk[] chunks = getActiveChunks();
		for (Chunk chunk : chunks) {
			coliders.addAll(chunk.getColiders());
		}
		return coliders;
	}

	public void cleanChunks() {
		for (Chunk chunk : chunks) {
			chunk.clean();
		}
	}

	public void setActive(int x, int y, int z) {

		Chunk chunk = getChunk(x, y, z);
		if (chunk != null) {
			chunk.setActive(true);
		}
	}

	public void setActive3(int x, int y, int z) {
		disable();
		setActive(x - 1, y - 1, z - 1);
		setActive(x - 1, y - 1, z);
		setActive(x - 1, y - 1, z + 1);
		setActive(x - 1, y, z - 1);
		setActive(x - 1, y, z);
		setActive(x - 1, y, z + 1);
		setActive(x - 1, y + 1, z - 1);
		setActive(x - 1, y + 1, z);
		setActive(x - 1, y + 1, z + 1);

		setActive(x, y - 1, z - 1);
		setActive(x, y - 1, z);
		setActive(x, y - 1, z + 1);
		setActive(x, y, z - 1);
		setActive(x, y, z);
		setActive(x, y, z + 1);
		setActive(x, y + 1, z - 1);
		setActive(x, y + 1, z);
		setActive(x, y + 1, z + 1);

		setActive(x + 1, y - 1, z - 1);
		setActive(x + 1, y - 1, z);
		setActive(x + 1, y - 1, z + 1);
		setActive(x + 1, y, z - 1);
		setActive(x + 1, y, z);
		setActive(x + 1, y, z + 1);
		setActive(x + 1, y + 1, z - 1);
		setActive(x + 1, y + 1, z);
		setActive(x + 1, y + 1, z + 1);
	}

	public void setActive5(int x, int y, int z) {
		disable();

		setActive(x - 2, y - 2, z - 2);
		setActive(x - 2, y - 2, z - 1);
		setActive(x - 2, y - 2, z);
		setActive(x - 2, y - 2, z + 1);
		setActive(x - 2, y - 2, z + 2);
		setActive(x - 2, y - 1, z - 2);
		setActive(x - 2, y - 1, z - 1);
		setActive(x - 2, y - 1, z);
		setActive(x - 2, y - 1, z + 1);
		setActive(x - 2, y - 1, z + 2);
		setActive(x - 2, y, z - 2);
		setActive(x - 2, y, z - 1);
		setActive(x - 2, y, z);
		setActive(x - 2, y, z + 1);
		setActive(x - 2, y, z + 2);
		setActive(x - 2, y + 1, z - 2);
		setActive(x - 2, y + 1, z - 1);
		setActive(x - 2, y + 1, z);
		setActive(x - 2, y + 1, z + 1);
		setActive(x - 2, y + 1, z + 2);
		setActive(x - 2, y + 2, z - 2);
		setActive(x - 2, y + 2, z - 1);
		setActive(x - 2, y + 2, z);
		setActive(x - 2, y + 2, z + 1);
		setActive(x - 2, y + 2, z + 2);

		setActive(x - 1, y - 2, z - 2);
		setActive(x - 1, y - 2, z - 1);
		setActive(x - 1, y - 2, z);
		setActive(x - 1, y - 2, z + 1);
		setActive(x - 1, y - 2, z + 2);
		setActive(x - 1, y - 1, z - 2);
		setActive(x - 1, y - 1, z - 1);
		setActive(x - 1, y - 1, z);
		setActive(x - 1, y - 1, z + 1);
		setActive(x - 1, y - 1, z + 2);
		setActive(x - 1, y, z - 2);
		setActive(x - 1, y, z - 1);
		setActive(x - 1, y, z);
		setActive(x - 1, y, z + 1);
		setActive(x - 1, y, z + 2);
		setActive(x - 1, y + 1, z - 2);
		setActive(x - 1, y + 1, z - 1);
		setActive(x - 1, y + 1, z);
		setActive(x - 1, y + 1, z + 1);
		setActive(x - 1, y + 1, z + 2);
		setActive(x - 1, y + 2, z - 2);
		setActive(x - 1, y + 2, z - 1);
		setActive(x - 1, y + 2, z);
		setActive(x - 1, y + 2, z + 1);
		setActive(x - 1, y + 2, z + 2);

		setActive(x, y - 2, z - 2);
		setActive(x, y - 2, z - 1);
		setActive(x, y - 2, z);
		setActive(x, y - 2, z + 1);
		setActive(x, y - 2, z + 2);
		setActive(x, y - 1, z - 2);
		setActive(x, y - 1, z - 1);
		setActive(x, y - 1, z);
		setActive(x, y - 1, z + 1);
		setActive(x, y - 1, z + 2);
		setActive(x, y, z - 2);
		setActive(x, y, z - 1);
		setActive(x, y, z);
		setActive(x, y, z + 1);
		setActive(x, y, z + 2);
		setActive(x, y + 1, z - 2);
		setActive(x, y + 1, z - 1);
		setActive(x, y + 1, z);
		setActive(x, y + 1, z + 1);
		setActive(x, y + 1, z + 2);
		setActive(x, y + 2, z - 2);
		setActive(x, y + 2, z - 1);
		setActive(x, y + 2, z);
		setActive(x, y + 2, z + 1);
		setActive(x, y + 2, z + 2);

		setActive(x + 1, y - 2, z - 2);
		setActive(x + 1, y - 2, z - 1);
		setActive(x + 1, y - 2, z);
		setActive(x + 1, y - 2, z + 1);
		setActive(x + 1, y - 2, z + 2);
		setActive(x + 1, y - 1, z - 2);
		setActive(x + 1, y - 1, z - 1);
		setActive(x + 1, y - 1, z);
		setActive(x + 1, y - 1, z + 1);
		setActive(x + 1, y - 1, z + 2);
		setActive(x + 1, y, z - 2);
		setActive(x + 1, y, z - 1);
		setActive(x + 1, y, z);
		setActive(x + 1, y, z + 1);
		setActive(x + 1, y, z + 2);
		setActive(x + 1, y + 1, z - 2);
		setActive(x + 1, y + 1, z - 1);
		setActive(x + 1, y + 1, z);
		setActive(x + 1, y + 1, z + 1);
		setActive(x + 1, y + 1, z + 2);
		setActive(x + 1, y + 2, z - 2);
		setActive(x + 1, y + 2, z - 1);
		setActive(x + 1, y + 2, z);
		setActive(x + 1, y + 2, z + 1);
		setActive(x + 1, y + 2, z + 2);

		setActive(x + 2, y - 2, z - 2);
		setActive(x + 2, y - 2, z - 1);
		setActive(x + 2, y - 2, z);
		setActive(x + 2, y - 2, z + 1);
		setActive(x + 2, y - 2, z + 2);
		setActive(x + 2, y - 1, z - 2);
		setActive(x + 2, y - 1, z - 1);
		setActive(x + 2, y - 1, z);
		setActive(x + 2, y - 1, z + 1);
		setActive(x + 2, y - 1, z + 2);
		setActive(x + 2, y, z - 2);
		setActive(x + 2, y, z - 1);
		setActive(x + 2, y, z);
		setActive(x + 2, y, z + 1);
		setActive(x + 2, y, z + 2);
		setActive(x + 2, y + 1, z - 2);
		setActive(x + 2, y + 1, z - 1);
		setActive(x + 2, y + 1, z);
		setActive(x + 2, y + 1, z + 1);
		setActive(x + 2, y + 1, z + 2);
		setActive(x + 2, y + 2, z - 2);
		setActive(x + 2, y + 2, z - 1);
		setActive(x + 2, y + 2, z);
		setActive(x + 2, y + 2, z + 1);
		setActive(x + 2, y + 2, z + 2);
	}

	public void setActive() {
		for (Chunk chunk : chunks) {
			chunk.setActive(true);
		}
	}

	public void disable() {
		for (Chunk chunk : chunks) {
			chunk.setActive(false);
		}
	}

	public void guardar() {
		String text = "";
		for (Chunk chunk : chunks) {
			chunk.update();
			text += chunk.getText() + "#\n";
		}
		text.substring(0, text.length() - 2);
		try {
			Lector.createFile("res/mapa/mapa6X", text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
