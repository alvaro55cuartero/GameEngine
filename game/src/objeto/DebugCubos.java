package objeto;

import org.joml.Vector3f;

import graphics.MasterRenderer;

public class DebugCubos {

	EntityCubo[] entities = new EntityCubo[5 * 5 * 5];

	public void rellenar() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					entities[k + (j * 5) + (i * 5 * 5)] = new EntityCubo(100, new Vector3f(i * 5, j * 5, k * 5), 0, 0,
							0, 1, 1, 1);
				}
			}
		}
	}

	public void render() {
		for (EntityCubo entity : entities) {
			MasterRenderer.processEntity3D(entity);
		}
	}
}
