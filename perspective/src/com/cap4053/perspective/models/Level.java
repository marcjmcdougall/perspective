package com.cap4053.perspective.models;

public class Level {
	private Texture[][] map;
	
	public Level(){
		map = new Texture[10][10];
		for(int i = 0; i < map.length; i++){
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = 0;
			}
		}
		
		
	}
}
