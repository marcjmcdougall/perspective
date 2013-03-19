package com.cap4053.perspective;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

public class Cube {

	private Mesh mesh;
    private Texture mTexture;
    
	public Cube(){
		
		// This array holds all the vertex information (all 8 "points" of a cube)
		float vertices[] = {
				 
				 -1.0f, -1.0f, -1.0f, Color.toFloatBits(255, 0, 0, 255),
				 1.0f, -1.0f, -1.0f, Color.toFloatBits(0, 255, 0, 255),
				 1.0f, 1.0f, -1.0f, Color.toFloatBits(0, 0, 255, 255),
				 -1.0f, 1.0f, -1.0f, Color.toFloatBits(0, 255, 0, 255),
				 -1.0f, -1.0f, 1.0f, Color.toFloatBits(255, 0, 0, 255),
				 1.0f, -1.0f, 1.0f, Color.toFloatBits(0, 0, 255, 255),
				 1.0f, 1.0f, 1.0f, Color.toFloatBits(0, 255, 0, 255),
				 -1.0f, 1.0f, 1.0f, Color.toFloatBits(0, 0, 255, 255)
		 };
		 
		// This array holds the color information for each vertex.  Note that each color will (by default)
		// gradually blend into the other color as we progress from one vertext to another.
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

        // This is the index information.  This is where we tell OpenGL HOW to draw the vertexes on screen.
        // Basically, here, as you can see, it is separated into 2 columns, and therefore implies that each
        // row represents one "face" of the cube.
        short indices[] = {
        		
                0, 4, 5,    0, 5, 1,
                1, 5, 6,    1, 6, 2,
                2, 6, 7,    2, 7, 3,
                3, 7, 4,    3, 4, 0,
                4, 7, 6,    4, 6, 5,
                3, 0, 1,    3, 1, 2
        };
        
        // This maps the texture to the cube.  Note that these values are all the same, because we are 
        // placing the same image on each face of the cube.  This is known as UV mapping.
        float texture[] = { 
        		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f, 
	    		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f,
	    		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f,
	    		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f,
	    		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f,
	    		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f,

	    							};
        
        if(mesh == null){
        	
        	mesh = new Mesh(true, 36, indices.length, 
        				new VertexAttribute(Usage.Position, 3, "a_position"),
        				new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
//        				new VertexAttribute(Usage.TextureCoordinates, 2, "a_texture"));
        	
        	mesh.setVertices(vertices);
        	mesh.setIndices(indices);
        }
        
        mTexture = new Texture(Gdx.files.internal("data/libgdx.png"));
	}
	
	public void draw(){
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		Gdx.graphics.getGL10().glEnable(GL10.GL_TEXTURE_2D);
//		mTexture.bind();
        mesh.render(GL10.GL_TRIANGLES, 0, 36);
	}
}
