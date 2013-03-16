package com.cap4053.perspective;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.cap4053.perspective.screens.GameScreen;
import com.cap4053.perspective.view.PerspectiveInputProcessor;

public class Main extends Game {
	
	@Override
	public void create() {		
		
		setScreen(new GameScreen());
		Gdx.input.setInputProcessor(new PerspectiveInputProcessor(this));
	}
	
	@Override
	public void dispose() {
		
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
}
