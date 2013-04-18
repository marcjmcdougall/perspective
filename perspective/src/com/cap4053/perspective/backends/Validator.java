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
	
	// Local variable that details the distance left to reach the goal point
	private int[][] distLeft;
	
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
		
		boolean done = false;
		boolean emptyPass = true;
		int currVal = 0;
		int avatarVal;
		int currRow, currCol;
		
		// Reset distance left with -1(infinity)
		distLeft = new int[Plane.DIMENSION][Plane.DIMENSION];
		for(int i=Plane.DIMENSION-1; i>=0; i--){
			for(int j=0; j<Plane.DIMENSION; j++){
				distLeft[i][j] = -1;
			}
		}
		if(p.findTileAt(targetRow, targetColumn, plane).canMoveTo()){
			distLeft[targetRow][targetColumn] = 0;
		}
		// Build distLeft array
		while(!done){
			for(int i=Plane.DIMENSION-1; i>=0; i--){
				for(int j=0; j<Plane.DIMENSION; j++){
					if(distLeft[i][j] == currVal){
						if(testLeft(i, j, plane) && distLeft[i][j-1] == -1){
							distLeft[i][j-1] = currVal + 1;
							emptyPass = false;
						}
						if(testRight(i, j, plane) && distLeft[i][j+1] == -1){
							distLeft[i][j+1] = currVal + 1;
							emptyPass = false;
						}
						if(testBottom(i, j, plane) && distLeft[i-1][j] == -1){
							distLeft[i-1][j] = currVal + 1;
							emptyPass = false;
						}
						if(testTop(i, j, plane) && distLeft[i+1][j] == -1){
							distLeft[i+1][j] = currVal + 1;
							emptyPass = false;
						}
					}
				}
			}
			if(emptyPass){
				done = true;
			}
			emptyPass = true;
			currVal = currVal + 1;
		}
		// Code for testing distLeft calculation.
		/*
		for(int i=Plane.DIMENSION-1; i>=0; i--){
			for(int j=0; j<Plane.DIMENSION; j++){
				System.out.print(distLeft[i][j]);
			}
			System.out.println();
		}
		*/
		// The avatar's current distance to goal square.
		avatarVal = distLeft[avatar.getRow()][avatar.getColumn()];
		currRow = avatar.getRow();
		currCol = avatar.getColumn();
		
		// Path is impossible, avatar cannot reach that square.
		if(avatarVal == -1){
			path = null;
			return false;
		}
		else{
			while(avatarVal>=0){
				// Create a new coordinate, and make it's row and column equal to the 
				// row and column of the cursor
				SimpleCoordinate coord = new SimpleCoordinate(currRow, currCol);
				
				// Add that coordinate to the path.
				path.add(coord);
				
				if(testLeft(currRow, currCol, plane) && distLeft[currRow][currCol-1] == avatarVal - 1){
					currCol = currCol - 1;
					emptyPass = false;
				}
				if(testRight(currRow, currCol, plane) && distLeft[currRow][currCol+1] == avatarVal - 1){
					currCol = currCol + 1;
					emptyPass = false;
				}
				if(testBottom(currRow, currCol, plane) && distLeft[currRow-1][currCol] == avatarVal - 1){
					currRow = currRow - 1;
					emptyPass = false;
				}
				if(testTop(currRow, currCol, plane) && distLeft[currRow+1][currCol] == avatarVal - 1){
					currRow = currRow + 1;
					emptyPass = false;
				}
				
				// Decrement current avatar distance value.
				avatarVal = avatarVal - 1;
			}
			return true;
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
		
		//	if(!visited[currentRow][currentColumn + 1]){
				
		//		visited[currentRow][currentColumn + 1] = true;
			
				if(p.findTileAt(currentRow, currentColumn + 1, plane).canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testRight()");
					
					return true;
				}
		//	}
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
		
			//if(!visited[currentRow - 1][currentColumn]){
				
			//	visited[currentRow - 1][currentColumn] = true;
				
				if(p.findTileAt(currentRow - 1, currentColumn, plane).canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testBottom()");
					
					return true;
				}
			//}
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
		
			//if(!visited[currentRow][currentColumn - 1]){
				
			//	visited[currentRow][currentColumn - 1] = true;
				
				if(p.findTileAt(currentRow, currentColumn - 1, plane).canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testLeft()");
					
					return true;
				}
			//}
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
			
			//if(!visited[currentRow + 1][currentColumn]){
			
		//		visited[currentRow + 1][currentColumn] = true;
		
				if(p.findTileAt(currentRow + 1, currentColumn, plane).canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testTop()");
					
					return true;
				}
			//}
		}
		
		return false;
	}
}
