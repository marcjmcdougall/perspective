package com.cap4053.perspective.backends;

import java.util.Stack;

import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.models2D.Tile;

public class Validator {

	private boolean[][] visited;
	private Tile[][] tiles;
	
	public Validator(){
		
		// Currently, do nothing
	}
	
	public boolean validateAvatarMove(int targetRow, int targetColumn, Avatar a, Tile[][] tiles){
		
		Stack<Tile> cursor = new Stack<Tile>();
		visited = new boolean[tiles.length][tiles[0].length];
		this.tiles = tiles;
		
		// Push the starting Tile onto the Stack
		cursor.push(tiles[a.getRow()][a.getColumn()]);
		
		int cursorRow = 0;
		int cursorColumn = 0;
		
		while(!cursor.empty()){
			
			cursorRow = cursor.peek().getRow();
			cursorColumn = cursor.peek().getColumn();
			
			// Test the value to the right
			if(testRight(cursorRow, cursorColumn)){
				
				// If this location equals the target row column pair
				if(cursorColumn + 1 == targetColumn && cursorRow == targetRow){
					
					return true;
				}
				
				//If we haven't returned by now, then push the next value onto the Stack
				cursor.push(tiles[cursorRow][cursorColumn + 1]);
			}
			// Test the value to the bottom
			else if(testBottom(cursorRow, cursorColumn)){
				
				// If this location equals the target row column pair
				if(cursorColumn == targetColumn && cursorRow - 1 == targetRow){
					
					return true;
				}
				
				//If we haven't returned by now, then push the next value onto the Stack
				cursor.push(tiles[cursorRow - 1][cursorColumn]);				
			}
			// Test the value to the left
			else if(testLeft(cursorRow, cursorColumn)){
				
				// If this location equals the target row column pair
				if(cursorColumn - 1 == targetColumn && cursorRow == targetRow){
					
					return true;
				}
				
				//If we haven't returned by now, then push the next value onto the Stack
				cursor.push(tiles[cursorRow][cursorColumn - 1]);				
			}
			// Test the value to the top
			else if(testTop(cursorRow, cursorColumn)){
				
				// If this location equals the target row column pair
				if(cursorColumn == targetColumn && cursorRow + 1 == targetRow){
					
					return true;
				}
				
				//If we haven't returned by now, then push the next value onto the Stack
				cursor.push(tiles[cursorRow + 1][cursorColumn]);				
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
	
	private boolean testRight(int currentRow, int currentColumn){
		
		if(currentColumn + 1 < tiles[0].length){	
		
			if(!visited[currentRow][currentColumn + 1]){
				
				visited[currentRow][currentColumn + 1] = true;
			
				if(tiles[currentRow][currentColumn + 1].canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testRight()");
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean testBottom(int currentRow, int currentColumn){
		
		if(currentRow - 1 >= 0){	
		
			if(!visited[currentRow - 1][currentColumn]){
				
				visited[currentRow - 1][currentColumn] = true;
				
				if(tiles[currentRow - 1][currentColumn].canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testBottom()");
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean testLeft(int currentRow, int currentColumn){
		
		if(currentColumn - 1 >= 0){
		
			if(!visited[currentRow][currentColumn - 1]){
				
				visited[currentRow][currentColumn - 1] = true;
				
				if(tiles[currentRow][currentColumn - 1].canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testLeft()");
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean testTop(int currentRow, int currentColumn){
		
		if(currentRow + 1 < tiles.length){
			
			if(!visited[currentRow + 1][currentColumn]){
			
				visited[currentRow + 1][currentColumn] = true;
		
				if(tiles[currentRow + 1][currentColumn].canMoveTo()){
					
//					DEBUG
//					Gdx.app.log(Perspective.TAG, "**Returning true from testTop()");
					
					return true;
				}
			}
		}
		
		return false;
	}
}
