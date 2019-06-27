package layers;

import java.util.ArrayList;

public class LayerStack {

	public ArrayList<Layer> layers = new ArrayList<Layer>();
	private int overlayCount = 0;

	public void pushLayer(Layer layer) {
		layers.add(overlayCount, layer);
		overlayCount++;
	}

	public void pushOverlay(Layer layer) {
		layers.add(layer);
	}

	public void popLayer() {
		layers.get(overlayCount);
		layers.remove(overlayCount);
		overlayCount--;
	}

	public void popOverlay() {
		layers.get(layers.size());
		layers.remove(layers.size());
	}

	public static void main(String args[]) {
		Layer layer = new tesl("hola");
		Layer ol = new tesl("ol");
		LayerStack ls = new LayerStack();
		ls.pushOverlay(ol);
		ls.pushLayer(layer);

	}

}
