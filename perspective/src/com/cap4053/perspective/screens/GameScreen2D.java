package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.view.GameInputProcessor2D;

public class GameScreen2D extends PerspectiveScreen{

	public static final float HORIZONTAL_MARGIN = 100.0f;
	public static final float VERTICAL_MARGIN = 20.0f;
	
	private Plane level2D;
	
	public GameScreen2D(Perspective game) {
		
		super(game);
		
		String tileDescription = 	"F F B F F B F\n" +
									"F F B F F B F\n" +
									"F F B F F B F\n" +
									"F F B F F B F\n" +
									"B B B B B B B\n" +
									"F F F F B F F\n" +
									"F B B B B F F\n";
		
		level2D = new Plane();
		level2D.initialize(0, 0, tileDescription, "", stage);
	}
	
	@Override
	public void show() {
		
		super.show();
		
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
}
