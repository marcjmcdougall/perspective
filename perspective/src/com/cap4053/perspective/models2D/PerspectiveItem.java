package com.cap4053.perspective.models2D;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.cap4053.perspective.backends.Parser;
import com.cap4053.perspective.backends.Plane;

public abstract class PerspectiveItem extends PerspectiveObject {

	Parser p;
	
	public PerspectiveItem(Texture texture, int row, int column, Plane plane) {
		
		super(texture, row, column, plane);
		
		this.p = new Parser();
	}

	public abstract Drawable getZenDrawable();
	
	public void onMoveOver(Avatar collidedObject){
		
		remove();
		collidedObject.onPickUp(this);
	}
	
	@Override
	public boolean remove() {
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "**REMOVING NOW**");
		
		if(getParent() == null){
			
			return false;
		}
		else{
			
			// TODO: Re-implement later 
			p.removeItemAt(getRow(), getColumn(), plane);
			
			return super.remove();
		}
	}
}
