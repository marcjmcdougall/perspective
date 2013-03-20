package com.cap4053.perspective.view;

import com.cap4053.perspective.Perspective;

public abstract class PerspectiveInputProcessor implements com.badlogic.gdx.InputProcessor{

	protected Perspective perspectiveGame;
	
	public PerspectiveInputProcessor(Perspective game){
		
		this.perspectiveGame = game;
	}
}
