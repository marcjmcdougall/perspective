package com.cap4053.perspective.models2D.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class FloorTile extends Tile {

	private FloorTile(Texture texture, int row, int column) {
		
		super(texture, row, column);
	}
	
	public static FloorTile create(int row, int column){
		
		Texture texture = new Texture(Gdx.files.internal("data/tiles/tile_blue.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		FloorTile output = new FloorTile(texture, row, column);
		
		output.setCanMoveTo(true);
		
		return output;
	}
}
