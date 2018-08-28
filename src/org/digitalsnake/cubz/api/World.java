package org.digitalsnake.cubz.api;

import org.digitalsnake.cubz.blocks.GrassBlock;

public class World {

	private String name;
	private BlockInstance[][][] blocks;
	
	public World() {
		name = "asdfmovie";
		blocks = new BlockInstance[64][255][64];
		generate();
	}
	
	private void generate() {
		int x = 0;
		int z = 0;
		GrassBlock b = new GrassBlock();
		for (; x < 64; x++) {
			z = 0;
			for (;z < 64; z++) {
				blocks[x][0][z] = new BlockInstance(b);
			}
		}
	}
	
}
