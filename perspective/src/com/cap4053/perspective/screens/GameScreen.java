package com.cap4053.perspective.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.cap4053.perspective.Perspective;

public class GameScreen extends PerspectiveScreen {

	private Stage contextMenu;
	private boolean displayMenu;
	private int time;
	private float elapsed = 0;
	
	private SpriteBatch batch;
	private BitmapFont black;
	private Texture clockTexture;
	private Image clockImg;

	public GameScreen(Perspective game, Stage contextMenu) {
		
		super(game);
		
		this.contextMenu = contextMenu;
		this.setDisplayMenu(false);
	}
	
	@Override
	public void show() {
		
		super.show();
		batch = new SpriteBatch();
		black = new BitmapFont(Gdx.files.internal("data/blackfont.fnt"),false);
		clockTexture = new Texture(Gdx.files.internal("data/items/item_clock256.png"));
		clockImg = new Image(clockTexture);
	}
	
	@Override
	public void render(float delta) {
		
		super.render(delta);
		
		if(isDisplayMenu()){
			
			contextMenu.act();
			contextMenu.draw();
		}
		
		
		clockTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		clockImg.setPosition(Gdx.graphics.getWidth()/2, 10);
		clockImg.setSize(50, 50);
		
		
		
		game.continueTime(delta);
	
		time = game.getTime();
		
		batch.begin();
		black.draw(batch, time/60 + ":" + (time%60)/10 +"" + time%10, Gdx.graphics.getWidth()/2 + 55, 50);
		batch.end();
		
		stage.addActor(clockImg);
		
	}
	
	@Override
	public void dispose() {
		
		super.dispose();
		
		contextMenu.dispose();
	}

	public Stage getContextMenu() {
		
		return contextMenu;
	}

	public void setContextMenu(Stage contextMenu) {
		
		this.contextMenu = contextMenu;
	}

	/**
	 * @return the displayMenu
	 */
	public boolean isDisplayMenu() {
		
		return displayMenu;
	}

	/**
	 * @param displayMenu the displayMenu to set
	 */
	public void setDisplayMenu(boolean displayMenu) {
		
		this.displayMenu = displayMenu;
	}

}
