package com.cap4053.perspective.models2D.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.cap4053.perspective.backends.Plane;

public class BlockTile extends Tile {

	private BlockTile(Texture texture, int row, int column, Plane level2D) {
		
		super(texture, row, column, level2D);
	}
	
	public static BlockTile create(int row, int column, Plane level2D){
		
		Texture texture = new Texture(Gdx.files.internal("data/tiles/tile_blocked.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		BlockTile output = new BlockTile(texture, row, column, level2D);
		
		return output;
	}
}
