package com.cap4053.perspective.models3D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;

public abstract class TexturedCube {

	private static final int MAX_VERTICES = 4;
	private static final int MAX_INDICES = 6;
	
	private Mesh frontMesh;
	private Mesh backMesh;
	private Mesh leftMesh;
	private Mesh rightMesh;
	private Mesh topMesh;
	private Mesh bottomMesh;
	
    private Texture frontTexture;
    private Texture backTexture;
    private Texture leftTexture;
    private Texture rightTexture;
    private Texture topTexture;
    private Texture bottomTexture;
    
    private float angleX;
    private float angleY;
    
	public TexturedCube(Texture frontTexture, Texture backTexture, Texture leftTexture, Texture rightTexture, Texture bottomTexture, Texture topTexture){
		
		this.frontTexture = frontTexture;
		this.backTexture = backTexture;
		this.leftTexture = leftTexture;
		this.rightTexture = rightTexture;
		this.topTexture = topTexture;
		this.bottomTexture = bottomTexture;
		
		this.angleX = 0.0f;
		this.angleY = 0.0f;
		
		// This array holds all the vertex information (all 8 "points" of a cube, meaning A, B, C, D, E, F, G, and H)
		// Note, there are 3 sets of vertices for each because we want to map the texture differently each time!
		
		float frontVertices[] = {
				
				-1.0f, 1.0f, 1.0f, 0.0f, 0.0f,   // H (Bottom Left)		0
				-1.0f, -1.0f, 1.0f, 0.0f, 1.0f,  // E (Top Left)		1
				 1.0f, 1.0f, 1.0f, 1.0f, 0.0f,   // G (Bottom Right)	2
				 1.0f, -1.0f, 1.0f, 1.0f, 1.0f,  // F (Top Right)		3
		};
		
		short frontIndices[] = {
				
			3, 1, 0,	0, 2, 3
//			3, 2, 0,	0, 1, 3	
		};
		
		float backVertices[] = {
				
				-1.0f, -1.0f, -1.0f, 1.0f, 1.0f, // A (Top Right)	0
				1.0f, -1.0f, -1.0f, 0.0f, 1.0f,  // B (Top Left)	1
				1.0f, 1.0f, -1.0f, 0.0f, 0.0f,   // C (Bottom Left)	2
				-1.0f, 1.0f, -1.0f, 1.0f, 0.0f,  // D (Bottom Right)	3
		};
		
		short backIndices[] = {
				
				2, 3, 1,	1, 3, 0
		};
		
		float leftVertices[] = {
				
				 -1.0f, -1.0f, 1.0f, 1.0f, 1.0f,  // E (Left)	0
				 -1.0f, 1.0f, 1.0f, 1.0f, 0.0f,   // H (Left)	1
				 -1.0f, -1.0f, -1.0f, 0.0f, 1.0f, // A (Left)	2
				 -1.0f, 1.0f, -1.0f, 0.0f, 0.0f,  // D (Left)	3
		};
		
		short leftIndices[] = {
				
				0, 3, 1,	0, 2, 3
		};
		
		float rightVertices[] = {
				
				 1.0f, 1.0f, -1.0f, 1.0f, 0.0f,   // C (Right)	0
				 1.0f, -1.0f, -1.0f, 1.0f, 1.0f,  // B (Right)	1
				 1.0f, -1.0f, 1.0f, 0.0f, 1.0f,   // F (Right)	2
				 1.0f, 1.0f, 1.0f, 0.0f, 0.0f,    // G (Right)	3
		};
		
		short rightIndices[] = {
				
				0, 2, 3,	1, 2, 0
		};
		
		float topVertices[] = {
				
				 -1.0f, -1.0f, 1.0f, 0.0f, 0.0f,  // E (Top)	0
				 1.0f, -1.0f, -1.0f, 1.0f, 1.0f,  // B (Top)	1
				 1.0f, -1.0f, 1.0f, 1.0f, 0.0f,   // F (Top)	2
				 -1.0f, -1.0f, -1.0f, 0.0f, 1.0f, // A (Top)	3
		};
		
		short topIndices[] = {
				
				3, 0, 2,	1, 3, 2
		};
		 
		float bottomVertices[] = {
				
				 1.0f, 1.0f, -1.0f, 1.0f, 0.0f,   // C (Bottom)	0
				 -1.0f, 1.0f, -1.0f, 0.0f, 0.0f,  // D (Bottom)	1
				 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,    // G (Bottom)	2
				 -1.0f, 1.0f, 1.0f, 0.0f, 1.0f,   // H (Bottom)	3
		};
		
		short bottomIndices[] = {
				
				2, 3, 1,	2, 1, 0
		};
		
        // This is the index information.  This is where we tell OpenGL HOW to draw the vertexes on screen.
        // Basically, here, as you can see, it is separated into 2 columns, and therefore implies that each
        // row represents one "face" of the cube.
        
		frontMesh = configureMesh(frontVertices, frontIndices);
		backMesh = configureMesh(backVertices, backIndices);
		leftMesh = configureMesh(leftVertices, leftIndices);
		rightMesh = configureMesh(rightVertices, rightIndices);
		topMesh = configureMesh(topVertices, topIndices);
		bottomMesh = configureMesh(bottomVertices, bottomIndices);
	}
	
	/**
	 * The updating of the Cube is abstract because we would like to have 2 different
	 * implementations of the Cube.  One that spins on it's own, and another that 
	 * the user can control.
	 */
	public abstract void update();
	
	public void dispose(){
		
		frontMesh.dispose();
		backMesh.dispose();
		leftMesh.dispose();
		rightMesh.dispose();
		topMesh.dispose();
		bottomMesh.dispose();
		
	    frontTexture.dispose();
	    backTexture.dispose();
	    leftTexture.dispose();
	    rightTexture.dispose();
	    topTexture.dispose();
	    bottomTexture.dispose();
	}
	
	public void draw(){
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.graphics.getGL10().glEnable(GL10.GL_TEXTURE_2D);
		Gdx.graphics.getGL10().glFrontFace(GL10.GL_CW);
		
		drawMesh(frontTexture, frontMesh);
		drawMesh(backTexture, backMesh);
		drawMesh(leftTexture, leftMesh);
		drawMesh(rightTexture, rightMesh);
		drawMesh(topTexture, topMesh);
		drawMesh(bottomTexture, bottomMesh);
	}
	
	private Mesh configureMesh(float[] vertices, short[] indices){
		
		Mesh output = new Mesh(true, MAX_VERTICES, MAX_INDICES, 
				new VertexAttribute(Usage.Position, 3, "a_position"),
				new VertexAttribute(Usage.TextureCoordinates, 2, "a_texture"));
		
		output.setVertices(vertices);
    	output.setIndices(indices);
    	
    	return output;
	}
	
	private void drawMesh(Texture texture, Mesh mesh){
		
		texture.bind();
		mesh.render(GL10.GL_TRIANGLES, 0, 6);
	}
	
	public float getAngleX(){
		
		return angleX;
	}
	
	public float getAngleY(){
		
		return angleY;
	}
	
	public void setAngleX(float angleX){
		
		this.angleX = angleX;
	}
	
	public void setAngleY(float angleY){
		
		this.angleY = angleY;
	}
}
