package audio;

import org.lwjgl.openal.AL10;

public class AudioUtils {

	public static void alError() {
		int error = AL10.alGetError();
		String errorMsg = "";
		switch (error) {
		case AL10.AL_NO_ERROR:
			errorMsg = "no error";
			break;
		case AL10.AL_INVALID_ENUM:
			errorMsg = "Invalid enum";
			break;
		case AL10.AL_INVALID_NAME:
			errorMsg = "invalid name";
			break;
		case AL10.AL_INVALID_OPERATION:
			errorMsg = "invalid operation";
			break;
		case AL10.AL_INVALID_VALUE:
			errorMsg = "invalid value";
			break;
		case AL10.AL_OUT_OF_MEMORY:
			errorMsg = "out of memory";
			break;

		}
		System.out.println("Error: " + errorMsg);

	}
}
