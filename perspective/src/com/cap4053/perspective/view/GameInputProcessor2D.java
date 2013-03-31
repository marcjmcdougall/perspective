package com.cap4053.perspective.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.LevelManager;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.screens.GameScreen2D;
import com.cap4053.perspective.screens.GameScreen3D;
import com.cap4053.perspective.screens.LevelSelectorScreen;

public class GameInputProcessor2D extends PerspectiveInputProcessor{

	private GameScreen2D gameScreen;
	private LevelManager manager;
	
	public GameInputProcessor2D(Perspective game, LevelManager manager, GameScreen2D gameScreen){
		
		super(game);
		
		this.gameScreen = gameScreen;
		this.manager = manager;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
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
	public boolean keyUp(int keycode) {
		
		if(keycode == Input.Keys.BACK | keycode == Input.Keys.BACKSPACE){
			
			perspectiveGame.setScreen(new LevelSelectorScreen(perspectiveGame));
		}
		else if(keycode == Input.Keys.NUM_3){
			
//			Texture front = gameScreen.getScreen();
//			perspectiveGame.setScreen(new GameScreen3D(perspectiveGame, front, front, front, front, front, front));
			
			manager.togglePerspective();
			manager.showScreen();
		}
		
		return true;
	}
}
