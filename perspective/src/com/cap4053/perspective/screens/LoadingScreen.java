package com.cap4053.perspective.screens;

import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.LevelManager;

public class LoadingScreen extends PerspectiveScreen {

	private LevelManager manager;
	
	private GameScreen2D[] screens;
	
	public LoadingScreen(Perspective game, LevelManager manager, 	GameScreen2D front, 
																	GameScreen2D back, 
																	GameScreen2D left,
																	GameScreen2D right, 
																	GameScreen2D top, 
																	GameScreen2D bottom) {
		
		super(game);
		
		this.manager = manager;
	}
}
