package com.cap4053.perspective.screens;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.LevelManager;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.models2D.PerspectiveItem;
import com.cap4053.perspective.models2D.PerspectiveObject;
import com.cap4053.perspective.models2D.tiles.Tile;
import com.cap4053.perspective.view.GameInputProcessor2D;

public class GameScreen2D extends PerspectiveScreen{

	public static float HORIZONTAL_MARGIN = 20.0f;
	public static float VERTICAL_MARGIN = 100.0f;
	
	private Plane level2D;
	private LevelManager manager;
	
	public GameScreen2D(Perspective game, boolean characterActive, String tileDescription, String itemDescription, LevelManager manager) {
		
		super(game);
		
		this.manager = manager;
		
		updateScreenDimensions();
			
//		DEBUGS
		Gdx.app.log(Perspective.TAG, "horizontal margin: " + HORIZONTAL_MARGIN + ", vertical margin: " + VERTICAL_MARGIN);
		Gdx.app.log(Perspective.TAG, "width: " + Gdx.graphics.getWidth() + ", height: " + Gdx.graphics.getHeight());
		
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
		
		updateScreenDimensions();
		
		super.resize(width, height);
	}
	
	private void updateScreenDimensions(){
		
		HORIZONTAL_MARGIN = (Gdx.graphics.getWidth() - (7 * PerspectiveObject.SQUARE_DIMENSION)) / 2.0f;
		VERTICAL_MARGIN = (Gdx.graphics.getHeight() - (7 * PerspectiveObject.SQUARE_DIMENSION)) / 2.0f;
	}

	/**
	 * @return the level2D
	 */
	public Plane getLevel2D(){
		
		return level2D;
	}

	public Texture getScreen(){
		
		TextureRegion front = ScreenUtils.getFrameBufferTexture((int) HORIZONTAL_MARGIN, 
																(int) VERTICAL_MARGIN,
																(int) PerspectiveObject.SQUARE_DIMENSION * 7, 
																(int) PerspectiveObject.SQUARE_DIMENSION * 7);
		
		return front.getTexture();
	}
}
