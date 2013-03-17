package com.cap4053.perspective;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.cap4053.perspective.screens.SplashScreen;
import com.cap4053.perspective.view.PerspectiveInputProcessor;

public class Perspective extends Game {
	
	public static final String TAG = "Perspective: ";
	
	@Override
	public void create() {		
		
		setScreen(new SplashScreen(this));
		Gdx.input.setInputProcessor(new PerspectiveInputProcessor(this));
	}
	
	@Override
	public void dispose() {
		
		super.dispose();
	}

	@Override
	public void render() {	
		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		
		super.resize(width, height);
	}

	@Override
	public void pause() {
		
		super.pause();
	}

	@Override
	public void resume() {
		
		super.resume();
	}
	
	public void setScreen(Screen screen){
		
		super.setScreen(screen);
	}
}
