package com.cap4053.perspective;

public class SaveFileData{
	
	public int xcord;
	public int ycord;
	public int level;
	public int score;
	
	public SaveFileData(){
		
	}
	
	public SaveFileData(int x, int y, int level, int score){
		xcord = x;
		ycord = y;
		this.level = level;
		this.score = score;
	}
	
	public String toString(){
		String ret = new String();
		ret = "xcord: " + xcord + " " + "ycord: " + ycord + " " + "level: " + level + " " + "score: " + score;
		return ret;
	}
}