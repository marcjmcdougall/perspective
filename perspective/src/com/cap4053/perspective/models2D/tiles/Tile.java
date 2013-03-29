package com.cap4053.perspective.models2D.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.models2D.PerspectiveObject;

public abstract class Tile extends PerspectiveObject {

	private boolean canMoveTo;
	
	public Tile(Texture texture, int row, int column, Plane level2D) {
		
		super(texture, row, column, level2D);
	}

	/**
	 * @return the permitsMovement
	 */
	public boolean canMoveTo() {
		
		return canMoveTo;
	}

	/**
	 * @param canMoveTo the permitsMovement to set
	 */
	public void setCanMoveTo(boolean canMoveTo) {
		
		this.canMoveTo = canMoveTo;
	}
}
