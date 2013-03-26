package com.cap4053.perspective.backends;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.models2D.BlockTile;
import com.cap4053.perspective.models2D.FloorTile;
import com.cap4053.perspective.models2D.PerspectiveItem;
import com.cap4053.perspective.models2D.PerspectiveObject;
import com.cap4053.perspective.models2D.Tile;

public class Plane {

	public static final int DIMENSION = 7;
	
	private Tile[][] tiles;
	private PerspectiveItem[][] items;
	private Avatar character;
	
	public Plane(){
		
		this.tiles = new Tile[DIMENSION][DIMENSION];
		this.items = new PerspectiveItem[DIMENSION][DIMENSION];
	}
	
	public void initialize(int characterStartingRow, int characterStartingColumn, String tileDescription, String itemDescription, Stage stage){
		
		parseTilesAsString(tileDescription);
		parseItemsAsString(itemDescription);
		
//		if(tiles[characterStartingRow][characterStartingColumn].canMoveTo()){
			
			this.character = Avatar.create(characterStartingRow, characterStartingColumn);
//		}
		
		for(int i = 0; i < tiles.length; i++){
			
			for(int j = 0; j < tiles[i].length; j++){
				
				// Add the actors to the screen
				stage.addActor(tiles[i][j]);
			}
		}
		
		stage.addActor(character);
	}

	public void onTouch(float x, float y, Stage stage){
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "Entered onTouch()!  X: " + x + " Y: " + y);
		
		PerspectiveObject actor = (PerspectiveObject) stage.hit(x, y, false);
		
		int row = actor.getRow();
		int column = actor.getColumn();
		
		moveCharacter(row, column);
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "Tile Touch Detected!  Actor: " + actor.getSimpleName() + " Row: " + actor.getRow() + " Column: " + actor.getColumn());
	}
	
	public void moveCharacter(int newRow, int newColumn){
		
		Validator v = new Validator();
		
		if(v.validateAvatarMove(newRow, newColumn, character, tiles)){
			
//			DEBUG
//			Gdx.app.log(Perspective.TAG, "**Attempting to move character now**");
			
			character.moveTo(newRow, newColumn);
		}
	}
	
	private void parseTilesAsString(String input){
		
		int row = 6;
		int column = 0;
		
		Scanner scan = new Scanner(input);
		Scanner lineScanner = null;
		
		while(scan.hasNextLine()){
			
			String nextLine = scan.nextLine();
			
			lineScanner = new Scanner(nextLine);
			
//			DEBUG
//			Gdx.app.log(Perspective.TAG, "Scanning Line: " + nextLine);
			
			while(lineScanner.hasNext()){
				
				String cursor = lineScanner.next();
				
//				DEBUG
//				Gdx.app.log(Perspective.TAG, "Scanning Character: " + cursor);
				
				if(cursor.equals("B")){
					
					tiles[row][column] = BlockTile.create(row, column);
				}
				else if(cursor.equals("F")){
					
					tiles[row][column] = FloorTile.create(row, column);
				}
				
				column ++;
			}
			
			row--;
			column = 0;
		}
		
		scan.close();
		lineScanner.close();
	}
	
	private void parseItemsAsString(String input){
		
		//TODO: Implementation
	}
	
	/**
	 * @return the tiles
	 */
	public Tile[][] getTiles() {
		
		return tiles;
	}

	/**
	 * @param tiles the tiles to set
	 */
	public void setTiles(Tile[][] tiles) {
		
		this.tiles = tiles;
	}

	/**
	 * @return the items
	 */
	public PerspectiveItem[][] getItems() {
		
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(PerspectiveItem[][] items) {
		
		this.items = items;
	}

	/**
	 * @return the character
	 */
	public Avatar getCharacter() {
		
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(Avatar character) {
		
		this.character = character;
	}
}
