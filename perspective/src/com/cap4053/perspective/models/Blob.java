package com.cap4053.perspective.models;

import com.badlogic.gdx.math.Vector2;

public class Blob {
	enum State {
		IDLE, MOVING
	}
	
	private Vector2 position = new Vector2();
	private State state = State.IDLE;
	
	public Blob(Vector2 position){
		this.position = position;
	}
	
	public void setState(State newState){
		this.state = newState;
	}
}
