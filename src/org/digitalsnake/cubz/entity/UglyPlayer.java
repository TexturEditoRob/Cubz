package org.digitalsnake.cubz.entity;

import org.digitalsnake.cubz.main.GameLogic;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.jungle.Spatial;
import org.jungle.util.Material;

public class UglyPlayer extends Entity {

	public UglyPlayer() {
		try {
			mesh = loadMesh("uglyplayer");
			Material mat = new Material(new Vector4f(0.1f, 0.25f, 0.5f, 0), 1f);
			mesh.setMaterial(mat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		spatial = new Spatial(mesh);
		spatial.setScale(0.5f);
	}
	
	public void update() {
		super.update();
		Vector3f campos = GameLogic.ctx.getCamera().getPosition();
		position.x = campos.x;
		position.y = campos.y;
		position.z = campos.z;
		position.z += 5.0f;
		position.y -= 2.0f;
		spatial.setPosition(position.x, position.y, position.z);
	}
	
}
