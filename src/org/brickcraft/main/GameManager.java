package org.brickcraft.main;

import org.brickcraft.graphics.DisplayManager;
import org.brickcraft.graphics.loader.Loader;
import org.brickcraft.graphics.models.RawModel;
import org.brickcraft.graphics.renderers.Renderer;
import org.lwjgl.opengl.Display;

import shaders.StaticShader;

public class GameManager {
	
	public static void main(String[] args) {
		
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
			
			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateWindow();
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeWindow();

	}

}
