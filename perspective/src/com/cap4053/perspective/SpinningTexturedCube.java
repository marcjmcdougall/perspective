package com.cap4053.perspective;

import com.badlogic.gdx.graphics.Texture;

public class SpinningTexturedCube extends TexturedCube {

	private float speed;
	
	public SpinningTexturedCube(Texture texture, float spinSpeed) {
		
		super(texture, texture, texture, texture, texture, texture);
		
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