package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.cap4053.perspective.Perspective;

public class LevelTransitionScreen extends PerspectiveScreen {
	
	Perspective game;
	Stage stage;
	BitmapFont black;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	Sprite sprite;
	TextButton stage1;
	TextButton stage2;
	
	public int width;
	public int height;
	
	public LevelTransitionScreen(Perspective game) {
		super(game);
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		batch.begin();
		
		stage.draw();
		
		//Gdx.app.log(Perspective.TAG, "Drawing");
	      
		/*int length = width;
		black.draw(batch, "blabla", 0, 2*length/3);
		stage.draw();
		Label textbox = new Label("testtext", skin);
		textbox.draw(batch, 0);*/
				
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		
		if(stage==null)
			stage = new Stage(width,height,true);
		stage.clear();
		
		int length = width;
		
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle style = new TextButtonStyle();
		
		style.up = skin.getDrawable("buttonnormal");
		style.down = skin.getDrawable("buttonpressed");
		style.font = black;
		
		stage1 = new TextButton("Advance to Next Level",style);
		stage1.setWidth(length);
		stage1.setHeight(length/3);
		stage1.setX(0);
		stage1.setY(0);
		
		stage1.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new GameScreen2D(game));
			}
		});
		
		stage.addActor(stage1);
		
		super.resize(width, height);
	}
	
	@Override
	public void show() {
		
		Texture background = new Texture(Gdx.files.internal("data/perspective_cube_other.png"));
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(background);
		
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		black = new BitmapFont(Gdx.files.internal("data/blackfont.fnt"),false);
		
	}
	
	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		black.dispose();
		stage.dispose();
	}
	
}