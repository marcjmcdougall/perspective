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

public class Star extends PerspectiveItem {

	private Star(Texture texture, int row, int column, Plane level2D) {
		
		super(texture, row, column, level2D);
	}

	public static Star create(int row, int column, Plane level2D){
		
		Texture texture = new Texture(Gdx.files.internal("data/Items/item_star.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return new Star(texture, row, column, level2D);
	}
	
	
	@Override
	public void onMoveOver(Avatar collidedObject) {
		
		// TODO Star-specific implementation
		Gdx.app.log(Perspective.TAG, "**Moved Over Star**");
		System.out.println("Star: " + collidedObject);
		
		collidedObject.addStar(this);

		super.onMoveOver(collidedObject);
	}

	@Override
	public Drawable getZenDrawable() {
		
		TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("data/Items/alternate_star.png")));
		
		TextureRegionDrawable drawable = new TextureRegionDrawable(tr);
		
		return drawable;
	}
}
