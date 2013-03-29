package com.cap4053.perspective.backends;

import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.models2D.PerspectiveItem;
import com.cap4053.perspective.models2D.PerspectiveObject;
import com.cap4053.perspective.models2D.items.Diamond;
import com.cap4053.perspective.models2D.items.Heart;
import com.cap4053.perspective.models2D.items.Star;
import com.cap4053.perspective.models2D.tiles.BlockTile;
import com.cap4053.perspective.models2D.tiles.FloorTile;
import com.cap4053.perspective.models2D.tiles.Tile;

public class Plane {

	public static final int DIMENSION = 7;
	
	private Tile[][] tiles;
	private PerspectiveItem[][] items;
	private Avatar character;
	
	public Plane(){
		
		this.tiles = new Tile[DIMENSION][DIMENSION];
		this.items = new PerspectiveItem[DIMENSION][DIMENSION];
	}
	
	public void initialize(int characterStartingRow, int characterStartingColumn, String tileMap, String itemMap, Stage stage){
		
		parseTilesAsString(tileMap);
		
		if(tiles[characterStartingRow][characterStartingColumn].canMoveTo()){
			
			this.character = Avatar.create(characterStartingRow, characterStartingColumn, this);
		}
		
		parseItemsAsString(itemMap);
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "About to add tiles and items");
		
		for(int i = 0; i < tiles.length; i++){
			
			for(int j = 0; j < tiles[i].length; j++){
				
				if(tiles[i][j] != null){
					
					stage.addActor(tiles[i][j]);
				}
				
				if(items[i][j] != null){
					
//					DEBUG
					Gdx.app.log(Perspective.TAG, "Adding " + items[i][j].getClass().getSimpleName());
					
					//TODO: REMOVED IN HERE!
					stage.addActor(items[i][j]);
					
//					DEBUG
					Gdx.app.log(Perspective.TAG, "Added " + items[i][j].getClass().getSimpleName());
				}
			}
		}
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "Added tiles and items");
		
		stage.addActor(character);
		

	}

	public void onTouch(float x, float y, Stage stage){
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "Entered onTouch()!  X: " + x + " Y: " + y);
		
		PerspectiveObject actor = (PerspectiveObject) stage.hit(x, y, false);
		
		if(actor != null){
		
			int row = actor.getRow();
			int column = actor.getColumn();
		
			moveCharacter(row, column);
		
//			DEBUG
//			Gdx.app.log(Perspective.TAG, "Tile Touch Detected!  Actor: " + actor.getSimpleName() + " Row: " + actor.getRow() + " Column: " + actor.getColumn());
		}
	}
	
	public void moveCharacter(int newRow, int newColumn){
		
		Validator v = new Validator();
		ArrayList<SimpleCoordinate> path;
		
		if(v.validateAvatarMove(newRow, newColumn, character, tiles)){
			
//			DEBUG
//			Gdx.app.log(Perspective.TAG, "**Attempting to move character now**");
			
			path = v.getPath();
			
			character.moveTo(newRow, newColumn, path, items);
		}
		
//		character.moveTo(newRow, newColumn);
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
					
					tiles[row][column] = BlockTile.create(row, column, this);
				}
				else if(cursor.equals("F")){
					
					tiles[row][column] = FloorTile.create(row, column, this);
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
				
				if(cursor.equals("H")){
					
					items[row][column] = Heart.create(row, column, this);
				}
				else if(cursor.equals("S")){
					
					items[row][column] = Star.create(row, column, this);
				}
				else if(cursor.equals("D")){
					
					items[row][column] = Diamond.create(row, column, this);
				}
				else{
					
					items[row][column] = null;
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
