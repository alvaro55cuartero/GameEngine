package tools;

public class Batch {

	public String id;
	public String width;
	public String heigth;

	String entities = "";

	public Batch(String id, String width, String height) {
		this.id = id;
		this.width = width;
		this.heigth = height;
	}

	public String toString() {
		return "#" + id + ":" + width + ":" + heigth + ";" + entities;
	}

	public void add(float x, float y, float z) {
		entities = entities + "\n" + x + ":" + y + ":" + z + ";";
	}

}
