package com.cap4053.perspective.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cap4053.perspective.Perspective;

public class GameScreen extends PerspectiveScreen {

	private Stage contextMenu;
	private boolean displayMenu;
	
	public GameScreen(Perspective game, Stage contextMenu) {
		
		super(game);
		
		this.contextMenu = contextMenu;
		this.setDisplayMenu(false);
	}
	
	@Override
	public void render(float delta) {
		
		super.render(delta);
		
		if(isDisplayMenu()){
			
			contextMenu.act();
			contextMenu.draw();
		}
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
