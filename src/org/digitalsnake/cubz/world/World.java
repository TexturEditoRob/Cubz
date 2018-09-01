package org.digitalsnake.cubz.world;

import java.util.ArrayList;

import org.digitalsnake.cubz.blocks.Block;
import org.digitalsnake.cubz.blocks.BlockInstance;
import org.digitalsnake.cubz.blocks.GrassBlock;
import org.digitalsnake.cubz.entity.Entity;
import org.digitalsnake.cubz.entity.UglyPlayer;

public class World {

	private String name;
	private int width, depth;
	private BlockInstance[][][] blocks;
	private ArrayList<Entity> entities = new ArrayList<>();
	
	public World() {
		name = "asdfmovie";
		width = 64;
		depth = 64;
		blocks = new BlockInstance[width][255][depth];
		generate();
		entities.add(new UglyPlayer());
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public Entity[] getEntities() {
		return entities.toArray(new Entity[entities.size()]);
	}
	
	public BlockInstance getBlock(int x, int y, int z) {
		return blocks[x][y][z];
	}
	
	private void generate() {
		GrassBlock b = new GrassBlock();
		for (int x = 0; x < 64; x++) {
			for (int z = 0; z < 64; z++) {
				blocks[x][0][z] = new BlockInstance(b);
			}
		}
	}
	
}
