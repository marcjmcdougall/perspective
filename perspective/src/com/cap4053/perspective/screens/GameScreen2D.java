package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.models2D.PerspectiveObject;
import com.cap4053.perspective.view.GameInputProcessor2D;

public class GameScreen2D extends PerspectiveScreen{

	public static final float HORIZONTAL_MARGIN = 100.0f;
	public static final float VERTICAL_MARGIN = 20.0f;
	
	private Plane level2D;
	
	public GameScreen2D(Perspective game) {
		
		super(game);
		
		String tileDescription = 	"F F F B F F F\n" +
									"F B F B F B F\n" +
									"F B F B F B F\n" +
									"F B F B F B F\n" +
									"F B F B F B B\n" +
									"F B F B F B F\n" +
									"F B F F F B F\n";
		
		level2D = new Plane();
		level2D.initialize(0, 0, tileDescription, "", stage);
	}
	
	@Override
	public void show() {
		
		super.show();
		
		// TODO: Set input processor to just the stage?
		Gdx.input.setInputProcessor(new GameInputProcessor2D(game, this));
	}
	
	public void resize(int width, int height){
		
		super.resize(width, height);
	}

	/**
	 * @return the level2D
	 */
	public Plane getLevel2D() {
		
		return level2D;
	}

	public Texture getScreen() {
		TextureRegion front = ScreenUtils.getFrameBufferTexture((int)HORIZONTAL_MARGIN, (int)VERTICAL_MARGIN,
										(int)PerspectiveObject.SQUARE_DIMENSION * 7, (int)PerspectiveObject.SQUARE_DIMENSION * 7);
		return front.getTexture();
	}
}
