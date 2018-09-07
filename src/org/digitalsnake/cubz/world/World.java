package org.digitalsnake.cubz.world;

import java.util.ArrayList;

import org.digitalsnake.cubz.blocks.Block;
import org.digitalsnake.cubz.blocks.BlockInstance;
import org.digitalsnake.cubz.blocks.Dirt;
import org.digitalsnake.cubz.blocks.Grass;
import org.digitalsnake.cubz.blocks.Stone;
import org.digitalsnake.cubz.entity.Entity;
import org.digitalsnake.cubz.entity.UglyPlayer;

public class World {

	private String name;
	private int width, depth;
	private BlockInstance[][][] blocks;
	private ArrayList<Entity> entities = new ArrayList<>();
	
	public World() {
		name = "Flat World";
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
		Grass grass = new Grass();
		Dirt dirt = new Dirt();
		Stone stone = new Stone();
		for (int x = 0; x < 64; x++) {
			for (int z = 0; z < 64; z++) {
				blocks[x][5][z] = new BlockInstance(grass);
				for (int i = 0; i < 5; i++) {
					blocks[x][i][z] = new BlockInstance(dirt);	
				}
				for (int i = 0; i < 2; i++) {
					blocks[x][i][z] = new BlockInstance(stone);
				}
			}
		}
	}
	
}
