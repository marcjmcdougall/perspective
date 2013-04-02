package com.cap4053.perspective.backends;

import java.util.Iterator;

import com.cap4053.perspective.models2D.PerspectiveItem;
import com.cap4053.perspective.models2D.tiles.Tile;

/**
 * Parser class that aides the parsing and removing of the Tiles and Items on each 2D Level.
 * 
 * @author Marc J McDougall
 */
public class Parser {

	/**
	 * Default constructor - does nothing currently.
	 */
	public Parser(){
		
		// Do Nothing
	}
	
	/**
	 * Helper method that returns the specific Tile at the given row, column, and Plane.
	 * 
	 * @param row The row to search for
	 * @param column The column to search for
	 * @param plane The Plane to search on
	 * @return The Tile at the location of the row, column on the provided Plane.
	 */
	public Tile findTileAt(int row, int column, Plane plane){
		
		// Get an iterator over the tile ArrayList
		Iterator<Tile> tileIter = plane.getTiles().iterator();
		
		// Cursor object
		Tile output = null;
		
		// While there are values left to parse
		while(tileIter.hasNext()){
			
			// Associate the cursor object with the next Tile in the ArrayList
			output = tileIter.next();
			
			// If the row and column of the Tile are equal to the ones that were passed in
			if(output.getRow() == row && output.getColumn() == column){
				
				// Return that Tile
				return output;
			}
		}
		
		// If we never reach a Tile with the given coordinates, return null
		return null;
	}

	/**
	 * Helper method that returns the specific Item at the given row, column, and Plane.
	 * 
	 * @param row The row to search for
	 * @param column The column to search for
	 * @param plane The Plane to search on
	 * @return The Item at the location of the row, column on the provided Plane.
	 */
	public PerspectiveItem findItemAt(int row, int column, Plane plane){
		
		// Get an iterator over the Item ArrayList		
		Iterator<PerspectiveItem> itemIter = plane.getItems().iterator();
		
		// Cursor object
		PerspectiveItem output = null;
		
		// While there are values left to parse
		while(itemIter.hasNext()){
			
			// Associate the cursor object with the next Item in the ArrayList		
			output = itemIter.next();
			
			// If the row and column of the Item are equal to the ones that were passed in
			if(output.getRow() == row && output.getColumn() == column){
				
				// Return that Item
				return output;
			}
		}
		
		// If we never reach a Item with the given coordinates, return null
		return null;
	}
	
	/**
	 * Helper method that removes the specific Item at the given row, column, and Plane.
	 * 
	 * @param row The row to search for
	 * @param column The column to search for
	 * @param plane The Plane to search on
	 */
	public void removeItemAt(int row, int column, Plane plane){
		
		// Get an iterator over the Item ArrayList	
		Iterator<PerspectiveItem> itemIter = plane.getItems().iterator();
		
		// Cursor object
		PerspectiveItem output = null;
		
		// While there are values left to parse
		while(itemIter.hasNext()){
			
			// Associate the cursor object with the next Item in the ArrayList
			output = itemIter.next();
			
			// If the row and column of the Item are equal to the ones that were passed in
			if(output.getRow() == row && output.getColumn() == column){
				
				// Remove that Item from the Arraylist
				plane.getItems().remove(output);
				
				// Return to prevent wasted execution time
				return;
			}
		}
	}
}
