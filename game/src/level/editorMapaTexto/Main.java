package level.editorMapaTexto;

import java.io.IOException;
import java.util.ArrayList;

import tools.Lector;

public class Main {

	public ArrayList<Chunk> chunks = new ArrayList<Chunk>();

	public static void main(String[] asd) {
		Main main = new Main();

		String txt;

		try {
			txt = Lector.leerArchivoTexto("res/mapa/mapa5X").replaceAll("\n", "");
			String[] c = txt.split("#");
			for (String chunk : c) {
				main.chunks.add(new Chunk(chunk));
			}
			for (Chunk chunk : main.chunks) {
				ArrayList<Ent> ents = chunk.entsOut();
				ArrayList<EntCol> entsCol = chunk.entsColOut();
				for (EntCol col : entsCol) {
					main.insertCol(col);
				}
			}

			Lector.createFile("res/mapa/mapa6X", main.toString());

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void insertCol(EntCol col) {
		for (Chunk chunk : chunks) {
			if (Math.floor(col.position.x / 16) == chunk.x) {
				if (Math.floor(col.position.y / 16) == chunk.y) {
					if (Math.floor(col.position.z / 16) == chunk.z) {
						chunk.cols.add(col);
					}
				}
			}
		}
	}

	public String toString() {
		String txt = "";
		for (Chunk chunk : chunks) {
			txt += chunk.toString() + "#";
		}
		return txt;
	}

	public static void refactor() {
		String txt;
		String r = "";
		try {
			txt = Lector.leerArchivoTexto("res/mapaXXX").replaceAll("\n", "");
			String[] chunks = txt.split("#");
			for (String chunk : chunks) {
				String[] partes = chunk.split("\\|");
				r += "#" + partes[0] + "|\n";
				if (partes.length > 1) {
					String[] ent = partes[1].split(";");
					for (int j = 0; j < ent.length; j++) {
						String[] args = ent[j].split(":");
						r += args[0] + ":" + args[1] + ":" + args[2] + ":" + args[3] + ":0.0:0.0:0.0:" + args[4] + ":"
								+ args[5] + ":" + args[6] + ";\n";
					}
				}
			}
			Lector.createFile("res/mapa4X", r);
			System.out.println("yas");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static String generador(int a) {
		String txt = "";
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < a; j++) {
				for (int k = 0; k < a; k++) {
					txt += i + ":" + j + ":" + k + "|\n#\n";
				}
			}
		}
		return txt;
	}

}
