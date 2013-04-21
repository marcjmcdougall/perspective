package com.cap4053.perspective.models2D.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.cap4053.perspective.backends.Plane;

public class FloorTile extends Tile {

	private FloorTile(Texture texture, int row, int column, Plane level2D) {
		
		super(texture, row, column, level2D);
	}
	
	public static FloorTile create(int row, int column, Plane level2D, String imageName){
		
		String path = "data/tiles/" + imageName;
		
		Texture texture = new Texture(Gdx.files.internal(path));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		FloorTile output = new FloorTile(texture, row, column, level2D);
		
		output.setCanMoveTo(true);
		
		return output;
	}
}
