package models;

public class RawModel {

	protected int id;
	protected int count;

	public RawModel() {

	}

	public RawModel(int id, int count) {
		this.id = id;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

}
