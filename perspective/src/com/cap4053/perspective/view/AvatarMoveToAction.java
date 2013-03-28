package com.cap4053.perspective.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.models2D.PerspectiveItem;

public class AvatarMoveToAction extends MoveToAction {

	Avatar avatar;
	PerspectiveItem items[][];
	
	int immediateRow;
	int immediateColumn;
	
	public AvatarMoveToAction(Avatar avatar, PerspectiveItem items[][], int immediateRow, int immediateColumn){
		
		super();
		
		this.avatar = avatar;
		this.items = items;
		
		this.immediateRow = immediateRow;
		this.immediateColumn = immediateColumn;
	}
	
	@Override
	public boolean act(float delta) {
		
		super.act(delta);
		
		PerspectiveItem item = detectCollision();
		
		if(item != null){
			
//			DEBUG
			Gdx.app.log(Perspective.TAG, "Item Detected: " + item.getClass().getSimpleName());
			
			item.onMoveOver(avatar);
		}
		
		return super.act(delta);
	}
	
	private PerspectiveItem detectCollision(){
		
		return items[immediateRow][immediateColumn];
	}
}
