package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.models3D.MoveableTexturedCube;
import com.cap4053.perspective.view.GameInputProcessor3D;

public class GameScreen3D extends PerspectiveScreen {

	private MoveableTexturedCube cube;
	Texture front, back, left, right, top, bottom;
	
	public GameScreen3D(Perspective game) {
		
		super(game);
		
		front = new Texture(Gdx.files.internal("data/sample_face.png"));
		back = new Texture(Gdx.files.internal("data/sample_face.png"));
		left = new Texture(Gdx.files.internal("data/sample_face.png"));
		right = new Texture(Gdx.files.internal("data/sample_face.png"));;
		top = new Texture(Gdx.files.internal("data/sample_face.png"));
		bottom = new Texture(Gdx.files.internal("data/sample_face.png"));
		
//		DEBUG
		Gdx.app.log(Perspective.TAG, "**New Default Game Screen Created**");
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
		
		Gdx.input.setInputProcessor(new GameInputProcessor3D(game));
		
//		Texture front = new Texture(Gdx.files.internal("data/sample_face.png"));
//		Texture back = new Texture(Gdx.files.internal("data/sample_face.png"));
//		Texture left = new Texture(Gdx.files.internal("data/sample_face.png"));
//		Texture right = new Texture(Gdx.files.internal("data/sample_face.png"));;
//		Texture top = new Texture(Gdx.files.internal("data/sample_face.png"));
//		Texture bottom = new Texture(Gdx.files.internal("data/sample_face.png"));
		
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

		Gdx.gl10.glTranslatef(0, 0, -3.0f);
		
		//Controls horizontal rotation
		Gdx.gl10.glRotatef(cube.getAngleX(), 0, 1, 0);
		
		//Controls vertical rotation
		Gdx.gl10.glRotatef(cube.getAngleY(),  1, 0, 0);

		cube.update();
        cube.draw();
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
