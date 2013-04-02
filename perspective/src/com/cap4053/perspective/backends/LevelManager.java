package com.cap4053.perspective.backends;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.screens.GameScreen2D;
import com.cap4053.perspective.screens.GameScreen3D;

/**
 * Class that manages each Level in the game.  It associates the 6 planes with each level, and 
 * computes the 3D texture for all the faces which are then displayed onto a 3D Cube.
 * 
 * @author Marc J McDougall
 */
public class LevelManager {

	// Class variables that are used to define the indices of each face
	private static final int FACE_FRONT = 0;
	private static final int FACE_BACK = 1;
	private static final int FACE_LEFT = 2;
	private static final int FACE_RIGHT = 3;
	private static final int FACE_TOP = 4;
	private static final int FACE_BOTTOM = 5;
	
	// Maintains a reference to the Game object, in order to switch screens
	private Perspective game;
	
	// Simple array data structure that contains the 6 2D faces that will be displayed to the user
	private GameScreen2D[] faces;
	
	// Holds a reference to the 3D cube so that it's vertices may be reused
	private GameScreen3D view3D;
	
	// Reference to the Parser class that aides in parsing the Tiles on the Screen
	private Parser p;
	
	// The context menu for the game
	private Stage menu;
	
	// Integer that refers to the current index in the array of the active face
	private int currentFace;
	
	// Variable that determines whether or not to display the 2D version of the game
	private boolean display2D;
	
	// Variable that determines whether or not to display the game menu
	private boolean displayMenu;
	
	/**
	 * Simple constructor that associates the main Game with the local variable.
	 * 
	 * @param game The Game associated with the LevelManager.
	 */
	public LevelManager(Perspective game){
		
		// Basic assignments and instantiations 
		this.game = game;
		this.faces = new GameScreen2D[6]; 
		this.p = new Parser();
		
		// Default the current face to the FRONT face
		this.currentFace = FACE_FRONT;
		
		// Default the starting perspective to the 2D orientation
		this.display2D = true;
		
		// Default the menu state of off
		this.displayMenu = false;
		
		// Sets up the menu Stage
		this.menu = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		
		// Initializes the Menu
		initializeMenu();
		
		// Defines the textures of the 3D cube.  This is temporary and will eventually be replaced with the screen shot function.
		Texture front = new Texture(Gdx.files.internal("data/levels/images/level2_front.png"));
		Texture back = new Texture(Gdx.files.internal("data/levels/images/level2_back.png"));
		Texture left = new Texture(Gdx.files.internal("data/levels/images/level2_left.png"));
		Texture right = new Texture(Gdx.files.internal("data/levels/images/level2_right.png"));
		Texture top = new Texture(Gdx.files.internal("data/levels/images/level2_top.png"));
		Texture bottom = new Texture(Gdx.files.internal("data/levels/images/level2_bottom.png"));
		
		// Creates the new 3D cube
		this.view3D = new GameScreen3D(game, menu, this, front, back, left, right, top, bottom);
	}
	
	/**
	 * Loads a level from the given file name.  The format of the files is detailed in the files themselves.  Note that you MUST
	 * specify any relevant sub-directory information in the name.
	 * 
	 * @param levelFileName The file name of the level.
	 */
	public void loadLevel(String levelFileName){
		
		// Temporary variables holding empty memory space for the Strings that represent each face
		String[] tileMaps = new String[6]; 
		String[] itemMaps = new String[6]; 
		
		// The FileHandle associated with the filename
		FileHandle file = Gdx.files.internal(levelFileName);
		
		// The String contained within the file
		String fileInfo = file.readString();
		
		// A Scanner that will be used to parse the String
		Scanner scan = new Scanner(fileInfo);
		
		// The first line details the initial configurations for the character
		String initialConfigs = scan.nextLine();
		
		// Advance the Scanner to compensate for empty lines
		scan.nextLine();
		
		// Only print output statements if we are in developer mode
		if(Perspective.DEVELOPER_MODE){
			
//			DEBUG
			Gdx.app.log(Perspective.TAG, initialConfigs);
		}
		
		// Another Scanner that will scan over the initial configurations of the character
		Scanner configScan = new Scanner(initialConfigs);
		
		// Variables holding references to the characters' starting row and column.  This currently
		// does nothing, and will be implemented once we have more complicated levels.
		int characterStartingRow = configScan.nextInt();
		int characterStartingColumn = configScan.nextInt();
		
		// Temporary variable used to hold the cascading input from the files.
		String input = "";
		
		// Pull in the tile maps for the 6 faces of the cube
		for(int i = 0; i < 6; i++){
			
			// Reassign the input string to the null string each time we advance through the array
			input = "";
			
			for(int j = 0; j < Plane.DIMENSION; j++){
				
				// Append the information in the file string with a newline to the input String
				input += scan.nextLine() + "\n";
			}
			
			// Add this String to the array
			tileMaps[i] = input;
			
			// If there is a next line (necessary in case we reach the EOF)
			if(scan.hasNextLine()){
				
				// Advance the Scanner to compensate for empty lines 
				scan.nextLine();
			}
		}
		
		// Pull in the item maps for the 6 faces of the cube		
		for(int i = 0; i < 6; i++){
			
			// Reassign the input string to the null string each time we advance through the array
			input = "";
			
			
			for(int j = 0; j < Plane.DIMENSION; j++){
				
				// Append the information in the file string with a newline to the input String
				input += scan.nextLine() + "\n";
			}
			
			// Add this String to the array
			itemMaps[i] = input;
			
			// If there is a next line (necessary in case we reach the EOF)
			if(scan.hasNextLine()){
				
				// Advance the scanner
				scan.nextLine();
			}
			
//			DEBUG
//			Gdx.app.log(Perspective.TAG, itemMaps[i]);
		}
		
		// Assign each of the Strings to actual objects
		for(int i = 0; i < 6; i++){
			
			// If the current face that we are on is equal to that map...
			if(currentFace == i){
				
				// Only print output statements if we are in developer mode
				if(Perspective.DEVELOPER_MODE){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "Passing true now, i=" + i);
				}
				
				// Create the new face, but pass in true to the constructor (used for character identification)
				faces[i] = new GameScreen2D(game, menu, true, tileMaps[i], itemMaps[i], this);
			}
			// Otherwise...
			else{
				
				// Create the new face, but pass in false to the constructor (used for character identification)
				faces[i] = new GameScreen2D(game, menu, false, tileMaps[i], itemMaps[i], this);
			}
		}
		
