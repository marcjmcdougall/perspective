package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.LevelManager;
import com.cap4053.perspective.models3D.MoveableTexturedCube;
import com.cap4053.perspective.view.GameInputProcessor3D;

public class GameScreen3D extends PerspectiveScreen {

	private MoveableTexturedCube cube;
	private LevelManager manager;
	
	Texture front, back, left, right, top, bottom;
	
	public GameScreen3D(Perspective game, LevelManager manager) {
		
		super(game);
		
		this.manager = manager;
		
		Texture front = new Texture(Gdx.files.internal("data/debug_front.png"));
		Texture back = new Texture(Gdx.files.internal("data/debug_back.png"));
		Texture left = new Texture(Gdx.files.internal("data/debug_left.png"));
		Texture right = new Texture(Gdx.files.internal("data/debug_right.png"));
		Texture top = new Texture(Gdx.files.internal("data/debug_top.png"));
		Texture bottom = new Texture(Gdx.files.internal("data/debug_bottom.png"));
		
		this.front = front;
		this.back = back;
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "**New Default Game Screen Created**");
	}
	
	public GameScreen3D(Perspective game, Texture front, Texture back, Texture left, Texture right, Texture top, Texture bottom) {
		
		//overloaded non-default constructor
		super(game);
		
		this.front = front;
		this.back = back;
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "**New Game Screen Created**");
	}
	
	@Override
	public void show(){
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "**Showing Game Screen Now**");
		
		Gdx.input.setInputProcessor(new GameInputProcessor3D(game, manager));
		
		cube = new MoveableTexturedCube(front, back, left, right, top, bottom);
		
		Gdx.gl10.glDisable(GL10.GL_DITHER);

        /*
         * Some one-time OpenGL initialization can be made here
         * probably based on features of this particular context
         */
        Gdx.gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

    	Gdx.gl10.glClearColor(1, 1, 1, 1);
	    
	    Gdx.gl10.glEnable(GL10.GL_CULL_FACE);
	    Gdx.gl10.glShadeModel(GL10.GL_SMOOTH);
	    Gdx.gl10.glEnable(GL10.GL_DEPTH_TEST);
	}
	
	public void render(float delta){
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "**Rendering Game Screen Now**");
		
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        //	We begin drawing the 3D objects here

		Gdx.gl10.glMatrixMode(GL10.GL_MODELVIEW);
		Gdx.gl10.glLoadIdentity();
		
//		DEBUG
//		Gdx.app.log(Perspective.TAG, "Scale: " + cube.getScale());
		
		Gdx.gl10.glTranslatef(0, 0, cube.getScale());
		
		//Controls horizontal rotation
		Gdx.gl10.glRotatef(cube.getAngleX(), 0, 1, 0);
		
//		Gdx.app.log(Perspective.TAG, "Cube Angle X: " + cube.getAngleX());
		
		//Controls vertical rotation
		Gdx.gl10.glRotatef(cube.getAngleY(),  1, 0, 0);
		
//		Gdx.app.log(Perspective.TAG, "Cube Angle Y: " + cube.getAngleY());

		cube.update();
        cube.draw();
	}
	
	@Override
	public void dispose() {
		
		if(true/* TODO: Replace with: "If this method is being called because the back button was pressed" */){
				
			cube.dispose();
			
			super.dispose();
		}
	}
	
	public void resize(int width, int height) {
		
		float ratio = (float) width / height;
		 
        Gdx.gl10.glMatrixMode(GL10.GL_PROJECTION);
        Gdx.gl10.glLoadIdentity();
        Gdx.gl10.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	}
	
	public MoveableTexturedCube getCube(){
		
		return cube;
	}
}
