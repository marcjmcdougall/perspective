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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.cap4053.perspective.Perspective;

public class MenuScreen extends PerspectiveScreen{
	
	Perspective game;
	Stage stage;
	BitmapFont black;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	TextButton button;
	TextButton options;
	Texture background;
	Sprite sprite;
	//AtlasRegion region;
	Drawable splashDrawable;
	Image splashImage;
	
	public MenuScreen(Perspective game) {
		
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
			
		batch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
		
		super.resize(width, height);
		
		if(stage==null)
			stage = new Stage(width,height,true);
		
		stage.clear();
		
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle style = new TextButtonStyle();
		
		style.up = skin.getDrawable("buttonnormal");
		style.down = skin.getDrawable("buttonpressed");
		style.font = black;
		
		button = new TextButton("Play!",style);
		button.setWidth(150);
		button.setHeight(50);
		button.setX(Gdx.graphics.getWidth()/2-(button.getWidth()+button.getWidth()/2));
		button.setY(Gdx.graphics.getHeight()/2-(button.getHeight()*2));
		
		options = new TextButton("Options",style);
		options.setWidth(150);
		options.setHeight(50);
		options.setX(Gdx.graphics.getWidth()/2+(button.getWidth()-button.getWidth()/2));
		options.setY(Gdx.graphics.getHeight()/2-(button.getHeight()*2));
		
		button.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new LevelSelectorScreen(game));
			}
		});
		
		options.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				game.setScreen(new OptionsScreen(game));
			}
		});
		
		stage.addActor(button);
		stage.addActor(options);
		
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		//Texture.setEnforcePotImages(false);
		background = new Texture(Gdx.files.internal("data/perspective_cube_other.png"));
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
