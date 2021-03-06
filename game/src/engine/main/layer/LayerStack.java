package engine.main.layer;

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
		overlayCount--;
		layers.remove(overlayCount);

	}

	public void popOverlay() {
		layers.get(layers.size());
		layers.remove(layers.size());
	}

	public Layer getLayer(int index) {
		return layers.get(index);
	}

	public int size() {
		return layers.size();
	}
}
