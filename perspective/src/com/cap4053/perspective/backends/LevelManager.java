package com.cap4053.perspective.backends;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.screens.GameScreen2D;
import com.cap4053.perspective.screens.GameScreen3D;

public class LevelManager {

	private static final int FACE_FRONT = 0;
	private static final int FACE_BACK = 1;
	private static final int FACE_LEFT = 2;
	private static final int FACE_RIGHT = 3;
	private static final int FACE_TOP = 4;
	private static final int FACE_BOTTOM = 5;
	
	private Perspective game;
	private GameScreen2D[] faces;
	private GameScreen3D view3D;
	private Parser p;
	
	private int currentFace;
	private boolean display2D;
	 
	public LevelManager(Perspective game){
		
		this.game = game;
		this.faces = new GameScreen2D[6]; 
		this.p = new Parser();
		
		this.currentFace = FACE_FRONT;
		this.display2D = true;
		
		Texture front = new Texture(Gdx.files.internal("data/levels/images/level2_front.png"));
		Texture back = new Texture(Gdx.files.internal("data/levels/images/level2_back.png"));
		Texture left = new Texture(Gdx.files.internal("data/levels/images/level2_left.png"));
		Texture right = new Texture(Gdx.files.internal("data/levels/images/level2_right.png"));
		Texture top = new Texture(Gdx.files.internal("data/levels/images/level2_top.png"));
		Texture bottom = new Texture(Gdx.files.internal("data/levels/images/level2_bottom.png"));
		
		this.view3D = new GameScreen3D(game, this, front, back, left, right, top, bottom);
	}
	
	public void loadLevel(String levelFileName){
		
		String[] tileMaps = new String[6]; 
		String[] itemMaps = new String[6]; 
		
		FileHandle file = Gdx.files.internal(levelFileName);
		
		String fileInfo = file.readString();
		
		Scanner scan = new Scanner(fileInfo);
		
		String initialConfigs = scan.nextLine();
		
		// Advance the Scanner
		scan.nextLine();
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, initialConfigs);
		
		Scanner configScan = new Scanner(initialConfigs);
		
		int characterStartingRow = configScan.nextInt();
		int characterStartingColumn = configScan.nextInt();
		
		String input = "";
		
		for(int i = 0; i < 6; i++){
			
			input = "";
			
			for(int j = 0; j < Plane.DIMENSION; j++){
				
				input += scan.nextLine() + "\n";
			}
			
			tileMaps[i] = input;
			
			if(scan.hasNextLine()){
				
				// Advance the scanner
				scan.nextLine();
			}
			
//			DEBUG
//			Gdx.app.log(Perspective.TAG, "Tiles[" + i + "]: " +  tileMaps[i]);
		}
		
		for(int i = 0; i < 6; i++){
			
			input = "";
			
			for(int j = 0; j < Plane.DIMENSION; j++){
				
				input += scan.nextLine() + "\n";
			}
			
			itemMaps[i] = input;
			
			if(scan.hasNextLine()){
				
				// Advance the scanner
				scan.nextLine();
			}
			
//			DEBUG
//			Gdx.app.log(Perspective.TAG, itemMaps[i]);
		}
		
		for(int i = 0; i < 6; i++){
			
			if(currentFace == i){
				
//				DEBUG
				Gdx.app.log(Perspective.TAG, "Passing true now, i=" + i);
				
				faces[i] = new GameScreen2D(game, true, tileMaps[i], itemMaps[i], this);
			}
			else{
				
				faces[i] = new GameScreen2D(game, false, tileMaps[i], itemMaps[i], this);
			}
		}
		
		setScreen(FACE_FRONT);
		showScreen();
	}
	
	public void setScreen(int newScreen){
		
		Plane oldFace = faces[currentFace].getLevel2D();
		int oldRow = oldFace.getCharacter().getRow();
		int oldColumn = oldFace.getCharacter().getColumn();
		
		Plane newFace = faces[newScreen].getLevel2D();
		
		if(p.findTileAt(oldRow, oldColumn, newFace).canMoveTo()){
			
			oldFace.setCharacterState(false);
			
			this.currentFace = newScreen;
			
			
			newFace.abruptlyMoveCharacter(oldRow, oldColumn);
			
			newFace.setCharacterState(true);
		}
	}
	
	public void showScreen(){
		
		if(display2D){
			
			game.setScreen(faces[currentFace]);
		}
		else{
			
			game.setScreen(view3D);
		}
	}
	
	public boolean isDisplaying2D(){
		
		return display2D;
	}
	
	public void togglePerspective(){
		
		if(display2D){
			
			display2D = false;
		}
		else{
			
			display2D = true;
		}
	}
}
