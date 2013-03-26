package com.cap4053.perspective.models2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Avatar extends PerspectiveObject {

	private Avatar(Texture texture, int row, int column) {
		
		super(texture, row, column);
	}
	
	public static Avatar create(int row, int column){
		
		Texture texture = new Texture(Gdx.files.internal("data/character.png"));
		
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return new Avatar(texture, row, column);
	}
	
	public void moveTo(int newRow, int newColumn){
		
		this.setRow(newRow);
		this.setColumn(newColumn);
	}
}
