package com.cap4053.perspective_desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "perspective";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;
		cfg.vSyncEnabled = true;
		
		new LwjglApplication(new com.cap4053.perspective.Perspective(), cfg);
	}
}
