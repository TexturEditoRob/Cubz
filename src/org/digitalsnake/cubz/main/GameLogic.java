package org.digitalsnake.cubz.main;

import org.digitalsnake.cubz.utils.DebugLogger;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.jungle.Camera;
import org.jungle.Mesh;
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
	
	private long lastFPSCheck = 0;
	private int currentFrames = 0;
	private int currentFPS = 0;
	
	private float color;
	
	private int getFPS() {
		
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
		mesh = OBJLoader.loadMesh("res/assets/evil.obj");
		material = new Material(texture, 1f);
		mesh.setMaterial(material);
		spatial = new Spatial(mesh);
		spatial.setPosition(20, 0, 10);
		renderer = new JungleRender();
		ctx = new Context(game, new Camera());
		ctx.addSpatial(spatial);
		light = new DirectionalLight(new Vector3f(1, 1, 1), new Vector3f(0, 1, 1), 1.f);
		
		try {
			renderer.init(window);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void input(Window window) {
		
		if (window.isKeyPressed(GLFW.GLFW_KEY_UP)) {
			
			color += 0.01f;
			
		}
		
		if (window.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
			
			color -= 0.01f;
			
		}
		
	}

	@Override
	public void render(Window window) {
		
//		System.out.println(ctx);
		
		if (window.shouldClose()) {
			
			game.exit();
			
		}
		
		window.setClearColor(new Vector4f(color, color, color, 1));
		
		renderer.render(window, ctx, new Vector3f(0.3f, 0.3f, 0.3f), null, null, light);
		
	}

	@Override
	public void update(float interval) {
		
		currentFrames++;
		
		if (System.nanoTime() > lastFPSCheck + 1000000000) {
			
			lastFPSCheck = System.nanoTime();
			currentFPS = currentFrames;
			currentFrames = 0;
			
			
		}
		
	}

	
	
}