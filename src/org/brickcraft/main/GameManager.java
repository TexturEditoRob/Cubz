package org.brickcraft.main;

import org.brickcraft.graphics.DisplayManager;
import org.brickcraft.graphics.loader.Loader;
import org.brickcraft.graphics.models.RawModel;
import org.brickcraft.graphics.renderers.Renderer;
import org.brickcraft.utils.DebugLogger;
import org.lwjgl.opengl.Display;

import shaders.StaticShader;

public class GameManager implements DebugLogger {

	public static void main(String[] args) {
		
		DebugLogger.debugMessage("Creating Window...");
		DisplayManager.createWindow();
		
		 float[] vertices = { 
			
			-0.5f, 0.5f, 0,
			-0.5f, -0.5f, 0, 
			0.5f, -0.5f, 0,
			0.5f, 0.5f, 0
			
		};
		 
		int[] indices = {
				
			0,1,3,
			3,1,2
				
		};
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		RawModel model = loader.loadToVAO(vertices, indices);
		StaticShader shader = new StaticShader();
		
		while (!Display.isCloseRequested()) {
			
//			DebugLogger.debugMessage("Rendering Window...");
			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateWindow();
			
		}
		
		DebugLogger.debugMessage("Closing Window...");
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeWindow();

	}

}
