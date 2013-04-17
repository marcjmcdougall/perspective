package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.LevelManager;

public class LoadingScreen extends PerspectiveScreen {

	private LevelManager manager;
	
//	private GameScreen2D[] screens;
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
		
		counter = 1;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		
		if (counter < 6) {
			manager.get2DScreen(counter).show();
			manager.get2DScreen(counter).resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			manager.get2DScreen(counter).render(delta);
			
			batch.begin();
			black.draw(batch, "Loading...", Gdx.graphics.getWidth()/2 - 55, Gdx.graphics.getHeight() - 50);
			batch.end();
			
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			textures[counter] = manager.getScreenshot();
			counter++;
		}
		// done generating screens 
		else if (counter == 6){
			manager.loadScreens(textures);
			manager.showScreen();
		}
	}
}
