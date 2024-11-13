/*
 * NAME: Maxwell Klema
 * PROJECT: Project Three
 * COURSE: CS26000
 * INSTRUCTOR: KIM, B.
 * LAB TIME: N/A
 * DUE DATE: 11/14/24
 *
 * DESCRIPTION
 *  A maze represents a singular maze with n rows and m columns. It represents a maze with a 2D array of Cells.
 *
 * CONSTRUCTOR METHODS
 *	Maze
 *	    Creates a new Maze object by taking in the number of rows and columns and a String representation of the maze. It also calculates the width inside the maze.
 *
 * INSTANCE AND DATA VARIABLES
 * 	pathFound
 * 		A boolean object represented whether a path has been found in the current maze object.
 *  maze
 *      a 2D Cell array representing the maze.
 *  widthInside
 *      An integer representing the number of columns inside the maze, excluding the borders.
 *  widthTotal
 *      An integer representing the number of columns in the maze, including the borders.
 *  rows
 *      An integer representing the number of rows in the maze.
 *  cols
 *      An integer representing the number of cols in the maze.
 *
 * INSTANCE METHODS
 * 	IterateMazeRaw
 * 		Takes in a string representation of a maze and creates a 2D array of Cells to represent the maze.
 *  asText
 * 	    Returns a text representation of the maze without the first two lines in the input file defining the number of rows and columns.
 *  findPath
 * 		Takes the current maze and uses the breadth-first algorithm to iterate over the maze and find a path.
 *  solutionAsText
 *      Returns just the path solution of the maze using @ symbols, if a path exists.
 *  pathFound
 *      Returns a boolean value indicating if a path was found to solve the maze.
 *  setPathFound
 *      Sets the pathFound status of the maze.
 *  getRows
 *      Returns the number of rows in the maze.
 *  getCols
 *      Returns the number of columns in the maze.
 *  getMaze
 *      Returns a 2D array of cells that represents the maze.
 */

import Exceptions.InvalidLocationException;

public class Maze {

    private boolean pathFound; // A boolean indicating whether a path has been found in the current maze object
    private Cell[][] maze; // A 2D array of Cell objects representing the maze structure
    private int widthInside; // The width of the maze excluding borders
    private int widthTotal; // The total width of the maze including borders
    private int rows; // The number of rows in the maze
    private int cols; // The number of columns in the maze

    //
    // Constructor that initializes the maze with the number of rows and columns
    // and parses the maze from a raw string.
    //
    public Maze(int rows, int cols, String mazeRaw) {
        maze = new Cell[rows+2][cols]; // Account for borders (2 extra rows for top and bottom borders)
        pathFound = false;
        this.rows = rows;
        this.cols = cols;
        widthInside = (cols*2)-1; // Width of the maze excluding borders
        widthTotal = (cols*2)+1; // Total width of the maze including borders
        iterateMazeRaw(mazeRaw); // Parse the raw string and populate the maze
    } //end Maze

    //
    // Parses the raw maze string and populates the maze with Cell objects.
    // This method considers the top and bottom borders, as well as the interior walls.
    //
    private void iterateMazeRaw(String mazeRaw) {

        // Set first row in maze (border across the top)
        for (int col = 1; col <= widthInside + 2; col += 2){
            if (mazeRaw.charAt(col) == '_'){ // Cannot move anywhere
                maze[0][col/2] = new Cell(false, false, false, false);
            } else if (mazeRaw.charAt(col) == ' '){ // Starting point - can only move south
                maze[0][col/2] = new Cell(false, true, false, false);
            }
        }

        // Set last row in maze (border across the bottom)
        int pointer = ((widthTotal+1)*(rows))-1; // Points to the first cell in the bottom row
        for (int col = 1; col < widthInside + 2; col += 2){
            if (mazeRaw.charAt(pointer + col) == '_'){
                maze[rows+1][col/2] = new Cell(false, false, false, false);
            } else if (mazeRaw.charAt(pointer + col) == ' '){ // Ending point - only move north
                maze[rows+1][col/2] = new Cell(true, false, false, false);
            }
        }

        // Create cells for the rest of the maze (interior)
        pointer = ((widthTotal+1)*(1))-1;
        for (int row = 1; row <= rows; row++){
            for (int col = 1; col < widthInside + 2; col += 2){
                // Determine directional boolean values based on surrounding characters
                boolean South = mazeRaw.charAt(pointer + col) != '_';
                boolean East = mazeRaw.charAt(pointer + col+1) != '|';
                boolean West = mazeRaw.charAt(pointer + col-1) != '|';
                boolean North;
                if (row != 1){ // Offset by 1 less than other rows
                    North = mazeRaw.charAt(pointer + col - (widthTotal+1)) != '_';
                } else {
                    North = mazeRaw.charAt(pointer + col - (widthTotal)) != '_';
                }
                maze[row][col/2] = new Cell(North, South, East, West);
            }
            pointer += widthTotal+1; // Move to the next line in the maze string
        }
    } //end iterateMazeRaw

