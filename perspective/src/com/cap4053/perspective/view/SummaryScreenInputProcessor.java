package com.cap4053.perspective.view;

import com.badlogic.gdx.math.Vector2;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.LevelManager;

public class SummaryScreenInputProcessor extends PerspectiveInputProcessor {

	LevelManager manager;
	
	public SummaryScreenInputProcessor(Perspective game, LevelManager manager) {
		super(game);
		this.manager = manager;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if(manager.getCurrentLevel() < 3){
			manager.setCurrentLevel(manager.getCurrentLevel() + 1);
			try {
				manager.loadLevel("data/levels/level" + manager.getCurrentLevel() + ".txt");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
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
