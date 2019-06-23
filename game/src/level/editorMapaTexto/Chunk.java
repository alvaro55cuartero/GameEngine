package level.editorMapaTexto;

import java.util.ArrayList;

import org.joml.Vector3f;

public class Chunk {

	String txt;
	int x;
	int y;
	int z;

	ArrayList<Ent> ents = new ArrayList<Ent>();
	ArrayList<EntCol> cols = new ArrayList<EntCol>();

	public Chunk(String txt) {
		this.txt = txt;
		String[] partes = txt.split("\\|");
		String[] args = partes[0].split(":");
		x = Integer.parseInt(args[0]);
		y = Integer.parseInt(args[1]);
		z = Integer.parseInt(args[2]);

		if (partes.length > 1) {
			String[] ent = partes[1].split(";");
			for (int i = 0; i < ent.length; i++) {
				this.ents.add(new Ent(ent[i]));
			}
		}
		if (partes.length > 2) {
			String[] entCol = partes[2].split(";");
			for (int i = 0; i < entCol.length; i++) {
				this.cols.add(new EntCol(entCol[i]));
			}
		}
	}

	public ArrayList<Ent> entsOut() {
		ArrayList<Ent> r = new ArrayList<Ent>();
		if (ents != null) {
			for (Ent ent : ents) {
				Vector3f pos = ent.getPos();
				if (Math.floor(pos.x / 16) != x || Math.floor(pos.y / 16) != y || Math.floor(pos.z / 16) != z) {
					r.add(ent);
					r.remove(ent);
				}
			}
		}
		return r;
	}

	public ArrayList<EntCol> entsColOut() {
		ArrayList<EntCol> r = new ArrayList<EntCol>();
		if (cols != null) {
			for (EntCol entCol : cols) {
				Vector3f pos = entCol.getPos();
				if (Math.floor(pos.x / 16) != x || Math.floor(pos.y / 16) != y || Math.floor(pos.z / 16) != z) {
					r.add(entCol);
				}
			}
		}
		cols.removeAll(r);
		return r;
	}

	public String toString() {
		String txt = "";
		txt = x + ":" + y + ":" + z + "|\n";
		for (Ent ent : ents) {
			txt += ent.toString();
		}
		txt += "|\n";
		for (EntCol col : cols) {
			txt += col.toString();
		}
		return txt;

	}

}
