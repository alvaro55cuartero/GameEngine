package graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import main.Camera;
import main.Const;
import models.CuboTexturedModel;
import models.PlaneTexturedModel;
import models.TexturedModel;
import objeto.Entity;
import shader.StaticShader;
import tools.FileUtils;

public class MasterRenderer {

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000000;

	private static Matrix4f projectionMatrix;

	private static StaticShader shader;
	private static Renderer renderer;

	public static final int TYPE_ORTHO = 0;
	public static final int TYPE_PERSPECTIVE = 1;

	public static final boolean TYPE_GUI = false;
	public static final boolean TYPE_3D_OBJECT = false;

	private static boolean init = false;

	private static List<TexturedModel> texturedModels = new ArrayList<TexturedModel>();
	private static List<Entity> entitiesGUI = new ArrayList<Entity>();
	private static Map<Integer, List<Entity>> entities3D = new HashMap<Integer, List<Entity>>();

	public static void init() {
		if (init == false) {

			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glCullFace(GL11.GL_BACK);
			MasterRenderer.shader = new StaticShader();
			projectionMatrix = new Matrix4f();
			texturedModels.add(new CuboTexturedModel("pointDebug"));
			changeView(TYPE_ORTHO);

			if (Const.debug) {
				init = true;
			}
		} else {
			System.out.println("error se inicio dos veces el master renderer");
		}
	}

	public static void tick() {

	}

	public static void render(Camera camera) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.46f, 0.77f, 0.98f, 1);
		shader.start();
		render3D(camera);
		renderGUI(camera);
		shader.stop();
		resetEntities();
	}

	private static void render3D(Camera camera) {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		shader.loadViewMatrix(camera);
		renderer.render(entities3D);
	}

	private static void renderGUI(Camera camera) {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		projectionMatrix.identity();
		shader.loadViewMatrix(new Matrix4f().identity());
		renderer.render(entitiesGUI);
	}

	public static void changeView(int type) {
		if (type == TYPE_ORTHO) {
			projectionMatrix.setOrtho(-Const.width / 200, Const.width / 200, -Const.height / 200, Const.height / 200,
					NEAR_PLANE, FAR_PLANE);

			// createOrthoProjectionMatrix();
		} else if (type == TYPE_PERSPECTIVE) {

			projectionMatrix.perspective(FOV, Const.aspectRatio, NEAR_PLANE, FAR_PLANE);
			// createProjectionMatrix();
		}
		renderer = new Renderer(shader, projectionMatrix);

	}

	// private static void createProjectionMatrix() {
	// float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) *
	// Const.aspectRatio);
	// float x_scale = y_scale / Const.aspectRatio;
	// float frustum_length = FAR_PLANE - NEAR_PLANE;
	//
	// projectionMatrix = new Matrix4f();
	// projectionMatrix.m00 = x_scale;
	// projectionMatrix.m11 = y_scale;
	// projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
	// projectionMatrix.m23 = -1;
	// projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
	// projectionMatrix.m33 = 0;
	// }
	//
	// private static void createOrthoProjectionMatrix() {
	// float r = Const.width / 200;
	// float l = -Const.width / 200;
	// float t = Const.height / 200;
	// float b = -Const.height / 200;
	// float f = FAR_PLANE;
	// float n = NEAR_PLANE;
	//
	// projectionMatrix = new Matrix4f();
	// projectionMatrix.m00 = 2 / (r - l);
	// projectionMatrix.m11 = 2 / (t - b);
	// projectionMatrix.m22 = -2 / (f - n);
	// projectionMatrix.m33 = 1;
	// projectionMatrix.m30 = -(r + +l) / (r - l);
	// projectionMatrix.m31 = -(t + b) / (t - b);
	// projectionMatrix.m32 = -(f + n) / (f - n);
	//
	// }

	public static void processTexturedModels(String ruta) {
		String txt = FileUtils.readTxt(ruta);
		txt = txt.replace("\n", "");
		String[] frases = txt.split(";");
		for (int i = 0; i < frases.length; i++) {
			texturedModels.add(new PlaneTexturedModel(frases[i]));
		}
	}

	public static void processEntity3D(Entity entity) {
		Integer id = entity.getTexturedModelId();
		List<Entity> batch = entities3D.get(id);
		if (batch == null) {
			batch = new ArrayList<Entity>();
		}
		batch.add(entity);
		entities3D.put(id, batch);
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
		shader.cleanUp();
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
}
