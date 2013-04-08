package com.cap4053.perspective.backends;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.models2D.tiles.Tile;

/**
 * Class used to validate certain moves on the game board.
 * 
 * @author Marc J. McDougall
 */
public class Validator {

	// ArrayList storing the path that is determined by the algorithm
	private ArrayList<SimpleCoordinate> path;
	
	// Reference to the parser that assists in moving through the board
	private Parser p;
	
	// Local variable that details if a slot has been visited on the board
	private boolean[][] visited;
	
	/**
	 * Simple default constructor
	 */
	public Validator(){
		
		// Create a new Parser
		p = new Parser();
	}
	
	/**
	 * Main method of the class.  This validates a move on the board and ensures that it is
	 * appropriate.  It also populates the local ArrayList with the path to that slot.
	 * 
	 * @param targetRow The intended row.
	 * @param targetColumn The intended column.
	 * @param avatar The character.
	 * @param plane The current Plane.
	 * @return Boolean detailing the validity of the move.
	 */
	public boolean validateAvatarMove(int targetRow, int targetColumn, Avatar avatar, Plane plane){
		
		// Reset the local path variable
		path = new ArrayList<SimpleCoordinate>();
		
		// Initialize a stack to facilitate parsing the path
		Stack<Tile> cursor = new Stack<Tile>();
		
		// Reset the visited variable
		visited = new boolean[Plane.DIMENSION][Plane.DIMENSION];
		
		// Push the starting Tile onto the Stack
		cursor.push(p.findTileAt(avatar.getRow(), avatar.getColumn(), plane));
		
		// Specify that the starting position has been visisted
		visited[avatar.getRow()][avatar.getColumn()] = true;
		
		// Initialize the local temporary variables to 0
		int cursorRow = 0;
		int cursorColumn = 0;
		
		// While the stack is not empty...
		while(!cursor.empty()){
			
			// Update the local placeholder variables to the row and column of the Tile at the head of the Stack
			cursorRow = cursor.peek().getRow();
			cursorColumn = cursor.peek().getColumn();
			
			// Test the value to the right
			if(testRight(cursorRow, cursorColumn, plane)){
				
//				DEBUG
//				Gdx.app.log(Perspective.TAG, "Adding (" + (cursorRow) + ", " + (cursorColumn + 1) + ")");
				
				// If we haven't returned by now, then push the next value onto the Stack
				cursor.push(p.findTileAt(cursorRow, cursorColumn + 1, plane));
				
				// If this location equals the target row column pair
				if(cursorColumn + 1 == targetColumn && cursorRow == targetRow){
					
					// Store the path before returning (for use later)
					storePath(cursor);
					
					// Return true to specify that the move is indeed valid
					return true;
				}
			}
			// Test the value to the bottom
			else if(testBottom(cursorRow, cursorColumn, plane)){
				
//				DEBUG
//				Gdx.app.log(Perspective.TAG, "Adding (" + (cursorRow - 1) + ", " + (cursorColumn) + ")");
				
				//If we haven't returned by now, then push the next value onto the Stack
				cursor.push(p.findTileAt(cursorRow - 1, cursorColumn, plane));
				
				// If this location equals the target row column pair
				if(cursorColumn == targetColumn && cursorRow - 1 == targetRow){
					
					// Store the path before returning (for use later)
					storePath(cursor);
					
					// Return true to specify that the move is indeed valid
					return true;
				}
			}
			// Test the value to the left
			else if(testLeft(cursorRow, cursorColumn, plane)){
				
//				DEBUG
//				Gdx.app.log(Perspective.TAG, "Adding (" + (cursorRow) + ", " + (cursorColumn - 1) + ")");
				
				//If we haven't returned by now, then push the next value onto the Stack
				cursor.push(p.findTileAt(cursorRow, cursorColumn - 1, plane));
				
				// If this location equals the target row column pair
				if(cursorColumn - 1 == targetColumn && cursorRow == targetRow){
					
					// Store the path before returning (for use later)
					storePath(cursor);
					
					// Return true to specify that the move is indeed valid
					return true;
				}
			}
			// Test the value to the top
			else if(testTop(cursorRow, cursorColumn, plane)){
				
//				DEBUG
//				Gdx.app.log(Perspective.TAG, "Adding (" + (cursorRow + 1) + ", " + (cursorColumn) + ")");
				
				//If we haven't returned by now, then push the next value onto the Stack
				cursor.push(p.findTileAt(cursorRow + 1, cursorColumn, plane));
				
				// If this location equals the target row column pair
				if(cursorColumn == targetColumn && cursorRow + 1 == targetRow){
					
					// Store the path before returning (for use later)
					storePath(cursor);
					
					// Return true to specify that the move is indeed valid
					return true;
				}
			}
			// Otherwise...
			else{
				
				// Pop the stack
				cursor.pop();
			}
		}
		
		// If a solution has not been found by now, then return false
		return false;
	}

