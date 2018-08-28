package org.digitalsnake.cubz.launcher;

import org.digitalsnake.cubz.main.GameLogic;
import org.jungle.game.Game;

public class GameLauncher extends Game {

	public static GameLauncher instance;
	
	public static void main(String[] args) {
		GameLauncher.instance = new GameLauncher();
		instance.logic = new GameLogic();
		instance.start();
	}
	
}