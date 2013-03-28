package com.cap4053.perspective.models2D.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.cap4053.perspective.models2D.PerspectiveItem;
import com.cap4053.perspective.models2D.PerspectiveObject;

public class Star extends PerspectiveItem {

	private Star(Texture texture, int row, int column) {
		
		super(texture, row, column);
	}

	public static Star create(int row, int column){
		
		Texture texture = new Texture(Gdx.files.internal("data/items/item_star.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return new Star(texture, row, column);
	}
	
	
	@Override
	public void onMoveOver(PerspectiveObject collidedObject) {
		
		// TODO Implementation
	}
}
