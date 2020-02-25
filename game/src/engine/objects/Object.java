package engine.objects;

import java.util.ArrayList;

public class Object {

	private String name;
	private ArrayList<Component> components;

	public Object(String name, ArrayList<Component> components) {
		this.name = name;
		this.components = components;
	}

	public void addComponent(Component component) {
		components.add(component);
	}

	public void remove(int index) {
		components.remove(index);
	}

}
