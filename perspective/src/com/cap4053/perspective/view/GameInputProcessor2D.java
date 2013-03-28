package com.cap4053.perspective.view;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.models2D.PerspectiveObject;
import com.cap4053.perspective.screens.GameScreen2D;
import com.cap4053.perspective.screens.GameScreen3D;

public class GameInputProcessor2D implements InputProcessor{

	private Perspective game;
	private GameScreen2D gameScreen;
	
	public GameInputProcessor2D(Perspective game, GameScreen2D gameScreen){
		
		this.game = game;
		this.gameScreen = gameScreen;
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
//		Gdx.app.log(Perspective.TAG, "Touch down received! X: " + screenX + ", Y: " + screenY);
		
		Plane plane = gameScreen.getLevel2D();
		
		Vector2 coords = gameScreen.getStage().screenToStageCoordinates(new Vector2(screenX, screenY));
		
		plane.onTouch(coords.x, coords.y, gameScreen.getStage());
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
//		TextureRegion front = ScreenUtils.getFrameBufferTexture((int)GameScreen2D.HORIZONTAL_MARGIN, (int)GameScreen2D.VERTICAL_MARGIN,
//																	480 - (int)GameScreen2D.HORIZONTAL_MARGIN, 320 - (int)GameScreen2D.VERTICAL_MARGIN);
		//all faces are the same for now
		Texture front = gameScreen.getScreen();
		game.setScreen(new GameScreen3D(game, front, front, front, front, front, front));
		
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
