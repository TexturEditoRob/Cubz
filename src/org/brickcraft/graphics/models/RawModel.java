package org.brickcraft.graphics.models;

public class RawModel {

	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoID, int vertexCount) {
		
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		
	}

	public int getVAO() {
		
		return vaoID;
		
	}

	public int getVertexCount() {
		
		return vertexCount;
		
	}
	
}