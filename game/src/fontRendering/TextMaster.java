package fontRendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontMeshCreator.TextMeshData;
import graphics.Loader;

public class TextMaster {
	private static Map<FontType, List<GUIText>> texts = new HashMap<FontType, List<GUIText>>();
	private static FontRenderer fontRenderer;

	public static void init() {
		TextMaster.fontRenderer = new FontRenderer();
	}

	public static void render() {
		fontRenderer.render(texts);
	}

	public static void loadText(GUIText text) {
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = Loader.loadToVao(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<GUIText> textBatch = texts.get(font);
		if (textBatch == null) {
			textBatch = new ArrayList<GUIText>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}

	public static void removeText(GUIText text) {
		List<GUIText> textBatch = texts.get(text.getFont());
		textBatch.remove(text);
		if (textBatch.isEmpty()) {
			texts.remove(text.getFont());
		}
	}

	public static void cleanUp() {
		fontRenderer.cleanUp();
	}
}