    //
    // Returns a string representation of the maze without the first two lines of the input file.
    // This method shows the maze structure including walls and paths.
    //
    public String asText() {
        String mazeAsText = "";

        // Append the first row (top border)
        for (int col = 0; col < cols; col++){
            if (maze[0][col].getSouth()){
                mazeAsText += (pathFound ? " @" : "  ");
            } else {
                mazeAsText += " _";
            }
        }
        mazeAsText += "\n";

        // Append the rest of the rows
        for (int row = 1; row <= rows; row++){
            for (int col = 0; col < cols; col++){
                // Determine whether the object can move in the current cell and what to print
                mazeAsText += (maze[row][col].getWest() ? " " : "|");
                if (maze[row][col].getSouth()){ // Check if cell is part of the path or has borders
                    mazeAsText += (maze[row][col].isOnPath() ? "@" : " ");
                } else {
                    mazeAsText += (maze[row][col].isOnPath() ? "@" : "_");
                }
            }
            mazeAsText += (!pathFound && row == rows? "|" : "|\n");
        }

        // Append to the last row
        if (pathFound){
            for (int col = 0; col < cols; col++){
                mazeAsText += (col == cols-1 && pathFound ? " @" : "  ");
            }
        }

        return mazeAsText;
    } //end asText

    //
    // Solves the maze using breadth-first search algorithm.
    // It updates the maze to mark the path if found.
    //
    public void findPath() throws InvalidLocationException {
        Set<Location> locationsSet = new Set<Location>();
        MyGenericQueue<Location> locationsQueue = new MyGenericQueue<Location>();

        Location startingLocation = new Location(0, 0, null);
        locationsQueue.enqueue(startingLocation); // Add starting location to the queue
        locationsSet.enter(startingLocation); // Add the starting location to the set

        Location endingLocation = new Location(rows+1, cols-1, null);

        while (!locationsQueue.isEmpty()){ // While there are still locations to check
            Location location = locationsQueue.dequeue();
            int locationRow = location.getRow();
            int locationCol = location.getColumn();

            if (!location.equals(endingLocation)){

                // Check all four neighbors of the current location
                for (int i = 0; i < 4; i++){
                    Location nextLocation = location.getLocation(i);

                    // Check if the direction is valid and the next location is not visited
                    if (maze[locationRow][locationCol].getIsNeighbors()[i] && !locationsSet.isElement(nextLocation)){
                        nextLocation.setPrevious(location);
                        locationsSet.enter(nextLocation);
                        locationsQueue.enqueue(nextLocation);
                    }
                }
            } else {
                setPathFound(true);

                // Trace the path from the end to the start and mark the path
                while (location.getPrevious() != null){
                    locationRow = location.getRow(); // Update the location row
                    locationCol = location.getColumn(); // Update the location column
                    maze[locationRow][locationCol].setOnPath(true);
                    location = location.getPrevious();
                }
            }
        }
    } //end findPath

    //
    // Returns a string representing the maze with the path marked using '@'
    //
    public String solutionAsText() {
        String solutionAsText = "";
        for (int row = 1; row <= rows; row++){
            for (int col = 0; col < cols; col++){
                solutionAsText += (maze[row][col].isOnPath() ? "@" : " ");
            }
            solutionAsText += "\n";
        }
        return solutionAsText;
    } //end solutionAsText

    //
    // Getter methods for maze properties
    //
    public boolean pathFound() {
        return pathFound;
    } //end pathFound

    //
    //  Sets the pathFound status of the maze.
    //
    public void setPathFound(boolean pathFound) {
        this.pathFound = pathFound;
    } //end setPathFound

    //
    // Returns the number of rows in the maze.
    //
    public int getRows() {
        return rows;
    } //end getRows

    //
    // Returns the number of columns in the maze.
    //
    public int getCols() {
        return cols;
    } //end getCols

    //
    //  Returns a 2D array of cells that represents the maze.
    //
    public Cell[][] getMaze() {
        return maze;
    } //end getMaze
} // end Maze
