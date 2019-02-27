package graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import main.Camera;
import main.Const;
import models.PlaneTexturedModel;
import models.TexturedModel;
import objeto.Entity;
import shader.shader3D.StaticShader;
import shader.shaderGUI.ShaderGUI;
import tools.FileUtils;

public class MasterRenderer {

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000000;

	private static Matrix4f projectionMatrix;

	private static StaticShader shader3D;
	private static ShaderGUI shaderGUI;

	public static final int TYPE_ORTHO = 0;
	public static final int TYPE_PERSPECTIVE = 1;

	public static final boolean TYPE_GUI = false;
	public static final boolean TYPE_3D_OBJECT = false;

	private static boolean init = false;

	private static List<TexturedModel> texturedModels = new ArrayList<TexturedModel>();
	private static List<Entity> entitiesGUI = new ArrayList<Entity>();
	private static Map<Integer, List<Entity>> entities3D = new HashMap<Integer, List<Entity>>();

	public static void init() {

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		shader3D = new StaticShader();
		shaderGUI = new ShaderGUI();
		projectionMatrix = new Matrix4f().identity();
	}

	public static void tick() {

	}

	public static void render(Camera camera) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.46f, 0.77f, 0.98f, 1);
		render3D(camera);
		renderGUI();
		resetEntities();
	}

	private static void render3D(Camera camera) {
		shader3D.start();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		shader3D.loadProjectionMatrix(projectionMatrix);
		shader3D.loadViewMatrix(camera);
		Renderer.render(entities3D, shader3D);
		shader3D.stop();
	}

	private static void renderGUI() {
		shaderGUI.start();
		shaderGUI.loadTextBoolean(false);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		Renderer.render(entitiesGUI, shaderGUI);
		shaderGUI.stop();
	}

	private static void renderTextGUI() {

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void changeView(int type) {
		if (type == TYPE_ORTHO) {
			projectionMatrix = projectionMatrixOrtho();
		} else if (type == TYPE_PERSPECTIVE) {
			projectionMatrix = projectionMatrixPerspective();
		}

	}

	public static void processTexturedModels(String ruta) {
		String txt = FileUtils.readTxt(ruta);
		txt = txt.replace("\n", "");
		String[] frases = txt.split(";");
		for (int i = 0; i < frases.length; i++) {
			texturedModels.add(new PlaneTexturedModel(frases[i]));
		}
	}

	public static void processTexturedModel(TexturedModel texturedModel) {
		texturedModels.add(texturedModel);
	}

	public static void processEntity3D(Entity entity) {
		if (entity != null) {
			Integer id = entity.getTexturedModelId();
			List<Entity> batch = entities3D.get(id);
			if (batch == null) {
				batch = new ArrayList<Entity>();
			}
			batch.add(entity);
			entities3D.put(id, batch);
		}
	}

	public static void processEntities3D(List<Entity> entities) {
		for (Entity entity : entities) {
			processEntity3D(entity);
		}
	}

	public static void processEntityGUI(Entity entity) {
		entitiesGUI.add(entity);
	}

	public static void processEntitiesGUI(List<Entity> entities) {
		for (Entity entity : entities) {
			processEntityGUI(entity);
		}
	}

	public static TexturedModel getTexturedModel(int id) {
		TexturedModel texturedModel = texturedModels.get(id);
		if (texturedModel != null) {
			return texturedModel;
		}
		return getTexturedModel(0);
	}

	public static Map<Integer, List<Entity>> getEntities3D() {
		return entities3D;
	}

	private static void resetEntities() {
		entitiesGUI.clear();
		entities3D.clear();
	}

	public static void resetTexturedModels() {
		texturedModels.clear();
	}

	public static void cleanUp() {
		texturedModels.clear();
		entitiesGUI.clear();
		entities3D.clear();
		shader3D.cleanUp();
		shaderGUI.cleanUp();
	}

	public static int findId(String ruta) {
		for (int i = 0; i < texturedModels.size(); i++) {
			if (texturedModels.get(i).getName().matches(ruta)) {
				return i;
			}
		}
		texturedModels.add(new PlaneTexturedModel(ruta));
		return texturedModels.size();
	}

	public static Matrix4f projectionMatrixOrtho() {
		return new Matrix4f().setOrtho(-Const.width / 200, Const.width / 200, -Const.height / 200, Const.height / 200,
				NEAR_PLANE, FAR_PLANE);
	}

	public static Matrix4f projectionMatrixPerspective() {
		return new Matrix4f().perspective(FOV, Const.aspectRatio, NEAR_PLANE, FAR_PLANE);
	}

	public static Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

}
