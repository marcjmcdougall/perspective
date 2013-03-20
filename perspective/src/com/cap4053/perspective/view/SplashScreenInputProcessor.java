package com.cap4053.perspective.view;

import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.screens.GameScreen;

public class SplashScreenInputProcessor extends PerspectiveInputProcessor {

	public SplashScreenInputProcessor(Perspective game) {
		
		super(game);
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
		
		perspectiveGame.setScreen(new GameScreen(perspectiveGame));
		
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
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

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}
}
