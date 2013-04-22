package com.cap4053.perspective.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.LevelManager;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.screens.GameScreen2D;
import com.cap4053.perspective.screens.LevelSelectorScreen;

public class GameInputProcessor2D extends PerspectiveInputProcessor{

	private GameScreen2D gameScreen;
	private LevelManager manager;
	
	public GameInputProcessor2D(Perspective game, LevelManager manager, GameScreen2D gameScreen){
		
		super(game);
		
		this.gameScreen = gameScreen;
		this.manager = manager;
	}

	/*@Override
	public boolean keyUp(int keycode) {
		
		if(keycode == Input.Keys.BACK | keycode == Input.Keys.BACKSPACE){
			
			perspectiveGame.setScreen(new LevelSelectorScreen(perspectiveGame));
		}
		else if(keycode == Input.Keys.NUM_3 | keycode == Input.Keys.MENU){
			
//			Texture front = gameScreen.getScreen();
//			perspectiveGame.setScreen(new GameScreen3D(perspectiveGame, front, front, front, front, front, front));
			manager.togglePerspective();
		}
		else if(keycode == Input.Keys.T){
			
			// TODO: Removed for now
//			Texture screen = gameScreen.getScreen();
			
//			perspectiveGame.setScreen(new GameScreen3D(perspectiveGame, manager, screen, screen, screen, screen, screen, screen));
		}
		else if(keycode == Input.Keys.M){
			
			manager.toggleMenu();
		}
		else if(keycode == Input.Keys.S){
			Perspective game = new Perspective();
			Json json = new Json();
			
			game.setScreen(perspectiveGame.getScreen());
			game.setTime(perspectiveGame.getTime());
			Gdx.app.log(Perspective.TAG, json.toJson(game));
			
			Perspective game1 = new Perspective();
			if(game1.getScreen()==null)
				Gdx.app.log(Perspective.TAG, "Initial Screen is null, time: " + game1.getTime());
			
			
			game1 = json.fromJson(Perspective.class, json.toJson(game));
			
			if(game1.getScreen()==null)
				Gdx.app.log(Perspective.TAG, "New Screen is null, time: " + game1.getTime());
		}
		
		return true;
	}*/

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if(count==2&&manager.getCanZoomOut())
			manager.togglePerspective();
		
		else if(count==1)
		{
			Plane plane = gameScreen.getLevel2D();
			
			Vector2 coords = gameScreen.getStage().screenToStageCoordinates(new Vector2(x, y));
			
			plane.onTouch(coords.x, coords.y, gameScreen.getStage());
		}
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		manager.togglePerspective();
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

		if(initialDistance>distance*2)
		{
			//manager.togglePerspective();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		
		return false;
	}
}
