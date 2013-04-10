package com.cap4053.perspective.models2D;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.cap4053.perspective.backends.Plane;

public class PerspectiveCollection{
	
	protected Plane plane;
	
	private ArrayList<PerspectiveCollectionItem> items;
	
	private String type;
	private int max;
	private int startX;
	private int startY;
	private boolean centered;
	private int objectSize;
	private int objectSpacing;
	
	private PerspectiveCollection(Plane level2D, String type, int enabled, int max, int startX, int startY, boolean centered, int objectSize, int objectSpacing) {
		
		this.plane = level2D;
		this.items = new ArrayList<PerspectiveCollectionItem>();
		this.max = max;
		this.type = type;
		this.startX = startX;
		this.startY = startY;
		this.centered = centered;
		this.objectSize = objectSize;
		this.objectSpacing = objectSpacing;
		
		displayItems(enabled);
		
	}
	
	public static PerspectiveCollection create(Plane level2D, String type, int enabled, int max, int startX, int startY, boolean centered, int objectSize, int objectSpacing){

		return new PerspectiveCollection(level2D, type, enabled, max, startX, startY, centered, objectSize, objectSpacing);
	}
	
	public void update(int enabled){
		System.out.println("Updating collections...");
		
		// Remove old items
		for(int i = 0; i < items.size(); i++){
			
			PerspectiveCollectionItem item = items.get(i);
			item.remove();
		}
		
		items.clear();
		
		displayItems(enabled);
	}
	
	public void displayItems(int enabled){
		
		int startX = this.startX;
		int startY = this.startY;
		
		if(this.centered){
			
			startX = (Gdx.graphics.getWidth() - (this.objectSize * this.max) - (this.objectSpacing * this.max-1))/2;
			startY = Gdx.graphics.getHeight() - 75;
		}
			
		int obtainedCounter = 0;
		
		for(int i = 0; i < max; i++){
			
			PerspectiveCollectionItem item;
			
			if(obtainedCounter != enabled){
				
				item = PerspectiveCollectionItem.create(this.type, true);
				obtainedCounter++;
			}
			else{
				item = PerspectiveCollectionItem.create(this.type, false);
			}
			
			item.setX(startX);
			item.setY(startY);
			startX += this.objectSize + this.objectSpacing;
			
			this.plane.getStage().addActor(item);
			items.add(item);
		}
	}
	
}
