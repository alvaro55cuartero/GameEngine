package main;

public class Const {

	public static final int width = 1920 / 2;
	public static final int height = 1080 / 2;

	public static final String tiltle = "Por determinar";

	public static final float aspectRatio = (float) width / (float) height;

	public static boolean debug = false;
	public static boolean debugState = false;

	public static final int anchoDepth = 3;
	public static final int altoDepth = 2;
	public static final int chunkSize = 16;
	public static final int CHUNKSPERSIDE = 3;

	public static final int anchoDepthInPixel = anchoDepth * chunkSize;
	public static final int altoDepthInPixel = altoDepth * chunkSize;

	public static final int ANCHOIMAGENInPixel = anchoDepthInPixel * CHUNKSPERSIDE;
	public static final int ALTOIMAGENInPixel = altoDepthInPixel * CHUNKSPERSIDE;

	public static final int sizeInPixels = ANCHOIMAGENInPixel * ALTOIMAGENInPixel;

}
