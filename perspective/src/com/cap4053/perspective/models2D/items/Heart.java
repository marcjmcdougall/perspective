package com.cap4053.perspective.models2D.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.models2D.PerspectiveItem;

public class Heart extends PerspectiveItem {

	private Heart(Texture texture, int row, int column, Plane level2D) {
		
		super(texture, row, column, level2D);
	}
	
	public static Heart create(int row, int column, Plane level2D){
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "**CREATING NOW**");
		
		Texture texture = new Texture(Gdx.files.internal("data/items/item_heart.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return new Heart(texture, row, column, level2D);
	}

	@Override
	public void onMoveOver(Avatar collidedObject) {
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "**Moved Over Heart**");
		
		collidedObject.setHealth(collidedObject.getHealth() + 1);
		
		super.onMoveOver(collidedObject);
	}
	
	@Override
	public Drawable getZenDrawable() {
		
		TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("data/items/alternate_heart.png")));
		
		TextureRegionDrawable drawable = new TextureRegionDrawable(tr);
		
		return drawable;
	}
}
