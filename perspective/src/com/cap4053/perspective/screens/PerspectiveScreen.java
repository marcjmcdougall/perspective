package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cap4053.perspective.Perspective;

public abstract class PerspectiveScreen implements Screen {

	protected Stage stage;
	protected Perspective game;
	
	public PerspectiveScreen(Perspective game){
		
		this.game = game;
		
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),true);
		
	}
	
	public String getName(){
		
		return getClass().getSimpleName();
	}
	
	@Override
	public void render(float delta) {
		
		stage.act(delta);
	
	    // clear the screen with the given RGB color (black)
	    Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
	    Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	
	    // draw the actors
	    stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
		stage.setViewport(width, height, true);
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "Resizing screen: " + getName() + " to: " + width + " x " + height );
	}

	@Override
	public void show() {
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "Showing screen: " + getName());
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		
		dispose();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
		stage.dispose();
	}
}
