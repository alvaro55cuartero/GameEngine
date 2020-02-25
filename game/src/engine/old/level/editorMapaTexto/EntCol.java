package engine.old.level.editorMapaTexto;

import org.joml.Vector3f;

public class EntCol {

	private String txt;
	private String type;
	private Vector3f pos;
	private Vector3f dim;

	public EntCol(String txt) {
		this.txt = txt;
		String[] args = txt.split(":");
		// type = args[0];
		// pos = new Vector3f(Float.parseFloat(args[1]), Float.parseFloat(args[2]),
		// Float.parseFloat(args[3]));
		// dim = new Vector3f(Float.parseFloat(args[4]), Float.parseFloat(args[5]),
		// Float.parseFloat(args[6]));

		setPos(new Vector3f(Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2])));
		dim = new Vector3f(Float.parseFloat(args[3]), Float.parseFloat(args[4]), Float.parseFloat(args[5]));
	}

	public String toString() {
		if (dim.x > dim.y) {
			type = "Y";
		} else {
			type = "X";
		}
		return type + ":" + getPos().x + ":" + getPos().y + ":" + getPos().z + ":" + dim.x + ":" + dim.y + ":" + dim.z
				+ ";\n";
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}
}
