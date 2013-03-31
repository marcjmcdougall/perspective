package com.cap4053.perspective.backends;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	private ArrayList<Tile> tiles;
	private ArrayList<PerspectiveItem> items;
	private Avatar character;
	private Stage stage;
	
	private Parser p;
	
	private boolean characterState;
	
	public Plane(Stage stage, boolean characterState){
		
		this.tiles = new ArrayList<Tile>();
		this.items = new ArrayList<PerspectiveItem>();
		this.stage = stage;
		
		this.p = new Parser();
		
		this.characterState = characterState;
	}
	
	public void setCharacterState(boolean newState){
		
		this.characterState = newState;
		onCharacterStateChange();
	}
	
	private void onCharacterStateChange(){
		
		if(characterState){
		
			stage.addActor(character);
		}
		else{
			
			// TODO: Might not be able to use Actor#remove()?
			character.remove();
		}
	}
	
	public void initialize(int characterStartingRow, int characterStartingColumn, String tileMap, String itemMap){
		
		parseTilesAsString(tileMap);
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "Tilemap: " + tileMap);
		
		
		Tile characterTile = p.findTileAt(characterStartingRow, characterStartingColumn, this);
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "Evaluating tile: " + characterTile);		
		
		if(characterTile != null){
			
			if(characterTile.canMoveTo()){
				
//				DEBUG
//				Gdx.app.log(Perspective.TAG, "Creating character now");
				
				this.character = Avatar.create(characterStartingRow, characterStartingColumn, this);
			}
		}
		
		parseItemsAsString(itemMap);
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "About to add tiles and items");
		
		Iterator<Tile> tileIter = tiles.iterator();
		
		while(tileIter.hasNext()){
			
			stage.addActor(tileIter.next());
		}
		
		Iterator<PerspectiveItem> itemIter = items.iterator();
		
		while(itemIter.hasNext()){
			
//					DEBUG
//			Gdx.app.log(Perspective.TAG, "Adding " + items[i][j].getClass().getSimpleName());
			
			stage.addActor(itemIter.next());
			
//					DEBUG
//			Gdx.app.log(Perspective.TAG, "Added " + items[i][j].getClass().getSimpleName());
		}
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "Added tiles and items");
		
		if(characterState){
			
			stage.addActor(character);
		}
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
	
	public void abruptlyMoveCharacter(int newRow, int newColumn){
		
		character.setRow(newRow);
		character.setColumn(newColumn);
	}
	
	public void moveCharacter(int newRow, int newColumn){
		
		Validator v = new Validator();
		ArrayList<SimpleCoordinate> path;
		
		if(v.validateAvatarMove(newRow, newColumn, character, this)){
			
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
		
//		DEBUG
//		System.out.println(input);
		
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
					
					tiles.add(BlockTile.create(row, column, this));
				}
				else if(cursor.equals("F")){
					
					tiles.add(FloorTile.create(row, column, this));
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
		
//		DEBUG
//		System.out.println(input);
		
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
	
	/**
	 * @return the tiles
	 */
	public ArrayList<Tile> getTiles() {
		
		return tiles;
	}

	/**
	 * @param tiles the tiles to set
	 */
	public void setTiles(ArrayList<Tile> tiles) {
		
		this.tiles = tiles;
	}

	/**
	 * @return the items
	 */
	public ArrayList<PerspectiveItem> getItems() {
		
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<PerspectiveItem> items) {
		
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
