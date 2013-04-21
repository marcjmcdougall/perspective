package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.cap4053.perspective.Perspective;

public class OptionsScreen extends PerspectiveScreen{

	Perspective game;
	Stage stage;
	BitmapFont black;
	TextureAtlas atlas;
	TextureAtlas atlas2;
	Skin skin;
	Skin skin2;
	SpriteBatch batch;
	TextButton button;
	TextButton options;
	Texture background;
	Sprite sprite;
	Slider slider1;
	Slider slider2;
	Music musicPlayer;
	
	public OptionsScreen(Perspective game) {
		super(game);
		this.game = game;
	}

	@Override
	public void render(float delta) {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		batch.begin();
		
		stage.draw();
		
		batch.end();
		
		batch.begin();		
		black.draw(batch,"Options", width/2-width/7, height/2+height/3);
		black.draw(batch, "Music", width/2-width/3, height/2);
		black.draw(batch, "SFX", width/2-width/3, height/2-height/4);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
		if(stage==null)
			stage = new Stage(width,height,true);
		stage.clear();
		
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle style1 = new TextButtonStyle();
		style1.up = skin2.getDrawable("Button_Small");
		black.setScale(0.6f);
		style1.font = black;
		
		button = new TextButton("Back", style1);
		button.setWidth(80);
		button.setHeight(60);
		button.setPosition(20, Gdx.graphics.getHeight()-80);
		
		button.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				
				game.setScreen(new MenuScreen(game));
			}
		});
		
		Slider.SliderStyle style = new Slider.SliderStyle();
		
		style.background = (skin.getDrawable("buttonnormal"));
		style.knob = skin2.getDrawable("LED-Zen");
		
		
		slider1 = new Slider(0, 20, 1, false, style);
		slider2 = new Slider(0, 20, 1, false, style);
		slider1.setValue(game.musicVolume * 20);
		slider2.setValue(game.SFXVolume * 20);
		slider1.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				float slider1Val;
				slider1Val = slider1.getValue();
				//Debug Statement
				//System.out.println("Slider1Val is " + slider1Val);
				game.musicVolume = (slider1Val/20f);
				Music musicPlayer = game.getMusicPlayer();
				musicPlayer.setVolume(game.musicVolume);
			}
		});
		slider2.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent even, float x, float y,int pointer, int button){
				
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y,int pointer, int button){
				float slider2Val;
				slider2Val = slider2.getValue();
				//Debug Statement
				//System.out.println("Slider1Val is " + slider1Val);
				game.SFXVolume = (slider2Val/20f);
			}
		});
				
		
		slider1.setX(width/2);
		slider1.setY(height/2-slider1.getHeight());
		
		slider2.setX(width/2);
		slider2.setY(height/2-height/4-slider2.getHeight());
		
		stage.addActor(slider1);
		stage.addActor(slider2);
		stage.addActor(button);
	}

	@Override
	public void show() {
		
		background = new Texture(Gdx.files.internal("data/perspective_cube_other.png"));
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(background);
		
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/button.pack");
		atlas2 = new TextureAtlas("data/graybutton.pack");
		skin = new Skin();
		skin2 = new Skin();
		skin.addRegions(atlas);
		skin2.addRegions(atlas2);
		black = new BitmapFont(Gdx.files.internal("data/blackfont.fnt"),false);
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
