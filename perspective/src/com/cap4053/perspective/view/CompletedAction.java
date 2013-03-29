package com.cap4053.perspective.view;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.models2D.PerspectiveObject;

public class CompletedAction extends Action {

	private PerspectiveObject object;
	
	private int column;
	private int row;
	
	public CompletedAction(PerspectiveObject object){
		
		this.object = object;
		this.column = 0;
		this.row = 0;
	}
	
	public void setTargetRow(int row){
		
		this.row = row;
	}
	
	public void setTargetColumn(int column){
		
		this.column = column;
	}
	
	@Override
	public boolean act(float delta) {
		
		if(getActor().getClass().getSimpleName().equals(Avatar.class.getSimpleName())){
			
			object.setRow(row);
			object.setColumn(column);
		}
		
		return true;
	}
}
