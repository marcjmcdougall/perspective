package com.cap4053.perspective.models3D;

import com.badlogic.gdx.graphics.Texture;

public class MoveableTexturedCube extends TexturedCube {

	private static final float STARTING_POINT = -2.15f;
	private static final float ENDING_POINT = -3.0f;
	private static final float SCALE_FACTOR = 0.025f;
	
	private float scale;
	
	public MoveableTexturedCube(Texture frontTexture, Texture backTexture, Texture leftTexture, Texture rightTexture, Texture topTexture, Texture bottomTexture) {
		
		super(frontTexture, backTexture, leftTexture, rightTexture, topTexture, bottomTexture);
		
		scale = STARTING_POINT;
	}

	@Override
	public void update() {
	
		// TODO: Implementation
		// Current, do nothing.
		
		if(scale > ENDING_POINT){
			
			scale -= SCALE_FACTOR;
		}
	}
	
	public float getScale(){
		
		return scale;
	}
}
