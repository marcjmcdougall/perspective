package com.cap4053.perspective.models3D;

import com.badlogic.gdx.graphics.Texture;

public class SpinningTexturedCube extends TexturedCube {

	private float speed;
	
	public SpinningTexturedCube(Texture frontTexture, Texture backTexture, Texture leftTexture, Texture rightTexture, Texture topTexture, Texture bottomTexture, float spinSpeed) {
		
		super(frontTexture, backTexture, leftTexture, rightTexture, topTexture, bottomTexture);
		
		this.speed = spinSpeed;
	}

	@Override
	public void update() {

		float angleX = this.getAngleX();
		float angleY = this.getAngleY();
		
		this.setAngleX(angleX + speed);
		this.setAngleY(angleY + speed);
		
		if(angleX > 360.0f){
			
			this.setAngleX(0.0f);
		}
		
		if(angleY > 360.0f){
			
			this.setAngleY(0.0f);
		}
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "New angleX: " + angleX + " Speed: "+ speed);
	}
}
