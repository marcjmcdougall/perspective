package com.cap4053.perspective;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.cap4053.perspective.screens.PerspectiveScreen;
import com.cap4053.perspective.screens.SplashScreen;
import com.cap4053.perspective.view.SplashScreenInputProcessor;

public class Perspective extends Game {
	
	public static final String TAG = Perspective.class.getSimpleName();
	
	private PerspectiveScreen currentScreen;
	private Music musicPlayer;
	
	@Override
	public void create() {		
		
		musicPlayer = Gdx.audio.newMusic(Gdx.files.internal("data/music/nowhere_but_up.mp3"));
		
		musicPlayer.setLooping(true);
		musicPlayer.setVolume(0.15f);
		musicPlayer.play();
		
		setScreen(new SplashScreen(this));
		Gdx.input.setInputProcessor(new SplashScreenInputProcessor(this));
		
		//Get rid of this stuff below later
		//GameSave testSave = new GameSave();
		//testSave.save();
		//testSave.load();
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
	
	public void setScreen(PerspectiveScreen screen){
		
		this.currentScreen = screen;
		super.setScreen(screen);
	}
	
	public Screen getScreen(){
		
		return currentScreen;
	}
}
