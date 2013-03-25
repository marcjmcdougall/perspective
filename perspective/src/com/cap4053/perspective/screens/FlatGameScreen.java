package com.cap4053.perspective.screens;

import java.util.ArrayList;
import java.util.Iterator;

import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.models2D.Character;
import com.cap4053.perspective.models2D.Block;
import com.cap4053.perspective.models2D.PerspectiveImage;

public class FlatGameScreen extends PerspectiveScreen{

	public static final float HORIZONTAL_MARGIN = 100.0f;
	public static final float VERTICAL_MARGIN = 20.0f;
	
	private ArrayList<PerspectiveImage> images;
	
	public FlatGameScreen(Perspective game) {
		
		super(game);
		
		images = new ArrayList<PerspectiveImage>();
		
		initialize();
	}
	
	@Override
	public void show() {
		
		super.show();
		
		Iterator<PerspectiveImage> iter = images.iterator();
		
		while(iter.hasNext()){
			
			stage.addActor(iter.next());
		}
	}
	
	public void resize(int width, int height){
		
		super.resize(width, height);
	}
	
	private void initialize(){
		
		// Adds a single block to the ArrayList
		for(int i = 0; i < 7; i++){
			
			for(int j = 0; j < 7; j++){
				
				images.add(Block.create(i, j));
			}
		}
		
		images.add(Character.create(4, 5));
	}
}
