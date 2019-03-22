package level.editorMapaTexto;

import org.joml.Vector3f;

public class EntCol {

	String txt;
	Vector3f position;

	public EntCol(String txt) {
		this.txt = txt;
		String[] args = txt.split(":");
		position = new Vector3f(Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]));
	}

	public String toString() {
		return txt;
	}
}
