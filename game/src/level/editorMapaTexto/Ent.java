package level.editorMapaTexto;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Ent {

	String txt;

	private int id;
	private Vector3f pos;
	private Vector3f rot;
	private Vector2f dim;

	public Ent(String txt) {
		this.txt = txt;

		String[] args = txt.split(":");
		id = Integer.parseInt(args[0]);
		setPos(new Vector3f(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3])));
		rot = new Vector3f(Float.parseFloat(args[4]), Float.parseFloat(args[5]), Float.parseFloat(args[6]));
		dim = new Vector2f(Float.parseFloat(args[7]), Float.parseFloat(args[8]));
	}

	public String toString() {
		if (rot.x >= 360) {
			rot.x %= 360;
		}
		if (rot.y >= 360) {
			rot.y %= 360;
		}
		if (rot.z >= 360) {
			rot.z %= 360;
		}

		return id + ":" + pos.x + ":" + pos.y + ":" + pos.z + ":" + rot.x + ":" + rot.y + ":" + rot.z + ":" + dim.x
				+ ":" + dim.y + ";\n";
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

}
