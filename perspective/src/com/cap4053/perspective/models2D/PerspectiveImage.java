package com.cap4053.perspective.models2D;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.cap4053.perspective.screens.FlatGameScreen;

public abstract class PerspectiveImage extends Image {

	public final float SQUARE_DIMENSION = 40.0f;
	
	private int row;
	private int column;
	
	public PerspectiveImage(Texture texture, int row, int column){
		
		super(texture);
		
		this.row = row;
		this.column = column;
		
		setX(FlatGameScreen.HORIZONTAL_MARGIN + SQUARE_DIMENSION * column);
		setY(FlatGameScreen.VERTICAL_MARGIN + SQUARE_DIMENSION * row);
		
		setWidth(SQUARE_DIMENSION);
		setHeight(SQUARE_DIMENSION);
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
	public void setRow(int row) {
		
		this.row = row;
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
	public void setColumn(int column) {
		
		this.column = column;
	}
}
