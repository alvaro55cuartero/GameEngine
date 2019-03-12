package graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import fontMeshCreator.FontType;
import main.Camera;
import main.Const;
import models.PlaneTexturedModel;
import models.RawModel;
import models.TexturedModel;
import objeto.Entity;
import objeto.Entity3D;
import shader.Shader;
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

	public static float zoom = 1;

	// public static final boolean TYPE_GUI = false;
	// public static final boolean TYPE_3D_OBJECT = false;

	private static boolean init = false;

	private static List<TexturedModel> texturedModels = new ArrayList<TexturedModel>();
	// private static List<TexturedModelGUI> texturedModelsGUI = new
	// ArrayList<TexturedModel>();
	private static List<FontType> fonts = new ArrayList<FontType>();

	private static Map<Integer, List<Entity>> entitiesGUI = new HashMap<Integer, List<Entity>>();
	private static Map<Integer, List<Entity>> entities3D = new HashMap<Integer, List<Entity>>();

	public static void init() {

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		shader3D = new StaticShader();
		shaderGUI = new ShaderGUI();
		projectionMatrix = new Matrix4f().identity();
	}

	public static void tick() {
		projectionMatrix = projectionMatrixOrtho();

	}

	public static void render(Camera camera) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.46f, 0.77f, 0.98f, 1);
		render3D(camera);
		renderGUI();
		resetEntities();
	}

	private static void render3D(Camera camera) {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		shader3D.start();
		shader3D.loadProjectionMatrix(projectionMatrix);
		shader3D.loadViewMatrix(camera);
		render(entities3D, shader3D);
		shader3D.stop();
	}

	private static void renderGUI() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		shaderGUI.start();
		shaderGUI.loadTextBoolean(false);
		for (Integer id : entitiesGUI.keySet()) {
			TexturedModel model = MasterRenderer.getTexturedModel(id);
			prepareTexturedModel(model);
			List<Entity> batch = entitiesGUI.get(id);
			if (batch != null) {
				if (batch.get(0).getType().matches("GUI")) {
					for (Entity entity : batch) {
						prepareInstance(entity, shaderGUI);
						GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
					}
				} else if (batch.get(0).getType().matches("TextGUI")) {
					for (Entity entity : batch) {
						GL11.glEnable(GL11.GL_BLEND);
						GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
						prepareInstance(entity, shaderGUI);
						shaderGUI.loadTextColour(new Vector3f(0, 1, 0));
						GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
						GL11.glDisable(GL11.GL_BLEND);
					}
				}
			}
			unbindTexturedModel(3);
		}
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
		if (entity != null) {
			Integer id = entity.getTexturedModelId();
			List<Entity> batch = entitiesGUI.get(id);
			if (batch == null) {
				batch = new ArrayList<Entity>();
			}
			batch.add(entity);
			entitiesGUI.put(id, batch);
		}
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
		return new Matrix4f().setOrtho(-Const.width * zoom / 200, Const.width * zoom / 200, -Const.height * zoom / 200,
				Const.height * zoom / 200, NEAR_PLANE, FAR_PLANE);
	}

	public static Matrix4f projectionMatrixPerspective() {
		return new Matrix4f().perspective(FOV, Const.aspectRatio, NEAR_PLANE, FAR_PLANE);
	}

	public static Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public static void render(Map<Integer, List<Entity>> entities3d, Shader shader) {
		for (Integer id : entities3d.keySet()) {
			TexturedModel model = MasterRenderer.getTexturedModel(id);
			prepareTexturedModel(model);
			List<Entity> batch = entities3d.get(id);
			if (batch != null) {
				for (Entity entity : batch) {
					prepareInstance(entity, shader);
					GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
				}
			}
			unbindTexturedModel(3);
		}
	}

	public static void render(List<Entity> entities, Shader shader) {
		for (Entity entity : entities) {
			TexturedModel model = MasterRenderer.getTexturedModel(entity.getTexturedModelId());
			prepareTexturedModel(model);
			prepareInstance(entity, shader);
			GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
			unbindTexturedModel(3);
		}
	}

	public static void render(List<Entity> entities, ShaderGUI shader) {
		for (Entity entity : entities) {
			TexturedModel model = MasterRenderer.getTexturedModel(entity.getTexturedModelId());
			RawModel rawModel = model.getRawModel();
			GL30.glBindVertexArray(rawModel.getId());
			bindVertexArrays(2);
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getModelTexture().getTextureID());
			prepareInstance(entity, shader);
			GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getCount(), GL11.GL_UNSIGNED_INT, 0);
			unbindTexturedModel(2);
		}
	}

	private static void prepareTexturedModel(TexturedModel model) {
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getModelTexture().getTextureID());
	}

	private static void bindVertexArrays(int num) {
		for (int i = 0; i < num; i++) {
			GL20.glEnableVertexAttribArray(i);
		}
	}

	private static void unbindTexturedModel(int num) {
		for (int i = 0; i < num; i++) {
			GL20.glDisableVertexAttribArray(i);
		}
		GL30.glBindVertexArray(0);
	}

	private static void prepareInstance(Entity entity, Shader shader) {
		if (shader instanceof StaticShader) {
			Matrix4f transformationMatrix = new Matrix4f();
			transformationMatrix.identity().translate(entity.getPosition())
					.rotateX((float) Math.toRadians(entity.getRx())).rotateY((float) Math.toRadians(entity.getRy()))
					.rotateZ((float) Math.toRadians(entity.getRz()))
					.scale(entity.getSx(), entity.getSy(), entity.getSz());
			((StaticShader) shader).loadTransformationMatrix(transformationMatrix);
		} else if (shader instanceof ShaderGUI) {
			Matrix4f transformationMatrix = new Matrix4f();
			transformationMatrix.identity()
					.translate(entity.getPosition().x * 2 / Const.width - 1,
							entity.getPosition().y * 2 / Const.height - 1, entity.getPosition().z)
					.rotateX((float) Math.toRadians(entity.getRx())).rotateY((float) Math.toRadians(entity.getRy()))
					.rotateZ((float) Math.toRadians(entity.getRz()))
					.scale(entity.getSx() * 2 / Const.width, entity.getSy() * 2 / Const.height, entity.getSz());
			((ShaderGUI) shader).loadTransformationMatrix(transformationMatrix);
		}
	}

	public static List<TexturedModel> getTexturedModels() {
		return texturedModels;
	}

	public static void setTexturedModels(List<TexturedModel> texturedModels) {
		MasterRenderer.texturedModels = texturedModels;
	}

}
