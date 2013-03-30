package com.cap4053.perspective.backends;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import com.cap4053.perspective.models2D.Avatar;
import com.cap4053.perspective.models2D.tiles.Tile;

public class Validator {

	private ArrayList<SimpleCoordinate> path;
	private Parser p;
	
	private boolean[][] visited;
	
	public Validator(){
		
		p = new Parser();
	}
	
	public boolean validateAvatarMove(int targetRow, int targetColumn, Avatar avatar, Plane plane){
		
		path = new ArrayList<SimpleCoordinate>();
		
		Stack<Tile> cursor = new Stack<Tile>();
		visited = new boolean[Plane.DIMENSION][Plane.DIMENSION];
		
		// Push the starting Tile onto the Stack
		cursor.push(p.findTileAt(avatar.getRow(), avatar.getColumn(), plane));
		visited[avatar.getRow()][avatar.getColumn()] = true;
		
		int cursorRow = 0;
		int cursorColumn = 0;
		
		while(!cursor.empty()){
			
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
					
					storePath(cursor);
					
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
					
					storePath(cursor);
					
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
					
					storePath(cursor);
					
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
					
					storePath(cursor);
					
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

	private void storePath(Stack<Tile> cursor) {
		
		Iterator<Tile> i = cursor.iterator();
		Tile tileCursor = null;
		
		while(i.hasNext()){
			
			tileCursor = i.next();
			
			SimpleCoordinate coord = new SimpleCoordinate(tileCursor.getRow(), tileCursor.getColumn());
			path.add(coord);
		}
	}
	
	public ArrayList<SimpleCoordinate> getPath(){
		
		return path;
	}
	
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
