package audio;

import static org.lwjgl.BufferUtils.createByteBuffer;
import static org.lwjgl.openal.AL10.AL_FORMAT_MONO16;
import static org.lwjgl.openal.AL10.AL_FORMAT_STEREO16;
import static org.lwjgl.openal.AL10.AL_NO_ERROR;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alGetError;
import static org.lwjgl.openal.AL10.alGetString;
import static org.lwjgl.openal.ALC10.ALC_NO_ERROR;
import static org.lwjgl.openal.ALC10.alcCloseDevice;
import static org.lwjgl.openal.ALC10.alcCreateContext;
import static org.lwjgl.openal.ALC10.alcDestroyContext;
import static org.lwjgl.openal.ALC10.alcGetError;
import static org.lwjgl.openal.ALC10.alcGetString;
import static org.lwjgl.openal.ALC10.alcMakeContextCurrent;
import static org.lwjgl.openal.ALC10.alcOpenDevice;
import static org.lwjgl.openal.EXTThreadLocalContext.alcSetThreadContext;
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
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.stb.STBVorbisInfo;

public class AudioManager {

	private static List<Integer> buffers = new ArrayList<Integer>();

	static long device;
	static long context;

	STBVorbisInfo info;

	public AudioManager() {

	}

	public static void init() {

		device = alcOpenDevice((ByteBuffer) null);
		if (device == NULL) {
			throw new IllegalStateException("Failed to open the default device.");
		}

		ALCCapabilities deviceCaps = ALC.createCapabilities(device);

		if (!deviceCaps.OpenALC10) {
			throw new IllegalStateException();
		}

		context = alcCreateContext(device, (IntBuffer) null);
		alcSetThreadContext(context);
		AL.createCapabilities(deviceCaps);
		AudioUtils.alError();
	}

	public static void setListenerData() {
		AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}

	public static int LoadSound(String ruta) {

		int buffer = AL10.alGenBuffers();
		AudioUtils.alError();

		buffers.add(buffer);

		try (STBVorbisInfo info = STBVorbisInfo.malloc()) {
			ShortBuffer pcm = readVorbis(ruta, 32 * 1024, info);
			alBufferData(buffer, info.channels() == 1 ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16, pcm, info.sample_rate());
		}

		return buffer;
	}

	public static void cleanUp() {
		for (int buffer : buffers) {
			AL10.alDeleteBuffers(buffer);
		}

		alcMakeContextCurrent(NULL);
		alcDestroyContext(context);
		alcCloseDevice(device);
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
