package com.cap4053.perspective.view;

import com.badlogic.gdx.Game;
import com.cap4053.perspective.screens.SplashScreen;

public class PerspectiveInputProcessor implements com.badlogic.gdx.InputProcessor{

	private Game perspectiveGame;
	
	public PerspectiveInputProcessor(Game game){
		
		this.perspectiveGame = game;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		
		return false;
	}

}
