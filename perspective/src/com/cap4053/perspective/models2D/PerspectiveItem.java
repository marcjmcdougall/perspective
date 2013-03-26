package com.cap4053.perspective.models2D;

import com.badlogic.gdx.graphics.Texture;

public abstract class PerspectiveItem extends PerspectiveObject {

	public PerspectiveItem(Texture texture, int row, int column) {
		
		super(texture, row, column);
	}

	public abstract void onMoveOver();
}
