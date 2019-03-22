package level.editorMapaTexto;

import org.joml.Vector3f;

public class Ent {

	String txt;

	Vector3f position;

	public Ent(String txt) {
		this.txt = txt;

		String[] args = txt.split(":");
		position = new Vector3f(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]));
	}

	public String toString() {
		return txt;
	}

}
