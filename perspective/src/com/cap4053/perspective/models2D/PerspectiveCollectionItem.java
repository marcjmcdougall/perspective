package com.cap4053.perspective.models2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PerspectiveCollectionItem extends Image{
	private PerspectiveCollectionItem(Texture texture){
		super(texture);
	}
	
	public static PerspectiveCollectionItem create(String type, boolean enabled){
		Texture texture = null;
		if(type.equalsIgnoreCase("Stars")){
			if(!enabled){
				texture = new Texture(Gdx.files.internal("data/items/uncollected_star.png"));
				texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			}
			else{
				texture = new Texture(Gdx.files.internal("data/items/collected_star.png"));
				texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			}
		}
		else if(type.equalsIgnoreCase("Hearts")){
			if(!enabled){
				texture = new Texture(Gdx.files.internal("data/items/uncollected_heart.png"));
				texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			}
			else{
				texture = new Texture(Gdx.files.internal("data/items/collected_heart.png"));
				texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			}
		}
		else if(type.equalsIgnoreCase("Summary-Stars")){
			if(!enabled){
				texture = new Texture(Gdx.files.internal("data/items/summary-star-unfilled.png"));
				texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			}
			else{
				texture = new Texture(Gdx.files.internal("data/items/summary-star-filled.png"));
				texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			}
		}
		else if(type.equalsIgnoreCase("Summary-Hearts")){
			if(!enabled){
				texture = new Texture(Gdx.files.internal("data/items/summary-heart-unfilled.png"));
				texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			}
			else{
				texture = new Texture(Gdx.files.internal("data/items/summary-heart-filled.png"));
				texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			}
		}
		
		
		return new PerspectiveCollectionItem(texture);
	}
}
