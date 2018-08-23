package org.brickcraft.graphics;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.opengl.ImageIOImageData;

public class DisplayManager {

	private static ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
	
	private static int WIDTH = 800, HEIGHT = 600, FPS_CAP = 120;
	private static String TITLE = "BrickCraft 3D";
	
	public static void createWindow() {
		
			try {
				Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
				Display.create(new PixelFormat(), attribs);
				Display.setTitle(TITLE);
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		
	}
	
	public static void updateWindow() {
		
		Display.sync(FPS_CAP);
		Display.update();
		
		countFPS();
		
	}
	
	public static void closeWindow() {
		
		Display.destroy();
		
	}
	
	/*WIP*/
	private static void countFPS() {
		
		//TODO: Count FPS in Game
		
	}
	
}