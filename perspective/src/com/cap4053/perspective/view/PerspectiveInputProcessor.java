package com.cap4053.perspective.view;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.cap4053.perspective.Perspective;

public abstract class PerspectiveInputProcessor implements GestureListener{

	protected Perspective perspectiveGame;
	
	public PerspectiveInputProcessor(Perspective game){
		
		this.perspectiveGame = game;
	}
}
