package com.cap4053.perspective;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

public class Cube {

	private Mesh mesh;
    private Texture mTexture;
    
	public Cube(Texture texture){
		
		this.mTexture = texture;
		
		// This array holds all the vertex information (all 8 "points" of a cube, meaning A, B, C, D, E, F, G, and H)
		// Note, there are 3 sets of vertices for each because we want to map the texture differently each time!
		float vertices[] = {
				 
				 -1.0f, -1.0f, -1.0f, 1.0f, 0.0f, // A (Top)	0
				 -1.0f, -1.0f, -1.0f, 1.0f, 0.0f, // A (Left)	1
				 -1.0f, -1.0f, -1.0f, 0.0f, 0.0f, // A (Back)	2
				 
				 1.0f, -1.0f, -1.0f, 0.0f, 0.0f,  // B (Top)	3
				 1.0f, -1.0f, -1.0f, 0.0f, 0.0f,  // B (Right)	4
				 1.0f, -1.0f, -1.0f, 1.0f, 0.0f,  // B (Back)	5
				 
				 1.0f, 1.0f, -1.0f, 0.0f, 1.0f,   // C (Bottom)	6
				 1.0f, 1.0f, -1.0f, 0.0f, 1.0f,   // C (Right)	7
				 1.0f, 1.0f, -1.0f, 1.0f, 1.0f,   // C (Back)	8
				 
				 -1.0f, 1.0f, -1.0f, 1.0f, 1.0f,  // D (Bottom)	9
				 -1.0f, 1.0f, -1.0f, 1.0f, 1.0f,  // D (Left)	10
				 -1.0f, 1.0f, -1.0f, 0.0f, 1.0f,  // D (Back)	11
				 
				 -1.0f, -1.0f, 1.0f, 1.0f, 1.0f,  // E (Top)	12
				 -1.0f, -1.0f, 1.0f, 0.0f, 0.0f,  // E (Left)	13
				 -1.0f, -1.0f, 1.0f, 1.0f, 0.0f,  // E (Front)	14
				 
				 1.0f, -1.0f, 1.0f, 0.0f, 1.0f,   // F (Top)	15
				 1.0f, -1.0f, 1.0f, 1.0f, 0.0f,   // F (Right)	16
				 1.0f, -1.0f, 1.0f, 0.0f, 0.0f,   // F (Front)	17
				 
				 1.0f, 1.0f, 1.0f, 0.0f, 0.0f,    // G (Bottom)	18
				 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,    // G (Right)	19
				 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,    // G (Front)	20
				 
				 -1.0f, 1.0f, 1.0f, 1.0f, 0.0f,   // H (Bottom)	21
				 -1.0f, 1.0f, 1.0f, 0.0f, 1.0f,   // H (Left)	22
				 -1.0f, 1.0f, 1.0f, 1.0f, 1.0f    // H (Front)	23
		 };
		 
        // This is the index information.  This is where we tell OpenGL HOW to draw the vertexes on screen.
        // Basically, here, as you can see, it is separated into 2 columns, and therefore implies that each
        // row represents one "face" of the cube.
        short indices[] = {
        		
        		// 23 indices total
        		23, 14, 20,		20, 14, 17,		// Front face
        		8, 5, 11,		2, 11, 5,		// Back face
        		19, 16, 4,		19, 4, 7,		// Right face
        		10, 1, 13,		10, 13, 22,		// Left face
        		12, 0, 15,		15, 0, 3,		// Top face
        		21, 18, 9,		18, 6, 9		// Bottom face
        };
        
        if(mesh == null){
        	
        	mesh = new Mesh(true, 72, 72, 
        				new VertexAttribute(Usage.Position, 3, "a_position"),
//        				new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
        				new VertexAttribute(Usage.TextureCoordinates, 2, "a_texture"));
        	
        	mesh.setVertices(vertices);
        	mesh.setIndices(indices);
        }
	}
	
	public void draw(){
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.graphics.getGL10().glEnable(GL10.GL_TEXTURE_2D);
		Gdx.graphics.getGL10().glFrontFace(GL10.GL_CCW);
		
		mTexture.bind();
		
        mesh.render(GL10.GL_TRIANGLES, 0, 72);
	}
}
