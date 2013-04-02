package com.cap4053.perspective.backends;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public abstract class PerspectiveGame extends Game {

	@Override
	public void setScreen(Screen screen) {
		
		screen.show();
		screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
}
