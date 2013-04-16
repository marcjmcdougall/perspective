package com.cap4053.perspective.models2D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.screens.GameScreen2D;

public abstract class PerspectiveObject extends Image {

	public final static float SQUARE_DIMENSION = 40.0f;
	
	protected Plane plane;
	
	private int row;
	private int column;
	
	public PerspectiveObject(Texture texture, int row, int column, Plane plane){
		
		super(texture);
		
		this.row = row;
		this.column = column;
		this.plane = plane;
		
		setWidth(SQUARE_DIMENSION);
		setHeight(SQUARE_DIMENSION);
		
		updateXYLocation();
	}
	
	public String getSimpleName(){
		
		return getClass().getSimpleName();
	}
	
	public void updateXYLocation(){
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "**Updating XY Location For " + getClass().getSimpleName() + " Now**");
		
		setX(GameScreen2D.HORIZONTAL_MARGIN + SQUARE_DIMENSION * column);
		setY(GameScreen2D.VERTICAL_MARGIN + SQUARE_DIMENSION * row);
	}
	
	public void onMoveOver(Avatar avatar){
		
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
	public void setColumn(int column) {
		
		this.column = column;
		
		updateXYLocation();
	}
}
