package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.models3D.SpinningTexturedCube;
import com.cap4053.perspective.view.SplashScreenInputProcessor;

public class SplashScreen extends PerspectiveScreen{

	private SpinningTexturedCube mCube;
	
    public SplashScreen(Perspective game){
    	
    	super(game);
    	
    	Gdx.input.setInputProcessor(new GestureDetector(new SplashScreenInputProcessor(game)));
    }
    
	@Override
	public void render(float delta) {
		
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        //	We begin drawing the 3D objects here

		Gdx.gl10.glMatrixMode(GL10.GL_MODELVIEW);
		Gdx.gl10.glLoadIdentity();

		Gdx.gl10.glTranslatef(0, 0, -3.0f);
		
		//Controls horizontal rotation
		Gdx.gl10.glRotatef(mCube.getAngleX(), 0, 1, 0);
		
		//Controls vertical rotation
		Gdx.gl10.glRotatef(mCube.getAngleY(),  1, 0, 0);

		mCube.update();
        mCube.draw();
	}

	@Override
	public void resize(int width, int height) {
		
		float ratio = (float) width / height;
		 
        Gdx.gl10.glMatrixMode(GL10.GL_PROJECTION);
        Gdx.gl10.glLoadIdentity();
        Gdx.gl10.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	}
	
	@Override
	public void dispose() {
		
		mCube.dispose();
		
		super.dispose();
	}

	@Override
	public void show() {
		
		Texture frontTexture = new Texture(Gdx.files.internal("data/perspective_cube_front.png"));
		Texture otherTexture = new Texture(Gdx.files.internal("data/perspective_cube_other.png"));
		
		this.mCube = new SpinningTexturedCube(frontTexture, otherTexture, otherTexture, otherTexture, otherTexture, otherTexture, 1.0f);
		
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
}
