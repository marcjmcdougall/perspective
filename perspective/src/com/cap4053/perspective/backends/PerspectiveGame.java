package com.cap4053.perspective.backends;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * An abstract class dedicated to providing some generic functionality for 
 * a the Game.  Simply enforces the method "setScreen()" to
 * 
 * @author Marc J McDougall
 */
public abstract class PerspectiveGame extends Game {

	@Override
	public void setScreen(Screen screen) {
		
		// Show the parametrized Screen
		screen.show();
		
		// Resize it appropriately
		screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
}
