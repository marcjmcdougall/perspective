package com.cap4053.perspective;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;

public class Cube {

	private FloatBuffer   mVertexBuffer;
    private FloatBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
    
	public Cube(){
		
		 float vertices[] = {
				 
				 -1.0f, -1.0f, -1.0f,
				 1.0f, -1.0f, -1.0f,
				 1.0f, 1.0f, -1.0f,
				 -1.0f, 1.0f, -1.0f,
				 -1.0f, -1.0f, 1.0f,
				 1.0f, -1.0f, 1.0f,
				 1.0f, 1.0f, 1.0f,
				 -1.0f, 1.0f, 1.0f
		 };
		 
        float colors[] = {
        		
        		0.0f, 0.0f, 0.0f, 1.0f,
        		1.0f, 0.0f, 0.0f, 1.0f,
        		1.0f, 1.0f, 0.0f, 1.0f,
        		0.0f, 1.0f, 0.0f, 1.0f,
        		0.0f, 0.0f, 1.0f, 1.0f,
        		1.0f, 0.0f, 1.0f, 1.0f,
        		1.0f, 1.0f, 1.0f, 1.0f,
        		0.0f, 1.0f, 1.0f, 1.0f
        };

        byte indices[] = {
                0, 4, 5,    0, 5, 1,
                1, 5, 6,    1, 6, 2,
                2, 6, 7,    2, 7, 3,
                3, 7, 4,    3, 4, 0,
                4, 7, 6,    4, 6, 5,
                3, 0, 1,    3, 1, 2
        };
        
        // Buffers to be passed to gl*Pointer() functions
        // must be direct, i.e., they must be placed on the
        // native heap where the garbage collector cannot
        // move them.
        //
        // Buffers with multi-byte datatypes (e.g., short, int, float)
        // must have their byte order set to native order

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asFloatBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);

        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
	}
	
	public void draw(){
		
		
		int[] textures = new int[1];
		
		Gdx.gl10.glFrontFace(Gdx.gl10.GL_CW);
        Gdx.gl10.glVertexPointer(3, Gdx.gl10.GL_FLOAT, 0, mVertexBuffer);
        
//        Gdx.gl10.glColorPointer(4, Gdx.gl10.GL_FLOAT, 0, mColorBuffer);
        
//        for(i=0; i<6; ++i)
//        {
//            gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[i]);   //use texture of ith face
//            indexBuffer.position(6*i);                          //select ith face
//
//            //draw 2 triangles making up this face
//            gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, indexBuffer);
//        }
        
        Gdx.gl10.glBindTexture(Gdx.gl10.GL_TEXTURE_2D, textures[0]);
        
        Gdx.gl10.glTexParameterf(Gdx.gl10.GL_TEXTURE_2D, Gdx.gl10.GL_TEXTURE_MIN_FILTER,
                Gdx.gl10.GL_NEAREST);
        Gdx.gl10.glTexParameterf(Gdx.gl10.GL_TEXTURE_2D,
                Gdx.gl10.GL_TEXTURE_MAG_FILTER,
                Gdx.gl10.GL_LINEAR);

        Gdx.gl10.glTexParameterf(Gdx.gl10.GL_TEXTURE_2D, Gdx.gl10.GL_TEXTURE_WRAP_S,
                Gdx.gl10.GL_CLAMP_TO_EDGE);
        Gdx.gl10.glTexParameterf(Gdx.gl10.GL_TEXTURE_2D, Gdx.gl10.GL_TEXTURE_WRAP_T,
                Gdx.gl10.GL_CLAMP_TO_EDGE);

        Gdx.gl10.glTexEnvf(Gdx.gl10.GL_TEXTURE_ENV, Gdx.gl10.GL_TEXTURE_ENV_MODE,
                Gdx.gl10.GL_REPLACE);
        
        Gdx.gl.glDrawElements(Gdx.gl10.GL_TRIANGLES, 6, Gdx.gl10.GL_UNSIGNED_BYTE, mIndexBuffer);
	}
}
