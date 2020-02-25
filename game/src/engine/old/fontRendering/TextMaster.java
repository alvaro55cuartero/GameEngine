package engine.old.fontRendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.old.fontMeshCreator.EntityGUIText;
import engine.old.fontMeshCreator.FontType;
import engine.old.fontMeshCreator.TextMeshData;
import engine.old.graphics.old.Loader;

public class TextMaster {
	private static Map<FontType, List<EntityGUIText>> texts = new HashMap<FontType, List<EntityGUIText>>();
	private static FontRenderer fontRenderer;

	public static void init() {
		TextMaster.fontRenderer = new FontRenderer();
	}

	public static void render() {
		fontRenderer.render(texts);
	}

	public static void loadText(EntityGUIText text) {
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = Loader.loadToVao(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<EntityGUIText> textBatch = texts.get(font);
		if (textBatch == null) {
			textBatch = new ArrayList<EntityGUIText>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}

	public static void removeText(EntityGUIText text) {
		List<EntityGUIText> textBatch = texts.get(text.getFont());
		textBatch.remove(text);
		if (textBatch.isEmpty()) {
			texts.remove(text.getFont());
		}
	}

	public static void cleanUp() {
		fontRenderer.cleanUp();
	}
}
