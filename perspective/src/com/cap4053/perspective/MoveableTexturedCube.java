package com.cap4053.perspective;

import sun.reflect.generics.tree.BottomSignature;

import com.badlogic.gdx.graphics.Texture;

public class MoveableTexturedCube extends TexturedCube {

	public MoveableTexturedCube(Texture frontTexture, Texture backTexture, Texture leftTexture, Texture rightTexture, Texture topTexture, Texture bottomTexture) {
		
		super(frontTexture, backTexture, leftTexture, rightTexture, topTexture, bottomTexture);
	}

	@Override
	public void update() {
	
		// TODO: Implementation
		// Current, do nothing.
	}
}
