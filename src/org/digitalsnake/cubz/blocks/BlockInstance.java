package org.digitalsnake.cubz.blocks;

import org.jungle.Mesh;
import org.jungle.Spatial;
import org.jungle.Texture;
import org.jungle.util.Material;
import org.jungle.util.OBJLoader;

/**
 * Implementation of one Block class that can be put in a World.
 * @author zenith391
 *
 */
public class BlockInstance {

	private Block block;
	private Spatial spatial;
	
	public BlockInstance(Block block) {
		this.block = block;
	}
	
	public void update() {
		
	}
	
	public Mesh getMesh() {
		if (block._textureCache == null) {
			try {
				block._textureCache = new Texture("./res/assets/" + block.getTexture() + ".png");
				// Assuming mesh too is empty
				block._meshCache = OBJLoader.loadMesh("res/assets/evil.obj");
				Material material = new Material(block._textureCache, 1.0f);
				block._meshCache.setMaterial(material);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return block._meshCache;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public Spatial getSpatial() {
		if (spatial == null) {
			spatial = new Spatial(getMesh());
		}
		return spatial;
	}
	
}
