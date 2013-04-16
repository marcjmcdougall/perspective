package com.cap4053.perspective.backends;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.models2D.PerspectiveItem;
import com.cap4053.perspective.models2D.PerspectiveObject;
import com.cap4053.perspective.models2D.items.Diamond;
import com.cap4053.perspective.models2D.items.Heart;
import com.cap4053.perspective.models2D.items.Star;
import com.cap4053.perspective.models2D.tiles.BlockTile;
import com.cap4053.perspective.models2D.tiles.FloorTile;
import com.cap4053.perspective.models2D.tiles.LevelTile;
import com.cap4053.perspective.models2D.tiles.Tile;

/**
 * Class that represents a Plane on a Cube.  This class enforces all the restrictions of the associated 2D level.  The 
 * majority of the game logic is handled in here.  
 * 
 * @author Marc J McDougall
 */
public class Plane {
	
	// A class variable defining the total number of Tiles on each Plane
	public static final int DIMENSION = 7;
	
	// ArrayList representing the Tile objects on the Plane
	private ArrayList<Tile> tiles;
	
	// ArrayList representing the Item objects on the Plane
	private ArrayList<PerspectiveItem> items;
	
	// A reference to the Avatar
	private Avatar character;
	
	// A reference to the stage that will be used to draw this plane
	private Stage stage;
	
	// A reference to a Parser object that is used as an aide in parsing the Tile and Item maps
	private Parser p;
	
	// A variable defining the state if the character (currently binary, either on or off the Screen)
	private boolean characterState;
	
	private LevelManager manager;
	
