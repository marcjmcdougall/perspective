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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.cap4053.perspective.Perspective;

public class LevelSelectorScreen extends PerspectiveScreen {
	
	Perspective game;
	Stage stage;
	BitmapFont black;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	TextButton stage1;
	TextButton stage2;
	TextButton stage3;
	TextButton stage4;
	TextButton stage5;
	TextButton stage6;
	TextButton stage7;
	TextButton stage8;
	TextButton stage9;
	Sprite sprite;

	public LevelSelectorScreen(Perspective game) {
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
				
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if(stage==null)
			stage = new Stage(width,height,true);
		stage.clear();
		
		int length = width/9;
		
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle style = new TextButtonStyle();
		
		style.up = skin.getDrawable("buttonnormal");
		style.down = skin.getDrawable("buttonpressed");
		style.font = black;
		
		stage1 = new TextButton("1",style);
		stage1.setWidth(length);
		stage1.setHeight(length);
		stage1.setX(width/2-stage1.getWidth()/2 - stage1.getWidth()*2);
		stage1.setY(height/2-stage1.getHeight()/2 + stage1.getHeight()*2);
		
		
		stage2 = new TextButton("2",style);
		stage2.setWidth(length);
		stage2.setHeight(length);
		stage2.setX(width/2-stage2.getWidth()/2);
		stage2.setY(height/2-stage2.getHeight()/2 + stage2.getHeight()*2);
		
		stage3 = new TextButton("3",style);
		stage3.setWidth(length);
		stage3.setHeight(length);
		stage3.setX(width/2-stage3.getWidth()/2 + stage3.getWidth()*2);
		stage3.setY(height/2-stage3.getHeight()/2 + stage3.getHeight()*2);
		
		stage4 = new TextButton("4",style);
		stage4.setWidth(length);
		stage4.setHeight(length);
		stage4.setX(width/2-stage1.getWidth()/2 - stage1.getWidth()*2);
		stage4.setY(height/2-stage1.getHeight()/2);
		
		stage5 = new TextButton("5",style);
		stage5.setWidth(length);
		stage5.setHeight(length);
		stage5.setX(width/2-stage2.getWidth()/2);
		stage5.setY(height/2-stage2.getHeight()/2);
		
		stage6 = new TextButton("6",style);
		stage6.setWidth(length);
		stage6.setHeight(length);
		stage6.setX(width/2-stage3.getWidth()/2 + stage3.getWidth()*2);
		stage6.setY(height/2-stage3.getHeight()/2);
		
		stage7 = new TextButton("7",style);
		stage7.setWidth(length);
		stage7.setHeight(length);
		stage7.setX(width/2-stage1.getWidth()/2 - stage1.getWidth()*2);
		stage7.setY(height/2-stage1.getHeight()/2 - stage1.getHeight()*2);
		
		stage8 = new TextButton("8",style);
		stage8.setWidth(length);
		stage8.setHeight(length);
		stage8.setX(width/2-stage2.getWidth()/2);
		stage8.setY(height/2-stage2.getHeight()/2 - stage2.getHeight()*2);
		
		stage9 = new TextButton("9",style);
		stage9.setWidth(length);
		stage9.setHeight(length);
		stage9.setX(width/2-stage3.getWidth()/2 + stage3.getWidth()*2);
		stage9.setY(height/2-stage3.getHeight()/2 - stage3.getHeight()*2);
		
		
		
		stage1.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				
				game.setScreen(new GameScreen2D(game));
			}
		});

		stage2.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new GameScreen3D(game));
			}
		});
		
		stage3.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new GameScreen3D(game));
			}
		});
		
		stage4.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new GameScreen3D(game));
			}
		});
		
		stage5.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new GameScreen3D(game));
			}
		});
		
		stage6.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new GameScreen3D(game));
			}
		});
		
		stage7.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new GameScreen3D(game));
			}
		});
		
		stage8.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new GameScreen3D(game));
			}
		});
		stage9.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new GameScreen3D(game));
			}
		});
		
		stage.addActor(stage1);
		stage.addActor(stage2);
		stage.addActor(stage3);
		stage.addActor(stage4);
		stage.addActor(stage5);
		stage.addActor(stage6);
		stage.addActor(stage7);
		stage.addActor(stage8);
		stage.addActor(stage9);
		
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
