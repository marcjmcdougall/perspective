package com.cap4053.perspective.models2D;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.cap4053.perspective.screens.GameScreen2D;

public abstract class PerspectiveObject extends Image {

	public final float SQUARE_DIMENSION = 40.0f;
	
	private int row;
	private int column;
	
	public PerspectiveObject(Texture texture, int row, int column){
		
		super(texture);
		
		this.row = row;
		this.column = column;
		
		setWidth(SQUARE_DIMENSION);
		setHeight(SQUARE_DIMENSION);
		
		updateXYLocation();
	}
	
	public String getSimpleName(){
		
		return getClass().getSimpleName();
	}
	
	private void updateXYLocation(){
		
		setX(GameScreen2D.HORIZONTAL_MARGIN + SQUARE_DIMENSION * column);
		setY(GameScreen2D.VERTICAL_MARGIN + SQUARE_DIMENSION * row);
		
//		DEBUG
//		System.out.println("Animate to new location");
	}
	
	/**
	 * @return the row
	 */
	public int getRow() {
		
		return row;
	}

	/**
	 * @param row the row to set
	 */
	protected void setRow(int row) {
		
		this.row = row;
		
		updateXYLocation();
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		
		return column;
	}

	/**
	 * @param column the column to set
	 */
	protected void setColumn(int column) {
		
		this.column = column;
		
		updateXYLocation();
	}
}
