package org.digitalsnake.cubz.blocks;

import org.jungle.Mesh;
import org.jungle.Texture;

public class Block {

	Texture _textureCache;
	Mesh _meshCache;
	
	private String texture = "grassblock";
	
	public String getTexture() {
		return texture;
	}
	
	protected void setTexture(String texture) {
		this.texture = texture;
	}
	
	public void update() {
		
	}
	
}
