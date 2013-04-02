package com.cap4053.perspective.models2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.Plane;

public abstract class PerspectiveItem extends PerspectiveObject {

	public PerspectiveItem(Texture texture, int row, int column, Plane level2D) {
		
		super(texture, row, column, level2D);
	}

	public abstract Drawable getZenDrawable();
	
	public void onMoveOver(Avatar collidedObject){
		
		//remove();
		levitateItem();
		
		// Changes avatar to item
		collidedObject.onPickUp(this);
	}
	
	@Override
	public boolean remove() {
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "**REMOVING NOW**");
		
		if(getParent() == null){
			
			return false;
		}
		else{
			
			level2D.getItems()[getRow()][getColumn()] = null;
			
			return super.remove();
		}
	}
	
	public void levitateItem(){
		
		this.addAction(Actions.moveTo(this.getX(), this.getY()+20, 0.35f, Interpolation.linear));
		
		SequenceAction sequence = new SequenceAction();
		sequence.addAction(Actions.fadeOut(0.5f));
		sequence.addAction(Actions.removeActor());
		
		this.addAction(sequence);
		
	}
}
