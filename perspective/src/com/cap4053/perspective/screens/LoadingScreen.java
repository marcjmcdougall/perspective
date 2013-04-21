package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.LevelManager;
import com.cap4053.perspective.backends.TimerManager;

public class LoadingScreen extends PerspectiveScreen {

	private LevelManager manager;
	
	private Texture[] textures;

	private SpriteBatch batch;
	private BitmapFont black;
	
	private int counter;
	
	public LoadingScreen(Perspective game, LevelManager manager) {
		
		super(game);
		
		this.manager = manager;

		textures = new Texture[6];
		
		batch = new SpriteBatch();
		black = new BitmapFont(Gdx.files.internal("data/blackfont.fnt"),false);
		
		counter = 0;
	}
	
	@Override
	public void render(float delta) {

		super.render(delta);
		
		TimerManager.shouldRunTimer = false;
		
		if (counter++ < 6) {
			manager.get2DScreen(counter % 6).show();
			manager.get2DScreen(counter % 6).resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			manager.get2DScreen(counter % 6).render(delta);
			
			batch.begin();
			black.draw(batch, "Loading...", Gdx.graphics.getWidth()/2 - 55, Gdx.graphics.getHeight() - 50);
			batch.end();
			
			if(!Perspective.DEVELOPER_MODE) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			textures[counter % 6] = manager.getScreenshot();
		}
		else { // done generating screens 
			manager.loadScreens(textures);
			manager.setScreen(0);
			manager.showScreen();
			
			TimerManager.shouldRunTimer = true;
		}
	}
}
