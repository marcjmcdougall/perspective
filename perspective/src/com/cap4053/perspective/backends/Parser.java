package com.cap4053.perspective.backends;

import java.util.Iterator;

import com.cap4053.perspective.models2D.PerspectiveItem;
import com.cap4053.perspective.models2D.tiles.Tile;

public class Parser {

	public Parser(){
		
		// Do Nothing
	}
	
	public Tile findTileAt(int row, int column, Plane plane){
		
		Iterator<Tile> tileIter = plane.getTiles().iterator();
		
		Tile output = null;
		
		while(tileIter.hasNext()){
			
			output = tileIter.next();
			
			if(output.getRow() == row && output.getColumn() == column){
				
				return output;
			}
		}
		
		return null;
	}
	
	public PerspectiveItem findItemAt(int row, int column, Plane plane){
		
		Iterator<PerspectiveItem> itemIter = plane.getItems().iterator();
		
		PerspectiveItem output = null;
		
		while(itemIter.hasNext()){
			
			output = itemIter.next();
			
			if(output.getRow() == row && output.getColumn() == column){
				
				return output;
			}
		}
		
		return null;
	}
	
	public void removeItemAt(int row, int column, Plane plane){
		
		Iterator<PerspectiveItem> itemIter = plane.getItems().iterator();
		
		PerspectiveItem output = null;
		
		while(itemIter.hasNext()){
			
			output = itemIter.next();
			
			if(output.getRow() == row && output.getColumn() == column){
				
				plane.getItems().remove(output);
				
				return;
			}
		}
	}
}
