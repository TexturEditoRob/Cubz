package org.digitalsnake.cubz.entity;

import org.joml.Vector3f;
import org.jungle.Mesh;
import org.jungle.Spatial;
import org.jungle.util.OBJLoader;

public abstract class Entity {

	protected String entityDisplayName;
	protected float entitySpeed;
	protected Mesh mesh;
	protected Spatial spatial;
	
	protected Vector3f position = new Vector3f();
	
	public String getDisplayName() {
		return entityDisplayName;
	}
	
	public float getSpeed() {
		return entitySpeed;
	}
	
	protected Mesh loadMesh(String obj) throws Exception {
		return OBJLoader.loadMesh("./res/assets/" + obj + ".obj");
	}
	
	public Mesh getMesh() {
		return mesh;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public Spatial getSpatial() {
		spatial.setPosition(position.x(), position.y(), position.z());
		return spatial;
	}
	
	public void update() {
		
	}
	
}