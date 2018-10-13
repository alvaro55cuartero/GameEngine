package audio;

import static org.lwjgl.openal.AL10.AL_SOURCE_STATE;
import static org.lwjgl.openal.AL10.AL_STOPPED;

import org.lwjgl.openal.AL10;

public class Source {
	private int sourceId;

	public Source() {
		sourceId = AL10.alGenSources();
		AL10.alSourcef(sourceId, AL10.AL_GAIN, 1);
		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1);
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
	}

	public void play(int buffer) {
		AL10.alSourcei(sourceId, AL10.AL_BUFFER, buffer);
		AL10.alSourcePlay(sourceId);
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {
				break;
			}
			if (AL10.alGetSourcei(sourceId, AL_SOURCE_STATE) == AL_STOPPED) {
				break;
			}
			System.out.println(".");
		}
	}

	public void delete() {
		AL10.alDeleteSources(sourceId);
	}
}
