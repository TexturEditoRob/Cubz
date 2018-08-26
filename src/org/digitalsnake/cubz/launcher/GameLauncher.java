package org.digitalsnake.cubz.launcher;

import org.digitalsnake.cubz.main.GameLogic;
import org.digitalsnake.cubz.utils.Reference;
import org.jungle.game.Game;

public class GameLauncher extends Game {

	private static GameLauncher launcher = Reference.launcher;
	
	public static void main(String[] args) {
		
		launcher.logic = new GameLogic();
		
		launcher.start();
		
	}
	
}