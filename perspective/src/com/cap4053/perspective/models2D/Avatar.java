package com.cap4053.perspective.models2D;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cap4053.perspective.backends.LevelManager;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.backends.SimpleCoordinate;
import com.cap4053.perspective.models2D.items.Heart;
import com.cap4053.perspective.models2D.items.Star;
import com.cap4053.perspective.screens.GameScreen2D;
import com.cap4053.perspective.view.AvatarMoveToAction;
import com.cap4053.perspective.view.CompletedAction;

public class Avatar extends PerspectiveObject {

	private static final float DURATION_PER_SQUARE = 0.25f;
	private static final Interpolation INTERPOLATOR = Interpolation.linear;
	
	/*
	private static final float TOTAL_DURATION = 0.25f;
	private static final Interpolation INTERPOLATOR = Interpolation.linear;
	private static final Interpolation INTERPOLATOR_START = Interpolation.swingIn;
	private static final Interpolation INTERPOLATOR_END = Interpolation.swingOut;*/
	
	private Plane currentPlane;
	private LevelManager manager;
	
	private static final int MAX_STARS = 4;
	private static final int MAX_HEALTH = 3;
	
	private PerspectiveCollection starCollection;
	private PerspectiveCollection heartCollection;
	
	private boolean isMoving;
	
	private Avatar(Texture texture, int row, int column, Plane level2D, LevelManager manager) {
		
		super(texture, row, column, level2D);
		
		this.currentPlane = level2D;
		
		this.currentPlane = level2D;
		this.manager = manager;
		
		// Adding item collection (stars)
		this.starCollection = PerspectiveCollection.create(level2D, "Stars", 
				manager.getStars().size(), MAX_STARS, 0, 0, true, 64, 5);
		
		this.heartCollection = PerspectiveCollection.create(level2D, "Hearts", 
				manager.getHearts().size(), MAX_HEALTH, 20, 50, false, 32, 5);
	}
	
	public static Avatar create(int row, int column, Plane level2D, LevelManager manager){
		
		Texture texture = new Texture(Gdx.files.internal("data/zen.png"));
		
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		return new Avatar(texture, row, column, level2D, manager);
	}
	
	public void addHeart(Heart heart) {
		if(!manager.getHearts().contains(heart)){
			manager.getHearts().add(heart);
			heartCollection.update(manager.getHearts().size());
		}
	}
	
	public void addStar(Star star) {
		if(!manager.getStars().contains(star)){
			manager.getStars().add(star);
			starCollection.update(manager.getStars().size());
		}
	}
	
	public Plane getCurrentPlane(){
		return currentPlane;
	}
	
	public void setCurrentPlane(Plane plane){
		this.currentPlane = plane;
	}

	public void moveTo(int newRow, int newColumn, ArrayList<SimpleCoordinate> path, ArrayList<PerspectiveItem>items){
		
		if(!this.isMoving){
			this.isMoving = true;
			
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
				
				/*
				// Calculate duration per square
				float dps = (TOTAL_DURATION/2);
				m.setDuration(dps);
				
				if(i == 0)
					m.setInterpolation(INTERPOLATOR_START);
				else if(i == path.size()-1)
					m.setInterpolation(INTERPOLATOR_END);
				else
					m.setInterpolation(INTERPOLATOR);*/
				
				sequence.addAction(m);
			}
				
			CompletedAction complete = new CompletedAction(this);
			complete.setTargetColumn(newColumn);
			complete.setTargetRow(newRow);
			
			sequence.addAction(complete);
			
			sequence.addAction(Actions.run(
		            new Runnable(){
		                public void run () {
		                	isMoving = false;
		            }}));  
			
			this.addAction(sequence);
		}
	}
	
	public void onPickUp(PerspectiveItem item){
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "**Updating drawable now**");
		
		final PerspectiveItem itemFinal = item;
		
		SequenceAction sequence = new SequenceAction();
		sequence.addAction(Actions.run(
	            new Runnable(){
	                public void run () {
	                	// Gets drawable item
	                	setDrawable(itemFinal.getZenDrawable());    
	            }}));
		sequence.addAction(Actions.delay(0.7f));
		sequence.addAction(Actions.run(
	            new Runnable(){
	                public void run () {
	                	// Gets Zen
	                    setDrawable(getAvatarDrawable());     
	            }}));  
		
		this.addAction(sequence);
		
	}
	
	public Drawable getBlankDrawable() {
		
		TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("data/LED-blank.png")));
		TextureRegionDrawable drawable = new TextureRegionDrawable(tr);
		
		return drawable;
	}
	
	public Drawable getAvatarDrawable() {
		
		TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("data/zen.png")));
		TextureRegionDrawable drawable = new TextureRegionDrawable(tr);
		
		return drawable;
	}
	
	public PerspectiveCollection getStarCollection() {
		return starCollection;
	}

	public void setStarCollection(PerspectiveCollection starCollection) {
		this.starCollection = starCollection;
	}

	public PerspectiveCollection getHeartCollection() {
		return heartCollection;
	}

	public void setHeartCollection(PerspectiveCollection heartCollection) {
		this.heartCollection = heartCollection;
	}
}
