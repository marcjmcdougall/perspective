package com.cap4053.perspective.models3D;

import com.badlogic.gdx.graphics.Texture;

public class MoveableTexturedCube extends TexturedCube {

	private static final float STARTING_POINT = -3.0f;
	private static final float ENDING_POINT = -3.5f;
	private static final float SCALE_FACTOR = 0.025f;
	private static final float ANGLE_SCALE = 2f;
	
	private float scale;
	
	private int targetX;
	private int targetY;
	
	private boolean transition;
	
	public MoveableTexturedCube(Texture frontTexture, Texture backTexture, Texture leftTexture, Texture rightTexture, Texture topTexture, Texture bottomTexture) {
		
		super(frontTexture, backTexture, leftTexture, rightTexture, topTexture, bottomTexture);
		
		scale = STARTING_POINT;
		transition = false;
	}
	
	// Returns an integer for which face is facing front.
	// 0 = front
	// 2 = left
	// 3 = right
	// 1 = back 
	// 4 = top
	// 5 = bottom
	// -1 = error?
	public int findFrontFace(){
		
		//System.out.println("X: " + angleX);
		//System.out.println("Y: " + angleY);
		float adjAngX = angleX%360;
		float adjAngY = angleY%360;
		
		if(adjAngX < 0){
			
			adjAngX = 360 + adjAngX;
		}
		if(adjAngY < 0){
			
			adjAngY = 360 + adjAngY;
		}
		//System.out.println("X: " + adjAngX);
		//System.out.println("Y: " + adjAngY);
		
		int returnVal = -1;
		
		if(adjAngX >= 0 && adjAngX < 45){
			if(adjAngY >= 0 && adjAngY < 45){
				//front
				returnVal = 0;
			}
			else if(adjAngY >= 45 && adjAngY < 135){
				//top
				returnVal = 4;
			}
			else if(adjAngY >= 135 && adjAngY < 225){
				//back
				returnVal = 1;
			}
			else if(adjAngY >= 225 && adjAngY < 315){
				//bottom
				returnVal = 5;
			}
			else if(adjAngY >= 315 && adjAngY <= 360){
				//front
				returnVal = 0;
			}
		}
		if(adjAngX >= 45 && adjAngX < 135){
			//left
			returnVal = 2;
		}
		if(adjAngX >= 135 && adjAngX < 225){
			if(adjAngY >= 0 && adjAngY < 45){
				//back
				returnVal = 1;
			}
			else if(adjAngY >= 45 && adjAngY < 135){
				//bottom
				returnVal = 5;
			}
			else if(adjAngY >= 135 && adjAngY < 225){
				//front
				returnVal = 0;
			}
			else if(adjAngY >= 225 && adjAngY < 315){
				//top
				returnVal = 4;
			}
			else if(adjAngY >= 315 && adjAngY <= 360){
				//back
				returnVal = 1;
			}
		}
		if(adjAngX >= 225 && adjAngX < 315){
			//right
			returnVal = 3;
		}
		if(adjAngX >= 315 && adjAngX <= 360){
			if(adjAngY >= 0 && adjAngY < 45){
				//front
				returnVal = 0;
			}
			else if(adjAngY >= 45 && adjAngY < 135){
				//top
				returnVal = 4;
			}
			else if(adjAngY >= 135 && adjAngY < 225){
				//back
				returnVal = 1;
			}
			else if(adjAngY >= 225 && adjAngY < 315){
				//bottom
				returnVal = 5;
			}
			else if(adjAngY >= 315 && adjAngY <= 360){
				//front
				returnVal = 0;
			}
		}
		return returnVal;
	}
	
	public void transitionTo2D(){
		transition = true;
		
		//Set angle from 0 to 360
		float adjAngX = angleX%360;
		float adjAngY = angleY%360;
		if(adjAngX < 0){
			adjAngX = 360 + adjAngX;
		}
		if(adjAngY < 0){
			adjAngY = 360 + adjAngY;
		}
		angleX = adjAngX;
		angleY = adjAngY;
		
		//System.out.println("X: " + angleX);
		//System.out.println("Y: " + angleY);
		
		//Determine where angles should go based on front face
		int frontFace = findFrontFace();
		switch(frontFace){
		case 0:
			//Make angleX = 0
			//Make angleY = 0
			if(angleX > 180){
				targetX = 360;
			}
			else{
				targetX = 0;
			}
			if(angleY > 180){
				targetY = 360;
			}
			else{
				targetY = 0;
			}
			break;
		case 2:
			//Make angleX = 90
			//Make angleY = 0
			targetX = 90;
			if(angleY > 180){
				targetY = 360;
			}
			else{
				targetY = 0;
			}
			break;
		case 3:
			//Make angleX = 270
			//Make angleY = 0
			targetX = 270;
			if(angleY > 180){
				targetY = 360;
			}
			else{
				targetY = 0;
			}
			break;
		case 1:
			//Make angleX = 0
			//Make angleY = 180
			targetX = 180;
			if(angleY >180){
				targetY = 360;
			}
			else{
				targetY = 0;
			}
			break;
		
		case 4:
			//Make angleX = 0
			//Make angleY = 90
			if(angleX > 180){
				targetX = 360;
			}
			else{
				targetX = 0;
			}
			if(angleY > 180){
				targetY = 270;
			}
			else{
				targetY = 90;
			}
			break;
		case 5:
			//Make angleX = 0
			//Make angleY = 270
			if(angleX > 180){
				targetX = 360;
			}
			else{
				targetX = 0;
			}
			targetY = 270;
			break;
		}
	}
	
	

	@Override
	public void update() {
	
		// TODO: Implementation
		// Current, do nothing.
		//System.out.println("front face: " + findFrontFace());
		
		if(!transition && scale > ENDING_POINT){
			
			scale -= SCALE_FACTOR;
		}
		else if(transition && (scale < STARTING_POINT)){
			
			makeAngleX(targetX);
			makeAngleY(targetY);
			
			//Move cube towards viewport, only if angle adjustment is done
			if(targetX == angleX && targetY == angleY){
				scale += SCALE_FACTOR;
			}
				
			//Transition is now over
			if(scale >= STARTING_POINT){
				transition = false;
			}
		}
	}
	
	//Assumes angles have already been adjusted prior to call
	private void makeAngleX(float newAng){
		//If close enough, just make it the new angle
		if(Math.abs(angleX - newAng) < ANGLE_SCALE){
			angleX = newAng;
		}
		else{
			if(angleX > newAng){
				angleX -= ANGLE_SCALE;
			}
			else{
				angleX += ANGLE_SCALE;
			}
		}
	}
	
	//Assumes angles have already been adjusted prior to call
	private void makeAngleY(int newAng){
		//If close enough, just make it the new angle
		if(Math.abs(angleY - newAng) < ANGLE_SCALE){
			angleY = newAng;
		}
		else{
			if(angleY > newAng){
				angleY -= ANGLE_SCALE;
			}
			else{
				angleY += ANGLE_SCALE;
			}
		}
	}
	
	public boolean getTransition(){
		return transition;
	}
	
	public float getScale(){
		
		return scale;
	}
}
