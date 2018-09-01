package org.digitalsnake.cubz.main;

import org.digitalsnake.cubz.utils.DebugLogger;
import org.digitalsnake.cubz.world.World;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.jungle.Camera;
import org.jungle.Mesh;
import org.jungle.MouseInput;
import org.jungle.Spatial;
import org.jungle.Texture;
import org.jungle.Window;
import org.jungle.game.Context;
import org.jungle.game.Game;
import org.jungle.game.IGameLogic;
import org.jungle.renderers.IRenderer;
import org.jungle.renderers.JungleRender;
import org.jungle.util.DirectionalLight;
import org.jungle.util.Material;
import org.jungle.util.OBJLoader;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;

public class GameLogic implements IGameLogic {

	private IRenderer renderer;
	private Context ctx;
	private Game game;
	private DirectionalLight light;
	private Mesh mesh;
	private Texture texture;
	private Material material;
	private Spatial spatial;
	private Vector3f cameraInc;
	private MouseInput mouseInput;
	
	private long lastFPSCheck = 0;
	private int currentFrames = 0;
	private int currentFPS = 0;
	
	private World world;
	
	public int getFPS() {
		return currentFPS;
	}
	
	@Override
	public void bind(Game g) {
		game = g;
	}

	@Override
	public void cleanup() {
		
	}

	@Override
	public void init(Window window) throws Exception {
		texture = new Texture("res/assets/grassblock.png");
		cameraInc = new Vector3f();
		mesh = OBJLoader.loadMesh("res/assets/evil.obj");
		material = new Material(texture, 1f);
		mesh.setMaterial(material);
		spatial = new Spatial(mesh);
		spatial.setPosition(20, 0, 10);
		renderer = new JungleRender();
		ctx = new Context(game, new Camera());
		ctx.addSpatial(spatial);
		world = new World();
		light = new DirectionalLight(new Vector3f(1, 1, 0.7f), new Vector3f(0, 1, 1), 1.f);
		mouseInput = new MouseInput();
		mouseInput.init(window);
		DebugLogger.logInfo("Running on LWJGL " + Version.VERSION_MAJOR + "." + Version.VERSION_MINOR + "." + Version.VERSION_REVISION);
		try {
			renderer.init(window);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void input(Window window) {
		
		if (window.isKeyPressed(GLFW.GLFW_KEY_W)) {
			cameraInc.z = -1;
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_S)) {
			cameraInc.z = 1;
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_A)) {
			cameraInc.x = -1;
		}
//		if (window.isKeyPressed(GLFW.GLFW_KEY_Q)) {
//			cameraInc.x = -1;
//		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_D)) {
			cameraInc.x = 1;
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			cameraInc.y = -1;
		}
		if  (window.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			cameraInc.y = 1;
		}
		mouseInput.input(window);
	}

	@Override
	public void render(Window window) {
		
		if (window.shouldClose()) {
			game.exit();
		}
		
		window.setClearColor(new Vector4f(0.1f, 0.7f, 0.7f, 1f));
		renderer.render(window, ctx, new Vector3f(0.3f, 0.3f, 0.3f), null, null, light);
	}

	@Override
	public void update(float interval) {
		
		ctx.getCamera().movePosition(cameraInc.x * 0.11f, cameraInc.y * 0.11f, cameraInc.z * 0.11f);
		if (mouseInput.isLeftButtonPressed()) {
			ctx.getCamera().moveRotation(mouseInput.getDisplVec().x * 0.51f, mouseInput.getDisplVec().y * 0.51f, 0);
		}
		cameraInc.x = cameraInc.y = cameraInc.z = 0; // Reset positions
		countFPS();
	}
	
	private void countFPS() {
		currentFrames++;
		if (System.nanoTime() > lastFPSCheck + 1000000000) {
			lastFPSCheck = System.nanoTime();
			currentFPS = currentFrames;
			currentFrames = 0;
		}
	}
	
}