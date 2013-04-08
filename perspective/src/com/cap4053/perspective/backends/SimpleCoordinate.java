package com.cap4053.perspective.backends;

/**
 * A class dedicated to solely encapsulating a row and column in a single data structure.
 * 
 * @author Marc J. McDougall
 */
public class SimpleCoordinate {

	// Representations of the row and column of the coordinate
	private int row;
	private int column;
	
	/**
	 * Basic constructor.  Provides input for the row and column.
	 * 
	 * @param row The row that should be assigned to this coordinate.
	 * @param column The column that should be assigned to this coordinate.
	 */
	public SimpleCoordinate(int row, int column){
		
		// Assign the variables appropriately
		this.row = row;
		this.column = column;
	}

	/**
	 * @return The row
	 */
	public int getRow() {
		
		return row;
	}

	/**
	 * @param row The row to set
	 */
	public void setRow(int row) {
		
		this.row = row;
	}

	/**
	 * @return The column
	 */
	public int getColumn() {
		
		return column;
	}

	/**
	 * @param column The column to set
	 */
	public void setColumn(int column) {
		
		this.column = column;
	}
}
