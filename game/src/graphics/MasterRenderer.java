package graphics;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import main.Const;
import objeto.Camera;
import objeto.entities.Entity;
import objeto.entities.Entity3D;
import objeto.light.AmbientLight;
import tools.FileUtils;

public class MasterRenderer {

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000000;

	public static float zoom = 1;

	private static Renderer3D renderer3D;
	private static RendererDebug rendererDebug;
	private static RendererGUI rendererGUI;
	private static AmbientLight light = new AmbientLight(new Vector3f(), new Vector3f(10, 10, 2.5f));

	public static void init() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);

		renderer3D = new Renderer3D();
		rendererDebug = new RendererDebug();
		rendererGUI = new RendererGUI();
	}

	public static void render(Camera camera) {
		light.render();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0f, 0f, 0f, 1);
		renderer3D.render(camera, light);
		rendererDebug.render(camera);
		rendererGUI.render();

		resetEntities();
	}

	public static void processTexturedModels(String ruta) {
		String[] partes = FileUtils.readTxt(ruta).replace("\n", "").split("#");
		renderer3D.processTexturedModels(partes[0]);
		rendererGUI.processTexturedModels(partes[2]);
	}

	public static void processEntity3D(Entity entity) {
		renderer3D.processEntity(entity);
	}

	public static void processEntities3D(List<Entity> entities) {
		for (Entity entity : entities) {
			processEntity3D(entity);
		}
	}

	public static void processEntityDebug(Entity entity) {
		rendererDebug.processEntity(entity);
	}

	public static void processEntitiesDebug(List<Entity> entities) {
		for (Entity entity : entities) {
			processEntityDebug(entity);
		}
	}

	public static void processEntities3D(ArrayList<Entity3D> entities) {
		for (Entity3D entity : entities) {
			processEntity3D((Entity3D) entity);
		}
	}

	public static void processEntities3D(Entity[] entities) {
		for (Entity entity : entities) {
			processEntity3D(entity);
		}
	}

	public static void processEntityGUI(Entity entity) {
		rendererGUI.processEntityGUI(entity);
	}

	public static void processEntitiesGUI(List<Entity> entities) {
		for (Entity entity : entities) {
			processEntityGUI(entity);
		}
	}

	private static void resetEntities() {
		renderer3D.reset();
		rendererDebug.reset();
		rendererGUI.reset();

	}

	public static void resetTexturedModels() {
		renderer3D.resetTexturedModels();
	}

	public static void cleanUp() {
		renderer3D.clear();

	}

	public static Matrix4f projectionMatrixOrtho() {
		return new Matrix4f().setOrtho(-Const.width * zoom / 200, Const.width * zoom / 200, -Const.height * zoom / 200,
				Const.height * zoom / 200, NEAR_PLANE, FAR_PLANE);
	}

	public static Matrix4f projectionMatrixPerspective() {
		return new Matrix4f().perspective(FOV, Const.aspectRatio, NEAR_PLANE, FAR_PLANE);
	}

	public static Renderer3D getRenderer3D() {
		return renderer3D;
	}

	public static RendererDebug getRendererDebug() {
		return rendererDebug;
	}

	public static RendererGUI getRendererGUI() {
		return rendererGUI;
	}

}
