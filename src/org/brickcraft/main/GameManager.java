package org.brickcraft.main;

import org.brickcraft.graphics.DisplayManager;
import org.brickcraft.graphics.loader.Loader;
import org.brickcraft.graphics.models.RawModel;
import org.brickcraft.graphics.models.TexturedModel;
import org.brickcraft.graphics.renderers.Renderer;
import org.brickcraft.graphics.textures.ModelTexture;
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
		 
		float[] textureCoords = {
				
			0,0,
			0,1,
			1,1,
			1,0
				
		};
		 
		int[] indices = {
				
			0,1,3,
			3,1,2
				
		};
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		StaticShader shader = new StaticShader();
		ModelTexture texture = new ModelTexture(loader.loadTexture("logo"));
		TexturedModel texturedModel = new TexturedModel(model,texture);
		
		while (!Display.isCloseRequested()) {
			
//			DebugLogger.debugMessage("Rendering Window...");
			renderer.prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateWindow();
			
		}
		
		DebugLogger.debugMessage("Closing Window...");
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeWindow();

	}

}
