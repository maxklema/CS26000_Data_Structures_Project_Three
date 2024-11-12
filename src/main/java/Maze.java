import Exceptions.InvalidLocationException;

public class Maze {

    private boolean pathFound;
    private Cell[][] maze;
    private int widthInside;
    private int widthTotal;
    private int rows;
    private int cols;

    public Maze(int rows, int cols, String mazeRaw) {
        maze = new Cell[rows+2][cols];
        pathFound = false;
        this.rows = rows;
        this.cols = cols;
        widthInside = (cols*2)-1; //width of the maze without the borders
        widthTotal = (cols*2)+1; //width of the maze with the borders
        iterateMazeRaw(mazeRaw); //populates maze, the 2D array storing each cell
    }

    private void iterateMazeRaw(String mazeRaw) {

        //set first row in maze (border across the top)
        for (int col = 1; col <= widthInside + 2; col += 2){
            if (mazeRaw.charAt(col) == '_'){ //cannot move anywhere
                maze[0][col/2] = new Cell(false, false, false, false);
            } else if (mazeRaw.charAt(col) == ' '){ //starting point - can only move south
                maze[0][col/2] = new Cell(false, true, false, false);
            }
        }

        //set last row in maze (border across the bottom)
        int pointer = ((widthTotal+1)*(rows))-1; //points to the first cell in the bottom row
        for (int col = 1; col < widthInside + 2; col += 2){
            if (mazeRaw.charAt(pointer + col) == '_'){
                maze[rows+1][col/2] = new Cell(false, false, false, false);
            } else if (mazeRaw.charAt(pointer + col) == ' '){ //ending point - only move north
                maze[rows+1][col/2] = new Cell(true, false, false, false);
            }
        }

        //create cells for the rest of the maze
        pointer = ((widthTotal+1)*(1))-1;
        for (int row = 1; row <= rows; row++){
            for (int col = 1; col < widthInside + 2; col += 2){
                // determine directional boolean values by looking at surrounding characters
                boolean South = mazeRaw.charAt(pointer + col) != '_';
                boolean East = mazeRaw.charAt(pointer + col+1) != '|';
                boolean West = mazeRaw.charAt(pointer + col-1) != '|';
                boolean North;
                if (row != 1){ //offset by 1 less than other rows
                    North = mazeRaw.charAt(pointer + col - (widthTotal+1)) != '_';
                } else {
                    North = mazeRaw.charAt(pointer + col - (widthTotal)) != '_';
                }
                maze[row][col/2] = new Cell(North, South, East, West);
            }
            pointer += widthTotal+1; //increase the pointer to the next line
        }
    }

    //will modify later as the maze is populated
    public String asText() {
        String mazeAsText = "";

        //append the first row of the maze
        for (int col = 0; col < cols; col++){
            if (maze[0][col].getSouth()){
                mazeAsText += (pathFound ? " @" : "  ");
            } else {
                mazeAsText += " _";
            }
        }
        mazeAsText += "\n";

        //append the rest of the rows
        for (int row = 1; row <= rows; row++){
            for (int col = 0; col < cols; col++){
                //check which way the object can move in the current cell, then it knows what to print
                mazeAsText += (maze[row][col].getWest() ? " " : "|");
                if (maze[row][col].getSouth()){ //determines if cell is part of path and the surrounding borders
                    mazeAsText += (maze[row][col].isOnPath() ? "@" : " ");
                } else {
                    mazeAsText += (maze[row][col].isOnPath() ? "@" : "_");
                }
            }
             mazeAsText += (!pathFound && row == rows? "|" : "|\n");
        }

        //append to the last row
        if (pathFound){
            for (int col = 0; col < cols; col++){
                mazeAsText += (col == cols-1 && pathFound ? " @" : "  ");
            }
        }

        return mazeAsText;
    }

    public void findPath() throws InvalidLocationException {

        Set<Location> locationsSet = new Set<Location>();
        MyGenericQueue<Location> locationsQueue = new MyGenericQueue<Location>();

        Location startingLocation = new Location(0, 0, null);
        locationsQueue.enqueue(startingLocation); //add starting location to the queue
        locationsSet.enter(startingLocation); //add the starting location to the set

        Location endingLocation = new Location(rows+1, cols-1, null);

        while (!locationsQueue.isEmpty()){ //while there are still locations
            Location location = locationsQueue.dequeue();
            int locationRow = location.getRow();
            int locationCol = location.getColumn();

            if (!location.equals(endingLocation)){

                //check all four neighbors of each location
                for (int i = 0; i < 4; i++){
                    Location nextLocation = location.getLocation(i);

                    //Check if the cursor can travel in that direction and that the next location is not currently in the ste
                    if (maze[locationRow][locationCol].getIsNeighbors()[i] && !locationsSet.isElement(nextLocation)){
                        nextLocation.setPrevious(location);
                        locationsSet.enter(nextLocation);
                        locationsQueue.enqueue(nextLocation);
                    }
                }
            } else {
                pathFound = true;

                //revisit each previous node that is in the path and set the onPath variable to 'true'
                while (location.getPrevious() != null){
                    locationRow = location.getRow(); //update the location row
                    locationCol = location.getColumn(); //update the location column
                    maze[locationRow][locationCol].setOnPath(true);
                    location = location.getPrevious();
                }
            }
        }
    }

    public String solutionAsText() {
        String solutionAsText = "";
        //append the rest of the rows
        for (int row = 1; row <= rows; row++){
            for (int col = 0; col < cols; col++){
                solutionAsText += (maze[row][col].isOnPath() ? "@" : " ");
            }
            solutionAsText += "\n";
        }
        return solutionAsText;
    }

    public boolean pathFound() {
        return pathFound;
    }

    public void setPathFound(boolean pathFound) {
        this.pathFound = pathFound;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Cell[][] getMaze() {
        return maze;
    }
}