package com.cap4053.perspective.backends;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class GameSave{
	
	private FileHandle saveFile;
	private Json json;
	public SaveFileData fileData;
	public SaveFileData loadData;
	
	public GameSave(){
		fileData = new SaveFileData(10, 20, 3, 500);
		json = new Json();
	}
	
	public void save(){
		saveFile = Gdx.files.external("saveFile.txt");
		//saveFile.writeString(Base64Coder.encodeString("test data"), false);
		saveFile.writeString(Base64Coder.encodeString(json.toJson(fileData)), false);
	}
	
	public void load(){
		saveFile = Gdx.files.external("saveFile.txt");
		//System.out.println(Base64Coder.decodeString(saveFile.readString()));
		SaveFileData loadData = json.fromJson(SaveFileData.class, Base64Coder.decodeString(saveFile.readString()));
		System.out.println(loadData.toString());
	}
	
}