		// Finally, set the screen to the FRONT face of the cube
		setScreen(FACE_FRONT);
		
		// Show the screen to the user
		showScreen();
	}
	
	/**
	 * Sets the screen to be facing a new Screen head on.
	 * 
	 * @param newScreen The new screen to face.
	 */
	public void setScreen(int newScreen){
		
		// Get a reference to the old Screen
		Plane oldFace = faces[currentFace].getLevel2D();
		
		// Determine the old character row and column (for transposing onto the new face)
		int oldRow = oldFace.getCharacter().getRow();
		int oldColumn = oldFace.getCharacter().getColumn();
		
		// Find the new face that is associated with the newScreen int
		Plane newFace = faces[newScreen].getLevel2D();
		
		// If it is possible to move to that new location...
		if(p.findTileAt(oldRow, oldColumn, newFace).canMoveTo()){
			
			// Turn the character off of the old Screen
			oldFace.setCharacterState(false);
			
			// Update the current faces to the new Screen
			this.currentFace = newScreen;
			
			// Abruptly move the character to the new face
			newFace.abruptlyMoveCharacter(oldRow, oldColumn);
			
			// Turn the character onto the new Screen
			newFace.setCharacterState(true);
		}
	}
	
	/**
	 * Sets the screen according to the state of the perspective.
	 */
	public void showScreen(){
		
		if(display2D){
			
			game.setScreen(faces[currentFace]);
		}
		else{
			
			game.setScreen(view3D);
		}
	}
	
	/**
	 * Provides visibility to the current perspective of the LevelManager (2D or 3D).
	 * 
	 * @return The state of the perspective of the game.
	 */
	public boolean isDisplaying2D(){
		
		return display2D;
	}
	
	/**
	 * Toggles the state of the menu.  It will be shown if it is not currently being shown, or
	 * hidden if it is currently being shown.
	 */
	public void toggleMenu(){
		
		// If we are currently displaying the menu
		if(displayMenu){
			
			// Stop displaying it
			displayMenu = false;
		}
		// Otherwise
		else{
			
			// Display it
			displayMenu = true;
		}
		
		animateMenu();
		
		if(display2D){
			
			faces[currentFace].setDisplayMenu(displayMenu);
		}
		else{
			
			view3D.setDisplayMenu(displayMenu);
		}
	}
	
	/**
	 * Toggles the perspective to the opposite of the current perspective.
	 */
	public void togglePerspective(){
		
		// If the perspective is 2D...
		if(display2D){
			
			// Make it 3D
			display2D = false;
		}
		// Otherwise...
		else{
			
			// Make it 2D
			display2D = true;
		}
		
		// Show the appropriate Screen
		showScreen();
	}
	
	/**
	 * Helper method used to add the Actors to the Stage.
	 */
	private void initializeMenu(){
		
		float backgroundX = Gdx.graphics.getWidth() * 0.125f;
		float backgroundY = Gdx.graphics.getHeight() * 0.125f;
		float backgroundWidth = Gdx.graphics.getWidth() * 0.75f;
		float backgroundHeight = Gdx.graphics.getHeight() * 0.75f;
		
		Image background = new Image(new Texture(Gdx.files.internal("data/perspective_cube_other.png")));
		
		BitmapFont font = new BitmapFont(Gdx.files.internal("data/whitefont.fnt"), false);
		LabelStyle style = new LabelStyle(font, Color.BLACK);
		Label title = new Label("Menu", style);
		
		background.setX(backgroundX);
		background.setY(backgroundY);
		background.setWidth(backgroundWidth);
		background.setHeight(backgroundHeight);
		
		title.setX(backgroundX + (backgroundWidth / 2.0f) - (title.getWidth() / 2.0f));
		title.setY(backgroundY + backgroundHeight - title.getHeight());
		
		menu.addActor(background);
		menu.addActor(title);
	}
	
	private void animateMenu(){
		
		AlphaAction fader = new AlphaAction();
		fader.setDuration(0.5f);
		
		if(displayMenu){
			
			fader.setReverse(true);
		}
		
		menu.addAction(fader);
	}
}
