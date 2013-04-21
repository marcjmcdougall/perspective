package com.cap4053.perspective.view;

import com.badlogic.gdx.math.Vector2;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.screens.MenuScreen;

public class SplashScreenInputProcessor extends PerspectiveInputProcessor {

	public SplashScreenInputProcessor(Perspective game) {
		
		super(game);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		perspectiveGame.setScreen(new MenuScreen(perspectiveGame));
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		
		return false;
	}
}
