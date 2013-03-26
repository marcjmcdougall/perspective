package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.cap4053.perspective.Perspective;

public class MenuScreen extends PerspectiveScreen{
	
	private BitmapFont black;
	private TextureAtlas atlas;
	private Skin skin;
	private SpriteBatch batch;
	private Texture background;
	private Sprite sprite;
	private Image splashImage;
	
	private Button button;
	private Button options;
	
	public MenuScreen(Perspective game) {
		
		super(game);
	}

	@Override
	public void render(float delta) {
		
		super.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		
		super.resize(width, height);
	}

	@Override
	public void show() {
		
		super.show();
		
		//Texture.setEnforcePotImages(false);
		background = new Texture(Gdx.files.internal("data/perspective_cube_other.png"));
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(background);
		
		splashImage = new Image(background);	
		splashImage.setFillParent(true);
		
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/MainMenu.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		black = new BitmapFont(Gdx.files.internal("data/blackfont.fnt"),false);
		
		stage.clear();
		
//		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle style1 = new TextButtonStyle();
		TextButtonStyle style2 = new TextButtonStyle();
		
		style1.up = skin.getDrawable("Play_Button");
		style1.down = skin.getDrawable("Play_Button_Pushed");
		style1.font = black;
		
		button = new TextButton("",style1);
		button.setWidth(120);
		button.setHeight(50);
		button.setX(Gdx.graphics.getWidth()/2-(button.getWidth()*2-button.getWidth()/2));
		button.setY(Gdx.graphics.getHeight()/2-(button.getHeight()*2));
		
		style2.up = skin.getDrawable("Options_Button");
		style2.down = skin.getDrawable("Options_Button_Pushed");
		style2.font = black;
		
		options = new TextButton("",style2);
		options.setWidth(120);
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
		
		stage.addActor(splashImage);
		stage.addActor(button);
		stage.addActor(options);
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
	}
}