	/**
	 * Helper method used to update the most recent path to the given row and column.
	 * 
	 * @param cursor The Stack that contains all the path information.
	 */
	private void storePath(Stack<Tile> cursor) {
		
		// Initialize an iterator to iterate over the elements in the Stack
		Iterator<Tile> i = cursor.iterator();
		
		// A local cursor object, and initialize it to null
		Tile tileCursor = null;
		
		// While there are elements in the iterator...
		while(i.hasNext()){
			
			// Advance the cursor
			tileCursor = i.next();
			
			// Create a new coordinate, and make it's row and column equal to the 
			// row and column of the cursor
			SimpleCoordinate coord = new SimpleCoordinate(tileCursor.getRow(), tileCursor.getColumn());
			
			// Add that coordinate to the path.
			path.add(coord);
		}
	}
	
	/**
	 * Provides visibility to the more recently-derived path ArrayList.
	 * 
	 * @return The path to the most recent valid location.
	 */
	public ArrayList<SimpleCoordinate> getPath(){
		
		return path;
	}
	
	/**
	 * Helper method that tests the right value and returns true if it is "valid".
	 * 
	 * @param currentRow The current row of the Stack
	 * @param currentColumn The current column of the Stack
	 * @param plane The current Plane
	 * @return Boolean representing the state of the right value
	 */
	private boolean testRight(int currentRow, int currentColumn, Plane plane){
		
		if(currentColumn + 1 < Plane.DIMENSION){	
		
			if(!visited[currentRow][currentColumn + 1]){
				
				visited[currentRow][currentColumn + 1] = true;
			
				if(p.findTileAt(currentRow, currentColumn + 1, plane).canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testRight()");
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Helper method that tests the bottom value and returns true if it is "valid".
	 * 
	 * @param currentRow The current row of the Stack
	 * @param currentColumn The current column of the Stack
	 * @param plane The current Plane
	 * @return Boolean representing the state of the bottom value
	 */
	private boolean testBottom(int currentRow, int currentColumn, Plane plane){
		
		if(currentRow - 1 >= 0){	
		
			if(!visited[currentRow - 1][currentColumn]){
				
				visited[currentRow - 1][currentColumn] = true;
				
				if(p.findTileAt(currentRow - 1, currentColumn, plane).canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testBottom()");
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Helper method that tests the left value and returns true if it is "valid".
	 * 
	 * @param currentRow The current row of the Stack
	 * @param currentColumn The current column of the Stack
	 * @param plane The current Plane
	 * @return Boolean representing the state of the left value
	 */
	private boolean testLeft(int currentRow, int currentColumn, Plane plane){
		
		if(currentColumn - 1 >= 0){
		
			if(!visited[currentRow][currentColumn - 1]){
				
				visited[currentRow][currentColumn - 1] = true;
				
				if(p.findTileAt(currentRow, currentColumn - 1, plane).canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testLeft()");
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Helper method that tests the right value and returns true if it is "valid".
	 * 
	 * @param currentRow The current row of the Stack
	 * @param currentColumn The current column of the Stack
	 * @param plane The current Plane
	 * @return Boolean representing the state of the right value
	 */
	private boolean testTop(int currentRow, int currentColumn, Plane plane){
		
		if(currentRow + 1 < Plane.DIMENSION){
			
			if(!visited[currentRow + 1][currentColumn]){
			
				visited[currentRow + 1][currentColumn] = true;
		
				if(p.findTileAt(currentRow + 1, currentColumn, plane).canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testTop()");
					
					return true;
				}
			}
		}
		
		return false;
	}
}
