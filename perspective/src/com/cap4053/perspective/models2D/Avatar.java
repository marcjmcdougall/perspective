package com.cap4053.perspective.models2D;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.cap4053.perspective.screens.GameScreen2D;
import com.cap4053.perspective.view.GameMoveCompletedAction2D;

public class Avatar extends PerspectiveObject {

	private Avatar(Texture texture, int row, int column) {
		
		super(texture, row, column);
	}
	
	public static Avatar create(int row, int column){
		
		Texture texture = new Texture(Gdx.files.internal("data/character.png"));
		
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return new Avatar(texture, row, column);
	}
	
	public void moveTo(int newRow, int newColumn){
		
		MoveToAction moveTo = new MoveToAction();
		moveTo.setDuration(1.0f);
		moveTo.setPosition(GameScreen2D.HORIZONTAL_MARGIN + SQUARE_DIMENSION * newColumn, GameScreen2D.VERTICAL_MARGIN + SQUARE_DIMENSION * newRow);
		moveTo.setInterpolation(Interpolation.pow2);
		
		GameMoveCompletedAction2D completed = new GameMoveCompletedAction2D(this, newColumn, newRow);
		
		SequenceAction sequence = new SequenceAction();
		sequence.addAction(moveTo);
		sequence.addAction(completed);
		
		// Still need to calculate path and appropriate timing
		this.addAction(sequence); 
	}
}
