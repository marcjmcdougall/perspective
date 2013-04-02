package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
<<<<<<< HEAD
=======
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
>>>>>>> 3ad139898a4c29c2b850636956eaffa5d2490c3d
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.LevelManager;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.view.GameInputProcessor2D;

public class GameScreen2D extends GameScreen{

	public static final float HORIZONTAL_MARGIN = 20.0f;
	public static final float VERTICAL_MARGIN = 100.0f;
	
	private Plane level2D;
	private LevelManager manager;
	
	public GameScreen2D(Perspective game, Stage contextMenu, boolean characterActive, String tileDescription, String itemDescription, LevelManager manager) {
		
		super(game, contextMenu);
		
		this.manager = manager;
		
//		DEBUGS
//		Gdx.app.log(Perspective.TAG, "horizontal margin: " + HORIZONTAL_MARGIN + ", vertical margin: " + VERTICAL_MARGIN);
//		Gdx.app.log(Perspective.TAG, "width: " + Gdx.graphics.getWidth() + ", height: " + Gdx.graphics.getHeight());
		
		level2D = new Plane(stage, characterActive);
		
		level2D.initialize(0, 0, tileDescription, itemDescription);
	}
	
	@Override
	public void show() {
		
		super.show();
		
		// TODO: Set input processor to just the stage?
		Gdx.input.setInputProcessor(new GameInputProcessor2D(game, manager, this));
	}
	
	@Override
	public void hide() {
		
		// Do nothing
	}
	
	public void resize(int width, int height){
		
		super.resize(width, height);
	}
	
	/**
	 * @return the level2D
	 */
	public Plane getLevel2D(){
		
		return level2D;
	}

<<<<<<< HEAD
	/* getScreen() method moved to LevelManager.java */
=======
	public Texture getScreen(){
		
		TextureRegion front = ScreenUtils.getFrameBufferTexture((int) HORIZONTAL_MARGIN, 
																(int) VERTICAL_MARGIN,
																(int) PerspectiveObject.SQUARE_DIMENSION * 7, 
																(int) PerspectiveObject.SQUARE_DIMENSION * 7);
		return front.getTexture();
	}
>>>>>>> 3ad139898a4c29c2b850636956eaffa5d2490c3d
}
