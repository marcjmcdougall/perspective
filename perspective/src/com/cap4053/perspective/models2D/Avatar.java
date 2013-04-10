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

/**
 * Class representing the character that is being played during the game.  Note that character and 
 * Avatar are used interchangeably.
 *  
 * @author Marc J. McDougall
 */
public class Avatar extends PerspectiveObject {

	// Static variable defining how quickly the avatar should progress through one square on the board
	private static final float DURATION_PER_SQUARE = 0.25f;
	
	// The interpolation to use when moving.
	private static final Interpolation INTERPOLATOR = Interpolation.linear;
	
	// Static variable detailing the total number of stars that can be obtained
	private static final int MAX_STARS = 4;
	
	// Static variable detailing the total health that can be obtained
	private static final int MAX_HEALTH = 3;
	
	// The current plane the Avatar is being rendered onto
	private Plane currentPlane;
	
	// A reference to the LevelManager class
	private LevelManager manager;
	
	// A reference to the collection object that contains the various stars
	private PerspectiveCollection starCollection;
	
	// A reference to the collection object that contains the various hearts
	private PerspectiveCollection heartCollection;
	
	// Boolean determining if the Avatar is moving
	private boolean isMoving;
	
	/**
	 * Main constructor - used to create a new Avatar object.  Note that this is private and should never be called
	 * directly.
	 * 
	 * @param texture The texture that should be used to draw the Avatar.
	 * @param row This is the row that the character should start at.
	 * @param column This is the column that the character should start at.
	 * @param level2D This is the current Plane that the character should be displayed onto.
	 * @param manager The manager that controls the movement of the character.
	 */
	private Avatar(Texture texture, int row, int column, Plane level2D, LevelManager manager) {
		
		// Call the super constructor
		super(texture, row, column, level2D);	
		
		// Assign the constructor parameters to the class variables
		this.currentPlane = level2D;
		this.manager = manager;

		// Adding item collection (stars)
		this.starCollection = PerspectiveCollection.create(level2D, "Stars", 
				manager.getStars().size(), MAX_STARS, 0, 0, true, 64, 5);
		
		// Adding item collection (hearts)
		this.heartCollection = PerspectiveCollection.create(level2D, "Hearts", 
				manager.getHearts().size(), MAX_HEALTH, 20, 50, false, 32, 5);
	}
	
	/**
	 * Static factory method that defines a new Avatar.  This is necessary to mandate that 
	 * the Texture is assigned appropriately.
	 * 
	 * @param row The row on which to initialize the Avatar.
	 * @param column The column on which to initialize the Avatar.
	 * @param level2D The plane on which to initialize the Avatar.
	 * @param manager The manager responsible for holding together the character information.
	 * @return A properly-constructed Avatar.
	 */
	public static Avatar create(int row, int column, Plane level2D, LevelManager manager){
		
		// Create the new Texture 
		Texture texture = new Texture(Gdx.files.internal("data/zen.png"));
		
		// Set the Linear filter for proper interpolation
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		// Return the Texture
		return new Avatar(texture, row, column, level2D, manager);
	}
	
	/**
	 * Add a heart to the board.
	 * 
	 * @param heart The heart to add to the board.
	 */
	public void addHeart(Heart heart) {
		
		if(!manager.getHearts().contains(heart)){
			
			manager.getHearts().add(heart);
			heartCollection.update(manager.getHearts().size());
		}
	}
	
	/**
	 * Add a heart to the board.
	 * 
	 * @param heart The heart to add to the board.
	 */
	public void addStar(Star star) {
		
		if(!manager.getStars().contains(star)){
			
			manager.getStars().add(star);
			starCollection.update(manager.getStars().size());
		}
	}
	
	/**
	 * Provides visibility to the current Plane.
	 * 
	 * @return The current Plane.
	 */
	public Plane getCurrentPlane(){
		
		return currentPlane;
	}
	
	/**
	 * Allows the setting of the current plane.
	 * 
	 * @param plane The Plane to set.
	 */
	public void setCurrentPlane(Plane plane){
		
		this.currentPlane = plane;
	}

	/**
	 * Method that attempts to move the character to a certain location.
	 * 
	 * @param newRow The target row.
	 * @param newColumn The target column.
	 * @param path The path to follow.
	 * @param items The items on the board currently.
	 */
	public void moveTo(int newRow, int newColumn, ArrayList<SimpleCoordinate> path, ArrayList<PerspectiveItem>items){
		
		// If the Avatar is not moving...
		if(!this.isMoving){
			
			// Specify that he is moving now.
			this.isMoving = true;
			
			// Initialize a simple cursor that will hold the location information
			SimpleCoordinate cursor = null;
			
			// Create an Action sequence that will encapsulate the movement to another coordinate.
			SequenceAction sequence = new SequenceAction();
			
			for(int i = 0; i < path.size(); i++){
				
				// Update the cursor 
				cursor = path.get(i);
				
				// Specify the target row as the row of the next value in the ArrayList
				int targetRow = cursor.getRow();
				
				// Specify the target column as the row of the next value in the ArrayList
				int targetColumn = cursor.getColumn();
				
				// Derive the target X and Y values
				float targetX = GameScreen2D.HORIZONTAL_MARGIN + SQUARE_DIMENSION * cursor.getColumn();
				float targetY = GameScreen2D.VERTICAL_MARGIN + SQUARE_DIMENSION * cursor.getRow();
				
				// Create a new MoveToAction
				AvatarMoveToAction m = new AvatarMoveToAction(this, items, targetRow, targetColumn);
				
				// Set that MoveToAction's position to the targetX and targetY (derived previously)
				m.setPosition(targetX, targetY);
				
				// Set the duration equal to the duration per square value
				m.setDuration(DURATION_PER_SQUARE);
				
				// Set the interpolation as defined earlier
				m.setInterpolation(INTERPOLATOR);
				
				// Add this moveTo action to the SequenceAction
				sequence.addAction(m);
			}
				
			// Create a new CompletedAction and assign the context to this
			CompletedAction complete = new CompletedAction(this);
			
			// Set the target column and row as necessary
			complete.setTargetColumn(newColumn);
			complete.setTargetRow(newRow);
			
			// Add the CompletedAction to the SequenceAction
			sequence.addAction(complete);
			
			// Add a new runnable to the Sequence
			sequence.addAction(Actions.run(
		            new Runnable(){
		                public void run () {
		                	isMoving = false;
		            }}));  
			
			// Add the sequence as an Action on the Avatar Actor
			this.addAction(sequence);
		}
	}
	
	/**
	 * Callback used to specify what happens when the Avatar picks up and object.
	 * 
	 * @param item Specify the Item that was picked up.
	 */
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
	
	/**
	 * Provides visibility to the drawable for the Avatar.
	 * 
	 * @return A new drawable representing the Avatar.
	 */
	public Drawable getAvatarDrawable() {
		
		// Create a new TextureRegion
		TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("data/zen.png")));
		TextureRegionDrawable drawable = new TextureRegionDrawable(tr);
		
		
		// Return that drawable
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