	/**
	 * Simple constructor.  Creates a new Plane object.
	 * 
	 * @param stage The associated Stage.
	 * @param characterState The initial state of the Avatar.
	 */
	public Plane(Stage stage, LevelManager manager, boolean characterState){
		
		// Initialize new objects as needed
		this.tiles = new ArrayList<Tile>();
		this.items = new ArrayList<PerspectiveItem>();
		this.p = new Parser();
		this.manager = manager;
		
		// Assign the parameters to their class variable equivalents
		this.stage = stage;
		this.characterState = characterState;
		
		Texture texture = new Texture(Gdx.files.internal("data/Plane-BG.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Image bgImage = new Image(texture);
		
		this.stage.addActor(bgImage);
		
	}
	
	/**
	 * Setter for the character state.  Defines whether or not to 
	 * render the character object on the Plane.
	 * 
	 * @param newState The new state of the Avatar (character).
	 */
	public void setCharacterState(boolean newState){
		
		this.characterState = newState;
		if(newState)
			Gdx.app.log(Perspective.TAG,"character state true");
		else
			Gdx.app.log(Perspective.TAG,"character state false");
		
		// Callback that adds and removes the Character Actor as necessary
		onCharacterStateChange();
	}
	
	/**
	 * Private helped callback that removes the character as an Actor when necessary.
	 */
	private void onCharacterStateChange(){
		
		if(this.character.getStarCollection() != null){
			this.character.getStarCollection().update(this.manager.getStars().size());
		}
		
		if(this.character.getHeartCollection() != null){
			this.character.getHeartCollection().update(this.manager.getHearts().size());
		}
		
		// If the character should be displayed...
		if(characterState){
		
			// Then, add it as an Actor
			stage.addActor(character);
		}
		// Otherwise...
		else{
			
			// Remove it as an Actor
			character.remove();
			stage.draw();
		}
	}
	
	/**
	 * Initialization method that sets all the values to their starting states, will return the Avatar to use on other planes
	 * 
	 * @param characterStartingRow The initial row of the character on this Plane
	 * @param characterStartingColumn The initial column of the character on this Plane
	 * @param tileMap A String representing the Tile objects on the Plane
	 * @param itemMap A String representing the Item objects on the Plane.
	 */
	public void initialize(int characterStartingRow, int characterStartingColumn, String tileMap, String itemMap){
		
		// Helper method that unwraps the String for the tiles and creates the ArrayList
		parseTilesAsString(tileMap);
		
		// Finds the Tile that the character is currently standing on
		Tile characterTile = p.findTileAt(characterStartingRow, characterStartingColumn, this);
		
		// If this value is not null...
		if(characterTile != null){
			
			// If it is possible to move to that Tile...
			if(characterTile.canMoveTo()){
				
				// Again, only print out debug statements while in the development mode
				if(Perspective.DEVELOPER_MODE){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "Creating character now");
				}
				
				// Create the new character
				this.character = Avatar.create(characterStartingRow, characterStartingColumn, this, this.manager);
			}
		}
		
		// Helper method that unwraps the String for the items and creates the ArrayList
		parseItemsAsString(itemMap);
	
		// Again, only print out debug statements while in the development mode
		if(Perspective.DEVELOPER_MODE){
		
//			DEBUG
			Gdx.app.log(Perspective.TAG, "About to add tiles and items");
		}
		
		// Obtain an Iterator that will parse the ArrayList of Tile objects
		Iterator<Tile> tileIter = tiles.iterator();
		
		// While there is something to parse...
		while(tileIter.hasNext()){
			
			// Add the Tile objects to the Stage
			stage.addActor(tileIter.next());
		}
		
		// Obtain an Iterator that will parse the ArrayList of PerspectiveItem objects
		Iterator<PerspectiveItem> itemIter = items.iterator();
		
		// While there is something to parse...
		while(itemIter.hasNext()){
			
			// Add the Item objects to the Stage
			stage.addActor(itemIter.next());
		}
		
		// If the character is on this Plane...
		if(characterState){
			
			// Add it to the Stage also
			stage.addActor(character);
		}
		
		
		
	}

	/**
	 * Callback for touch events that hit this Stage.
	 * 
	 * @param x The x-coordinate of the touch event.
	 * @param y The y-coordinate of the touch event.
	 * @param stage The Stage that should be analyzed.
	 */
	public void onTouch(float x, float y, Stage stage){
		
		// Obtain the object that was hit by this touch
		PerspectiveObject actor = null;
		
		try{
			actor = (PerspectiveObject) stage.hit(x, y, false);
		}
		catch(ClassCastException e){
			// Item is collection object
		}
		
		// If this value is not null...
		if(actor != null){
		
			// Get the associated row and column of the Actor
			int row = actor.getRow();
			int column = actor.getColumn();
		
			// Move the Character to that row and column
			moveCharacter(row, column);
			
			// Again, only print out debug statements while in the development mode
			if(Perspective.DEVELOPER_MODE){
				
//				DEBUG
//				Gdx.app.log(Perspective.TAG, "Tile Touch Detected!  Actor: " + actor.getSimpleName() + " Row: " + actor.getRow() + " Column: " + actor.getColumn());
			}
		}
	}
	
	/**
	 * Method that "abruptly" moves the Avatar to a specific row and column.  This is necessary in case the user wants to speed
	 * up the animation, or for just programatically setting the character position.
	 * 
	 * @param newRow The desired row.
	 * @param newColumn The desired column.
	 */
	public void abruptlyMoveCharacter(int newRow, int newColumn){
		
		// Simply set the characters row and column accordingly
		character.setRow(newRow);
		character.setColumn(newColumn);
	}
	
	/**
	 * Method that moves the Avatar according to the method defined in "AvatarMoveToAction.java" class.
	 * 
	 * @param newRow The desired row.
	 * @param newColumn The desired column.
	 */
	public void moveCharacter(int newRow, int newColumn){
		
		// Create a new temporary Validator that will be used for path determination
		Validator v = new Validator();
		
		// Create a temporary ArrayList that will contain the path returned from the Validator
		ArrayList<SimpleCoordinate> path;
		
		// If this is a valid movement...
		if(v.validateAvatarMove(newRow, newColumn, character, this)){
			
			// Again, only print out debug statements while in the development mode
			if(Perspective.DEVELOPER_MODE){
				
//				DEBUG
//				Gdx.app.log(Perspective.TAG, "**Attempting to move character now**");
			}
			
			// Obtain the path used to reach this location
			path = v.getPath();
			
			// Move to that location using the given path
			character.moveTo(newRow, newColumn, path, items);
		}
	}
	
	/**
	 * Helper method that parses the input String and populates the Tile ArrayList.  The implementation of 
	 * this method should not be of your concern.
	 * 
	 * @param input The String that represents the Tiles.
	 */
	private void parseTilesAsString(String input){
		
		int row = 6;
		int column = 0;
		
		Scanner scan = new Scanner(input);
		Scanner lineScanner = null;
		
		while(scan.hasNextLine()){
			
			String nextLine = scan.nextLine();
			
			lineScanner = new Scanner(nextLine);
			
			while(lineScanner.hasNext()){
				
				String cursor = lineScanner.next();
				
				if(cursor.equals("B")){
					
					tiles.add(BlockTile.create(row, column, this));
				}
				else if(cursor.equals("F")){
					
					tiles.add(FloorTile.create(row, column, this));
				}
				
				else if(cursor.equals("P")){
					
					tiles.add(LevelTile.create(row, column, this, "tile_purple.png"));
				}
				
				column ++;
			}
			
			row--;
			column = 0;
		}
		
		scan.close();
		lineScanner.close();
	}
	
	/**
	 * Helper method that parses the input String and populates the Item ArrayList.  The implementation of 
	 * this method should not be of your concern.
	 * 
	 * @param input The String that represents the PerspectiveItems.
	 */
	private void parseItemsAsString(String input){
		
		int row = 6;
		int column = 0;
		
		Scanner scan = new Scanner(input);
		Scanner lineScanner = null;
		
		while(scan.hasNextLine()){
			
			String nextLine = scan.nextLine();
			
			lineScanner = new Scanner(nextLine);
			
			while(lineScanner.hasNext()){
				
				String cursor = lineScanner.next();
				
				if(cursor.equals("H")){
					
					items.add(Heart.create(row, column, this));
				}
				else if(cursor.equals("S")){
					
					items.add(Star.create(row, column, this));
				}
				else if(cursor.equals("D")){
					
					items.add(Diamond.create(row, column, this));
				}
				
				column ++;
			}
			
			row--;
			column = 0;
		}
		
		scan.close();
		lineScanner.close();
	}
	
	// called from GameScreen2D.java when the screen is resized
	public void onResize(){
		// update Zen's location
		character.updateXYLocation();
		
		// Obtain an Iterator that will parse the ArrayList of Tile objects
		Iterator<Tile> tileIter = tiles.iterator();
		
		// While there is something to parse...
		while(tileIter.hasNext()){
			
			// Update the location of the tile
			tileIter.next().updateXYLocation();
		}
		
		// Obtain an Iterator that will parse the ArrayList of PerspectiveItem objects
		Iterator<PerspectiveItem> itemIter = items.iterator();
		
		// While there is something to parse...
		while(itemIter.hasNext()){
			
			// Update the location of the item
			itemIter.next().updateXYLocation();
		}
	}
	
	
	
	/**
	 * Provides visibility to the Tile ArrayList.
	 * 
	 * @return The Tile ArrayList.
	 */
	public ArrayList<Tile> getTiles() {
		
		return tiles;
	}

	/**
	 * Setter method for the Tile ArrayList.
	 * 
	 * @param tiles The tiles to set.
	 */
	public void setTiles(ArrayList<Tile> tiles) {
		
		this.tiles = tiles;
	}

	/**
	 * Provides visibility to the Item ArrayList.
	 * 
	 * @return The PerspectiveItem ArrayList.
	 */
	public ArrayList<PerspectiveItem> getItems() {
		
		return items;
	}

	/**
	 * Setter method for the Item ArrayList.
	 * 
	 * @param items The items to set.
	 */
	public void setItems(ArrayList<PerspectiveItem> items) {
		
		this.items = items;
	}

	/**
	 * Provides visibility to the Avatar.
	 * 
	 * @return The character (Avatar).
	 */
	public Avatar getCharacter() {
		
		return character;
	}

	/**
	 * Setter method for the Avatar
	 * 
	 * @param character The Avatar to be the new character.
	 */
	public void setCharacter(Avatar character) {
		
		this.character = character;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
