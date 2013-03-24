package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
		
		Slider.SliderStyle style = new Slider.SliderStyle();
		
		style.background = (skin.getDrawable("buttonnormal"));
		style.knob = skin2.getDrawable("knob");
		
		slider1 = new Slider(0, 20, 1, false, style);
		slider2 = new Slider(0, 20, 1, false, style);
				
		
		slider1.setX(width/2);
		slider1.setY(height/2-slider1.getHeight());
		
		slider2.setX(width/2);
		slider2.setY(height/2-height/4-slider2.getHeight());
		
		stage.addActor(slider1);
		stage.addActor(slider2);
	}

	@Override
	public void show() {
		
		background = new Texture(Gdx.files.internal("data/perspective_cube_other.png"));
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(background);
		
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/button.pack");
		atlas2 = new TextureAtlas("data/buttonslider.pack");
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
