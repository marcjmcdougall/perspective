package com.cap4053.perspective;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.cap4053.perspective.screens.SplashScreen;
import com.cap4053.perspective.view.SplashScreenInputProcessor;

public class Perspective extends Game {
	
	public static final String TAG = Perspective.class.getSimpleName();
	
	private Screen currentScreen;
	
	@Override
	public void create() {		
		
		setScreen(new SplashScreen(this));
		Gdx.input.setInputProcessor(new SplashScreenInputProcessor(this));
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
		
		this.currentScreen = screen;
		super.setScreen(screen);
	}
	
	public Screen getScreen(){
		
		return currentScreen;
	}
}
