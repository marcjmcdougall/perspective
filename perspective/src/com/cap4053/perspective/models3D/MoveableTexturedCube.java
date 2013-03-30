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
	
	// Returns an integer for which face is facing front.
	// 1 = front
	// 2 = right
	// 3 = back
	// 4 = left
	// 5 = top
	// 6 = bottom
	// 0 = error?
	public int findFrontFace(){
		float adjAngX = angleX%360;
		float adjAngY = angleY%360;
		
		if(adjAngX < 0){
			adjAngX = 360 + adjAngX;
		}
		if(adjAngY < 0){
			adjAngY = 360 + adjAngY;
		}
		
		int returnVal = 0;
		
		//Front
		if((adjAngX > 315 || adjAngX < 45) && (adjAngY > 315 || adjAngY < 45)){
			returnVal = 1;
		}
		//Left
		if(adjAngX > 45 && adjAngX < 135){
			returnVal = 4;
		}
		//Back
		if((adjAngX > 135 && adjAngX < 225) && (adjAngY > 315 || adjAngY < 45)){
			returnVal = 3;
		}
		//Right
		if(adjAngX < 315 && adjAngX > 225){
			returnVal = 2;
		}
		//Top
		if(adjAngY > 45 && adjAngY < 135){
			returnVal = 5;
		}
		//Bottom
		if(adjAngY > 225 && adjAngY < 315){
			returnVal = 6;
		}
		
		return returnVal;
	}

	@Override
	public void update() {
	
		// TODO: Implementation
		// Current, do nothing.
		//System.out.println("front face: " + findFrontFace());
		
		if(scale > ENDING_POINT){
			
			scale -= SCALE_FACTOR;
		}
	}
	
	public float getScale(){
		
		return scale;
	}
}
