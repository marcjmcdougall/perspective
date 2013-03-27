package com.cap4053.perspective.models2D;

import com.badlogic.gdx.scenes.scene2d.Action;

public class Game2DMoveCompletedAction extends Action {

	private PerspectiveObject object;
	
	private int column;
	private int row;
	
	public Game2DMoveCompletedAction(PerspectiveObject object, int column, int row){
		
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
