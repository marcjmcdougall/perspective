package com.cap4053.perspective.models2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Block extends PerspectiveImage {

	private Block(Texture texture, int row, int column) {
		
		super(texture, row, column);
	}
	
	public static Block create(int row, int column){
		
		Texture texture = new Texture(Gdx.files.internal("data/tiles/tile_blocked.png"));
		
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return new Block(texture, row, column);
	}
}
