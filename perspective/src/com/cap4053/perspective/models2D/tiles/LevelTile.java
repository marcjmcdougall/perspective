package com.cap4053.perspective.models2D.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.screens.SummaryScreen;

public class LevelTile extends Tile {
	
	private boolean isLevelingUp;

	private LevelTile(Texture texture, int row, int column, Plane level2D) {
		
		super(texture, row, column, level2D);
		this.isLevelingUp = false;
	}
	
	public static LevelTile create(int row, int column, Plane level2D, String imageName){
		
		String path = "data/tiles/" + imageName;
		
		Texture texture = new Texture(Gdx.files.internal(path));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		LevelTile output = new LevelTile(texture, row, column, level2D);
		
		output.setCanMoveTo(true);
		
		return output;
	}
	
	public void onMoveOver(Avatar collidedObject){
		// Prevents being called multiple times
		if(!this.isLevelingUp){
			this.isLevelingUp = true;
			
			int stars = collidedObject.getManager().getStars().size();
			int hearts = collidedObject.getManager().getHearts().size();
			SummaryScreen summary = SummaryScreen.create(this.plane, stars, hearts);
			
			summary.addAction(Actions.alpha(0.0f));
			this.plane.getStage().addActor(summary);
			summary.addAction(Actions.alpha(1, 0.7f));
			
			
			
			System.out.println("LEVEL UP!");
			
			
		}
	}
}
