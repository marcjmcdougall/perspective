package com.cap4053.perspective.screens;

import java.util.ArrayList;
import java.util.Iterator;

import com.cap4053.perspective.Perspective;
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
	
	private void initialize(){
		
		// Adds a single block to the ArrayList
		images.add(Block.create(0, 0));
		images.add(Block.create(0, 1));
		images.add(Block.create(0, 2));
		images.add(Block.create(0, 3));
		images.add(Block.create(0, 4));
		images.add(Block.create(0, 5));
		images.add(Block.create(0, 6));
		images.add(Block.create(1, 0));
		images.add(Block.create(2, 1));
		images.add(Block.create(3, 2));
		images.add(Block.create(4, 3));
		images.add(Block.create(5, 4));
	}
}
