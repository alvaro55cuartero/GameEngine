package engine.old.audio;

import org.lwjgl.openal.ALC;

public class MasterAudio {

	public static void init() {
		try {
			ALC.create();

		} catch (Exception e) {

		}
	}

}
