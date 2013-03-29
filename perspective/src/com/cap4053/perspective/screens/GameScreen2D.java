package com.cap4053.perspective.screens;

import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.cap4053.perspective.Perspective;
import com.cap4053.perspective.backends.Plane;
import com.cap4053.perspective.models2D.PerspectiveObject;
import com.cap4053.perspective.view.GameInputProcessor2D;

public class GameScreen2D extends PerspectiveScreen{

	public static final float HORIZONTAL_MARGIN = 100.0f;
	public static final float VERTICAL_MARGIN = 20.0f;
	
	private Plane level2D;
	
	public GameScreen2D(Perspective game) {
		
		super(game);
		
		String tileDescription = 	"F F F F F F F\n" +
									"F B B B B B F\n" +
									"F B F F F B F\n" +
									"F B F B B B F\n" +
									"F B F F F B F\n" +
									"F B B B F B F\n" +
									"F F F F F B F\n";
		
		String itemDescription = 	"D D D E E E H\n" +
									"E E E E E E E\n" +
									"E E E E E E E\n" +
									"E E E E E E E\n" +
									"E E S E E E E\n" +
									"E E E E E E E\n" +
									"E E E E E E E\n";

		level2D = new Plane();
		level2D.initialize(0, 0, tileDescription, itemDescription, stage);
	}
	
	@Override
	public void show() {
		
		super.show();
		
		// TODO: Set input processor to just the stage?
		Gdx.input.setInputProcessor(new GameInputProcessor2D(game, this));
	}
	
	public void resize(int width, int height){
		
		super.resize(width, height);
	}

	/**
	 * @return the level2D
	 */
	public Plane getLevel2D() {
		
		return level2D;
	}

	public Texture getScreen(/*int x, int y, int w, int h, boolean flipY*/) {
		TextureRegion front = ScreenUtils.getFrameBufferTexture((int)HORIZONTAL_MARGIN, (int)VERTICAL_MARGIN,
										256, 256);
		return front.getTexture();
//		Gdx.gl.glPixelStorei(GL10.GL_PACK_ALIGNMENT, 1);
//        
//        final Pixmap pixmap = new Pixmap(w, h, Pixmap.Format.RGBA8888);
//        ByteBuffer pixels = pixmap.getPixels();
//        Gdx.gl.glReadPixels(x, y, w, h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, pixels);
//        
//        final int numBytes = w * h * 4;
//        byte[] lines = new byte[numBytes];
//        if (flipY) {
//                final int numBytesPerLine = w * 4;
//                for (int i = 0; i < h; i++) {
//                        pixels.position((h - i - 1) * numBytesPerLine);
//                        pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
//                }
//                pixels.clear();
//                pixels.put(lines);
//        } else {
//                pixels.clear();
//                pixels.get(lines);
//        }
//        
//        return new Texture(pixmap);
	}
}
