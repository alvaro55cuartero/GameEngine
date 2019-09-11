package engine.audio;

import static org.lwjgl.BufferUtils.createByteBuffer;
import static org.lwjgl.openal.AL10.AL_BUFFER;
import static org.lwjgl.openal.AL10.AL_FORMAT_MONO16;
import static org.lwjgl.openal.AL10.AL_FORMAT_STEREO16;
import static org.lwjgl.openal.AL10.AL_NO_ERROR;
import static org.lwjgl.openal.AL10.AL_SOURCE_STATE;
import static org.lwjgl.openal.AL10.AL_STOPPED;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alDeleteBuffers;
import static org.lwjgl.openal.AL10.alDeleteSources;
import static org.lwjgl.openal.AL10.alGenBuffers;
import static org.lwjgl.openal.AL10.alGenSources;
import static org.lwjgl.openal.AL10.alGetError;
import static org.lwjgl.openal.AL10.alGetSourcei;
import static org.lwjgl.openal.AL10.alGetString;
import static org.lwjgl.openal.AL10.alSourcePlay;
import static org.lwjgl.openal.AL10.alSourceStop;
import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.openal.ALC10.ALC_NO_ERROR;
import static org.lwjgl.openal.ALC10.alcGetError;
import static org.lwjgl.openal.ALC10.alcGetString;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_close;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_get_info;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_get_samples_short_interleaved;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_open_memory;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_stream_length_in_samples;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBVorbisInfo;

public class Test {

	public static void main(String args[]) throws IOException, InterruptedException {
		AudioManager.init();
		AudioManager.setListenerData(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

		float x = 1000f;

		int buffer = AudioManager.LoadSound("res/audio/CYCdh_TrashA01.ogg");
		Source source = new Source();
		source.setLooping(true);
		source.play(buffer);
		source.setPosition(x, 0, 0);

		while (source.isPlaying()) {
			System.out.println(x);
			x -= 1f;
			source.setPosition(x, 0, 0);

			Thread.sleep(10);
			if (x < -1000) {
				break;
			}
		}
		source.delete();
		AudioManager.cleanUp();

		// long device = alcOpenDevice((ByteBuffer) null);
		// if (device == NULL) {
		// throw new IllegalStateException("Failed to open the default device.");
		// }
		//
		// ALCCapabilities deviceCaps = ALC.createCapabilities(device);
		//
		// if (!deviceCaps.OpenALC10) {
		// throw new IllegalStateException();
		// }
		//
		// System.out.println("OpenALC10: " + deviceCaps.OpenALC10);
		// System.out.println("OpenALC11: " + deviceCaps.OpenALC11);
		// System.out.println("caps.ALC_EXT_EFX = " + deviceCaps.ALC_EXT_EFX);
		//
		// if (deviceCaps.OpenALC11) {
		// List<String> devices = ALUtil.getStringList(NULL, ALC_ALL_DEVICES_SPECIFIER);
		// if (devices == null) {
		//
		// } else {
		// for (int i = 0; i < devices.size(); i++) {
		// System.out.println(i + ": " + devices.get(i));
		// }
		// }
		// }
		//
		// String defaultDeviceSpecifier = Objects.requireNonNull(alcGetString(NULL,
		// ALC_DEFAULT_DEVICE_SPECIFIER));
		// System.out.println("Default device: " + defaultDeviceSpecifier);
		//
		// long context = alcCreateContext(device, (IntBuffer) null);
		// alcSetThreadContext(context);
		// AL.createCapabilities(deviceCaps);
		//
		// System.out.println("ALC_FREQUENCY: " + alcGetInteger(device, ALC_FREQUENCY) +
		// "Hz");
		// System.out.println("ALC_REFRESH: " + alcGetInteger(device, ALC_REFRESH) +
		// "Hz");
		// System.out.println("ALC_SYNC: " + (alcGetInteger(device, ALC_SYNC) ==
		// ALC_TRUE));
		// System.out.println("ALC_MONO_SOURCES: " + alcGetInteger(device,
		// ALC_MONO_SOURCES));
		// System.out.println("ALC_STEREO_SOURCES: " + alcGetInteger(device,
		// ALC_STEREO_SOURCES));
		//
		// try {
		// testPlayback();
		// } finally {
		// alcMakeContextCurrent(NULL);
		// alcDestroyContext(context);
		// alcCloseDevice(device);
		// }

	}

	private static void testPlayback() {
		// generate buffers and sources
		int buffer = alGenBuffers();
		checkALError();

		int source = alGenSources();
		checkALError();

		try (STBVorbisInfo info = STBVorbisInfo.malloc()) {
			ShortBuffer pcm = readVorbis("res/audio/test.ogg", 32 * 1024, info);

			// copy to buffer
			alBufferData(buffer, info.channels() == 1 ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16, pcm, info.sample_rate());
			checkALError();
		}

		// set up source input
		alSourcei(source, AL_BUFFER, buffer);
		checkALError();

		// play source
		alSourcePlay(source);
		checkALError();

		// wait
		System.out.println("Waiting for sound to complete...");
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {
				break;
			}
			if (alGetSourcei(source, AL_SOURCE_STATE) == AL_STOPPED) {
				break;
			}
			System.out.println(".");
		}

		// stop source 0
		alSourceStop(source);
		checkALError();

		// delete buffers and sources
		alDeleteSources(source);
		checkALError();

		alDeleteBuffers(buffer);
		checkALError();
	}

	static ShortBuffer readVorbis(String resource, int bufferSize, STBVorbisInfo info) {
		ByteBuffer vorbis;
		try {
			vorbis = ioResourceToByteBuffer(resource, bufferSize);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		IntBuffer error = BufferUtils.createIntBuffer(1);
		long decoder = stb_vorbis_open_memory(vorbis, error, null);
		if (decoder == NULL) {
			throw new RuntimeException("Failed to open Ogg Vorbis file. Error: " + error.get(0));
		}

		stb_vorbis_get_info(decoder, info);

		int channels = info.channels();

		ShortBuffer pcm = BufferUtils.createShortBuffer(stb_vorbis_stream_length_in_samples(decoder) * channels);

		stb_vorbis_get_samples_short_interleaved(decoder, channels, pcm);
		stb_vorbis_close(decoder);

		return pcm;
	}

	public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
		ByteBuffer buffer;

		Path path = Paths.get(resource);
		if (Files.isReadable(path)) {
			try (SeekableByteChannel fc = Files.newByteChannel(path)) {
				buffer = BufferUtils.createByteBuffer((int) fc.size() + 1);
				while (fc.read(buffer) != -1) {
					;
				}
			}
		} else {
			try (InputStream source = Test.class.getClassLoader().getResourceAsStream(resource);
					ReadableByteChannel rbc = Channels.newChannel(source)) {
				buffer = createByteBuffer(bufferSize);

				while (true) {
					int bytes = rbc.read(buffer);
					if (bytes == -1) {
						break;
					}
					if (buffer.remaining() == 0) {
						buffer = resizeBuffer(buffer, buffer.capacity() * 3 / 2); // 50%
					}
				}
			}
		}

		buffer.flip();
		return buffer.slice();
	}

	private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
		ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
		buffer.flip();
		newBuffer.put(buffer);
		return newBuffer;
	}

	static void checkALCError(long device) {
		int err = alcGetError(device);
		if (err != ALC_NO_ERROR) {
			throw new RuntimeException(alcGetString(device, err));
		}
	}

	static void checkALError() {
		int err = alGetError();
		if (err != AL_NO_ERROR) {
			throw new RuntimeException(alGetString(err));
		}
	}
}
