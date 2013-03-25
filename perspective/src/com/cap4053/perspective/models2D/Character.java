package com.cap4053.perspective.models2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Character extends PerspectiveImage {

	private Character(Texture texture, int row, int column) {
		
		super(texture, row, column);
	}
	
	public static Character create(int row, int column){
		
		Texture texture = new Texture(Gdx.files.internal("data/character.png"));
		
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return new Character(texture, row, column);
	}
}
