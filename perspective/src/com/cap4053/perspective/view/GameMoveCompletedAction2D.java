package com.cap4053.perspective.view;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.cap4053.perspective.models2D.PerspectiveObject;

public class GameMoveCompletedAction2D extends Action {

	private PerspectiveObject object;
	
	private int column;
	private int row;
	
	public GameMoveCompletedAction2D(PerspectiveObject object, int column, int row){
		
		this.object = object;
		
		this.column = column;
		this.row = row;
	}
	
	@Override
	public boolean act(float delta) {
		
		object.setRow(row);
		object.setColumn(column);
		
		return false;
	}
}
