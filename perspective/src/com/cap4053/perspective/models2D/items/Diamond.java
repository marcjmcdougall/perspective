package com.cap4053.perspective.models2D.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.models2D.PerspectiveItem;

public class Diamond extends PerspectiveItem {

	private Diamond(Texture texture, int row, int column, Plane plane2D) {
		
		super(texture, row, column, plane2D);
	}

	public static Diamond create(int row, int column, Plane level2D){
	
		Texture texture = new Texture(Gdx.files.internal("data/items/item_diamond.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return new Diamond(texture, row, column, level2D);
	}
	
	@Override
	public void onMoveOver(Avatar collidedObject) {
		
		//TODO: Diamond-specific implementation
		
		super.onMoveOver(collidedObject);
	}

	@Override
	public Drawable getZenDrawable() {
		
		TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("data/items/alternate_diamond.png")));
		
		TextureRegionDrawable drawable = new TextureRegionDrawable(tr);
		
		return drawable;
	}
}
