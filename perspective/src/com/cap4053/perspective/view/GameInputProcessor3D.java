package com.cap4053.perspective.view;

import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.screens.CubeGameScreen;

public class GameInputProcessor3D extends PerspectiveInputProcessor {

	private static final float TOUCH_SCALE_FACTOR = 1.0f;
	
	private float previousY;
	private float previousX;
	
	private CubeGameScreen screen;
	
	public GameInputProcessor3D(Perspective game) {
		
		super(game);
		
		this.screen = (CubeGameScreen) game.getScreen();
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

//		DEBUG
//		Gdx.app.log(Perspective.TAG, "**Screen being touched now**");
		
		previousX = screenX;
        previousY = screenY;
		
		return true;
	}
	
	public boolean touchDragged(int screenX, int screenY, int pointer){
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "**Screen being dragged now**");
		
		float x = screenX;
        float y = screenY;
        
        float currentAngleX = screen.getCube().getAngleX();
        float currentAngleY = screen.getCube().getAngleY();
        
        float dx = x - previousX;
        float dy = y - previousY;
        
        screen.getCube().setAngleX(currentAngleX + dx * TOUCH_SCALE_FACTOR);
        screen.getCube().setAngleY(currentAngleY + dy * TOUCH_SCALE_FACTOR);
        
        previousX = x;
        previousY = y;
        
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
}
