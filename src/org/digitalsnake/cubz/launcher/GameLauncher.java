package org.digitalsnake.cubz.launcher;

import org.digitalsnake.cubz.main.GameLogic;
import org.digitalsnake.cubz.utils.Reference;
import org.jungle.Window;
import org.jungle.game.Game;

public class GameLauncher extends Game {

	public static GameLauncher instance;
	
	public static void main(String[] args) {
		GameLauncher.instance = new GameLauncher();
		instance.logic = new GameLogic();
//		instance.win.setSize(800, 600);
//		instance.win.setTitle("Cubz");
		instance.start();
	}
	
	public static Window getWindow() {
		return instance.win;
	}
	
}