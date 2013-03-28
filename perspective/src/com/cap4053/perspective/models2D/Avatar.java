package com.cap4053.perspective.models2D;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.backends.SimpleCoordinate;
import com.cap4053.perspective.screens.GameScreen2D;
import com.cap4053.perspective.view.AvatarMoveToAction;
import com.cap4053.perspective.view.CompletedAction;

public class Avatar extends PerspectiveObject {

	private static final float DURATION_PER_SQUARE = 0.50f;
	private static final Interpolation INTERPOLATOR = Interpolation.swing;
	
	private static final int MAX_HEALTH = 10;
	
	private int health;
	
	private Avatar(Texture texture, int row, int column, Plane level2D) {
		
		super(texture, row, column, level2D);
		
		this.health = MAX_HEALTH;
	}
	
	public static Avatar create(int row, int column, Plane level2D){
		
		Texture texture = new Texture(Gdx.files.internal("data/zen.png"));
		
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return new Avatar(texture, row, column, level2D);
	}
	
	/**
	 * @return the health
	 */
	public int getHealth() {
		
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		
		this.health = health;
	}

	public void moveTo(int newRow, int newColumn, ArrayList<SimpleCoordinate> path, PerspectiveItem[][] items){
		
		SimpleCoordinate cursor = null;
		
		SequenceAction sequence = new SequenceAction();
		
		for(int i = 0; i < path.size(); i++){
			
			cursor = path.get(i);
			
			int targetRow = cursor.getRow();
			int targetColumn = cursor.getColumn();
			
			float targetX = GameScreen2D.HORIZONTAL_MARGIN + SQUARE_DIMENSION * cursor.getColumn();
			float targetY = GameScreen2D.VERTICAL_MARGIN + SQUARE_DIMENSION * cursor.getRow();
			
			AvatarMoveToAction m = new AvatarMoveToAction(this, items, targetRow, targetColumn);
			
			m.setPosition(targetX, targetY);
			m.setDuration(DURATION_PER_SQUARE);
			m.setInterpolation(INTERPOLATOR);
			
			sequence.addAction(m);
		}
			
		CompletedAction complete = new CompletedAction(this);
		complete.setTargetColumn(newColumn);
		complete.setTargetRow(newRow);
		
		sequence.addAction(complete);
		
		this.addAction(sequence);
	}
	
	public void onPickUp(PerspectiveItem item){
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "**Updating drawable now**");
		
		this.setDrawable(item.getZenDrawable());
	}
}
