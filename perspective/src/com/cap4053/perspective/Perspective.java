package com.cap4053.perspective;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.OrderedMap;
import com.cap4053.perspective.screens.PerspectiveScreen;
import com.cap4053.perspective.screens.SplashScreen;
import com.cap4053.perspective.view.SplashScreenInputProcessor;

/**
 * Main Game class that contains most of the functionality for working the game Screens.
 * 
 * @author Marc J McDougall
 */
public class Perspective extends Game implements Json.Serializable {
	
	// Public class variable that is used for debugging
	public static final String TAG = Perspective.class.getSimpleName();
	public static final boolean DEBUG = true;
	
	// Public class variable used to control printout statements.  Also removes potentially frustrating features
	// during the development process (such as music and sound effects).  Must be set to false before release.
	public static final boolean DEVELOPER_MODE = true;
	
	// Reference to the current screen that is being displayed 
	private PerspectiveScreen currentScreen;
	
	// Music variable that handles the playing of music for the application
	private Music musicPlayer;
	
	private int time = 59;
	private float elapsed = 0;
	
	private static final String TIME = "time";
	
	public float SFXVolume = 1.0f;
	public float musicVolume = 0.15f;
	
	
	@Override
	public void create() {		
		
		// Obtain a reference to the Music object wrapped with the application
		musicPlayer = Gdx.audio.newMusic(Gdx.files.internal("data/music/nowhere_but_up.mp3"));
		
		// Confirm that the music should loop
		musicPlayer.setLooping(true);
		
		// Set the volume to be very quiet
		musicPlayer.setVolume(musicVolume);
		
		// Only play the music if you are NOT in developer mode
		if(!DEVELOPER_MODE){
			
			// Start playing the music as soon as the Game begins 
			musicPlayer.play();
		}
		
		// Set the screen to the SplashScreen initially
		setScreen(new SplashScreen(this));
		
		// Assign the input processor to the one designed for the Splash Screen
		Gdx.input.setInputProcessor(new GestureDetector(new SplashScreenInputProcessor(this)));
	}
	
	@Override
	public void dispose() {
		
		// Remember to dispose the objects allocated within this class
		musicPlayer.dispose();
		currentScreen.dispose();
		
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
	
	/**
	 * Method that handles the proper switching of screens.  It enforces the restriction that the
	 * currentScreen variable must be assigned to the Screen that is being displayed now.
	 * 
	 * @param screen The new Screen that should be shown.
	 */
	public void setScreen(PerspectiveScreen screen){
		
		this.currentScreen = screen;
		super.setScreen(screen);
	}
	
	/**
	 * Provides visibility to the current Screen that is being shown.
	 * 
	 * @return The currently-shown Screen.
	 */
	public Screen getScreen(){
		
		return currentScreen;
	}
	
	public int getTime()
	{
		return this.time;
	}
	
	public void decTime()
	{
		this.time--;
	}
	
	public void addTime(int add)
	{
		this.time+=add;
	}
	
	public void continueTime(float delta)
	{
		this.elapsed+=delta;
		
		if(this.elapsed>=1)
		{
			this.decTime();
			this.elapsed = 0;
		}
		
		if(this.getTime()==0)
			this.addTime(59);
	}
	
	public void setTime(int time)
	{
		this.time = time;
	}

	@Override
	public void write(Json json) {
		json.writeValue(TIME, this.getTime());
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
	}
	
	public Music getMusicPlayer(){
		return musicPlayer;
	}
}
