package org.digitalsnake.cubz.main;

import static org.digitalsnake.cubz.utils.Reference.*;
import static org.digitalsnake.cubz.utils.keycodes.KeyCodes.*;
import org.digitalsnake.cubz.blocks.BlockInstance;
import org.digitalsnake.cubz.entity.Entity;
import org.digitalsnake.cubz.launcher.GameLauncher;
import org.digitalsnake.cubz.ui.UISystem;
import org.digitalsnake.cubz.utils.debug.DebugLogger;
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

	public static Context ctx;
	private Window win;
	private IRenderer renderer;
	private Game game;
	private DirectionalLight light;
	private Vector3f cameraInc;
	private MouseInput mouseInput;
	private long lastFPSCheck = 0;
	private int currentFrames = 0;
	private static int currentFPS = 0;
	
	private World world;
	
	public static int getFPS() {
		return currentFPS;
	}
	
	@Override
	public void bind(Game g) {
		game = g;
		win = GameLauncher.getWindow();
		win.setSize(WIDTH, HEIGHT);
		win.setTitle(TITLE);
	}

	@Override
	public void cleanup() {
		
	}

	@Override
	public void init(Window window) throws Exception {
		cameraInc = new Vector3f();
		renderer = new JungleRender();
		ctx = new Context(game, new Camera());
		ctx.getCamera().setPosition(32, 6, 32);
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
		
		for (int x = 0; x < world.getWidth(); x++) {
			for (int z = 0; z < world.getDepth(); z++) {
				for (int y = 0; y < 255; y++) {
					BlockInstance b = world.getBlock(x, y, z);
					if (b != null) {
						Spatial bs = b.getSpatial();
						bs.setPosition(x, y, z);
						bs.setScale(0.5f);
						ctx.addSpatial(bs);
					}
				}
			}
		}
		
		for (Entity en : world.getEntities()) {
			ctx.addSpatial(en.getSpatial());
		}
	}

	@Override
	public void input(Window window) {
		
		if (window.isKeyPressed(KEY_W)) {
			cameraInc.z = -1;
		}
		if (window.isKeyPressed(KEY_S)) {
			cameraInc.z = 1;
		}
		if (window.isKeyPressed(KEY_A)) {
			cameraInc.x = -1;
		}
		if (window.isKeyPressed(KEY_D)) {
			cameraInc.x = 1;
		}
		if (window.isKeyPressed(KEY_SPACE)) {
			cameraInc.y = 1;
		}
		if  (window.isKeyPressed(KEY_SHIFT1)) {
			cameraInc.y = -1;
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
		countFPS();
	}

	@Override
	public void update(float interval) {
		
		ctx.getCamera().movePosition(cameraInc.x * 0.11f, cameraInc.y * 0.11f, cameraInc.z * 0.11f);
		if (mouseInput.isLeftButtonPressed()) {
			ctx.getCamera().moveRotation(mouseInput.getDisplVec().x * 0.51f, mouseInput.getDisplVec().y * 0.51f, 0);
		}
		cameraInc.x = cameraInc.y = cameraInc.z = 0; // Reset positions
		for (Entity en : world.getEntities()) {
			en.update();
		}
	}
	
	private void countFPS() {
		currentFrames++;
		if (System.nanoTime() > lastFPSCheck + 1000000000) {
			lastFPSCheck = System.nanoTime();
			currentFPS = currentFrames;
			currentFrames = 0;
			if (currentFPS <= 10) {
				DebugLogger.logInfo("Running on " + getFPS() + " FPS (Bad Performance)");
			}
			if (currentFPS >= 50) {
				DebugLogger.logInfo("Running on " + getFPS() + " FPS (Good Performance)");
			}
		}
	}
	
}