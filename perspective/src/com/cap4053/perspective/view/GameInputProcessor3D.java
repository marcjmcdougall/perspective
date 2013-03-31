package com.cap4053.perspective.view;

import com.badlogic.gdx.Input;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.LevelManager;
import com.cap4053.perspective.screens.GameScreen3D;

public class GameInputProcessor3D extends PerspectiveInputProcessor {

	private static final float TOUCH_SCALE_FACTOR = 1.0f;
	
	private float previousY;
	private float previousX;
	
	private LevelManager manager;
	
	private GameScreen3D screen;
	
	public GameInputProcessor3D(Perspective game, LevelManager manager) {
		
		super(game);
		
		this.manager = manager;
		this.screen = (GameScreen3D) game.getScreen();
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

//		perspectiveGame.setScreen(new GameScreen2D(perspectiveGame));
		
		int targetScreen = screen.getCube().findFrontFace();
		
		if(targetScreen >= 0){
			
			//manager.setScreen(screen.getCube().findFrontFace());
			//switchPerspective();
			
			screen.getCube().transitionTo2D();
		}
		
		return false;
	}
	
	public boolean keyUp(int keycode) {
		
//		if(keycode == Input.Keys.NUM_1){
//			
//			manager.setScreen(0);
//			switchPerspective();
//		}
//		else if(keycode == Input.Keys.NUM_2){
//			
//			manager.setScreen(1);
//			switchPerspective();
//		}
//		else if(keycode == Input.Keys.NUM_3){
//			
//			manager.setScreen(2);
//			switchPerspective();
//		}
//		else if(keycode == Input.Keys.NUM_4){
//			
//			manager.setScreen(3);
//			switchPerspective();
//		}
//		else if(keycode == Input.Keys.NUM_5){
//			
//			manager.setScreen(4);
//			switchPerspective();
//		}
//		else if(keycode == Input.Keys.NUM_6){
//			
//			manager.setScreen(5);
//			switchPerspective();
//		}
//		
		if(keycode == Input.Keys.NUM_2){
			
			//perspectiveGame.setScreen(new GameScreen2D(perspectiveGame));
			
			screen.getCube().transitionTo2D();
		}
		return false;
	}
	
	private void switchPerspective(){
		
		manager.togglePerspective();
		manager.showScreen();
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
