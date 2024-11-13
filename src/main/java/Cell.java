/*
 * NAME: Maxwell Klema
 * PROJECT: Project Three
 * COURSE: CS26000
 * INSTRUCTOR: KIM, B.
 * LAB TIME: N/A
 * DUE DATE: 11/14/24
 *
 * DESCRIPTION
 * 	A cell is used to represent a single section of the maze and to build a 2D array of the maze.
 *
 * CONSTRUCTOR METHODS
 *	Cell
 *		Creates a new cell object by taking in directional booleans and setting onPath to false.
 *
 * INSTANCE AND DATA VARIABLES
 * 	NORTH
 * 		Denoted by a zero. It represents a cell's northern location.
 *  SOUTH
 *      Denoted by a one. It represents a cell's southern location.
 *  EAST
 *     Denoted by a two. It represents a cell's eastern location.
 *  WEST
 *     Denoted by a three. It represents a cell's western location.
 *  neighbors[]
 *     An array of directional boolean values.
 *  onPath
 *      A boolean representing if a current cell is on the solution path.
 *
 * INSTANCE METHODS
 * 	getNorth
 * 		Returns a boolean value representing if you can travel north from the cell.
 *  getSouth
 * 		Returns a boolean value representing if you can travel south from the cell.
 *  getEast
 * 		Returns a boolean value representing if you can travel east from the cell.
 *  getWest
 *      Returns a boolean value representing if you can travel west from the cell.
 *  setOnPath
 *      Sets the onPath boolean value.
 *  getIsNeighbors
 *      Returns the array of directional boolean values for a cell
 */

public class Cell {

    //class data fields
    static final int NORTH = 0; // Denoted by zero. Represents a cell's northern location
    static final int SOUTH = 1; // Denoted by one. Represents a cell's southern location
    static final int EAST = 2; // Denoted by two. Represents a cell's eastern location
    static final int WEST = 3; // Denoted by three. Represents a cell's western location

    //class instance data fields
    private boolean neighbors[]; // Array of directional boolean values
    private boolean onPath; // Boolean representing if the cell is on the solution path

    //
    // Creates a new cell object by taking in directional booleans and setting onPath to false
    //
    public Cell(boolean North, boolean South, boolean East, boolean West) {
        onPath = false;
        neighbors = new boolean[4];
        neighbors[NORTH] = North;
        neighbors[SOUTH] = South;
        neighbors[EAST] = East;
        neighbors[WEST] = West;
    } //end Cell

    //
    // Returns a boolean value representing if you can travel north from the cell
    //
    public boolean getNorth() {
        return neighbors[NORTH];
    } //end getNorth

    //
    // Returns a boolean value representing if you can travel south from the cell
    //
    public boolean getSouth() {
        return neighbors[SOUTH];
    } //end getSouth

    //
    // Returns a boolean value representing if you can travel east from the cell
    //
    public boolean getEast() {
        return neighbors[EAST];
    } //end getEast

    //
    // Returns a boolean value representing if you can travel west from the cell
    //
    public boolean getWest() {
        return neighbors[WEST];
    } //end getWest

    //
    // Returns whether the cell is on the solution path
    //
    public boolean isOnPath() {
        return onPath;
    } //end isOnPath

    //
    // Sets the onPath boolean value
    //
    public void setOnPath(boolean onPath) {
        this.onPath = onPath;
    } //end setOnPath

    //
    // Returns the array of directional boolean values for a cell
    //
    public boolean[] getIsNeighbors() {
        return neighbors;
    } //end getIsNeighbors
} //end Cell