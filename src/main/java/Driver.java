import Exceptions.InvalidLocationException;

import java.io.File;
import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException, InvalidLocationException {

        //read in the maze input file
        FileIO fileIO = new FileIO(new File("src/main/resources/Mazes_Fall_2024_CS_26000-03.in"));
        int mazeNumber = 1; //keep track of which maze is being solved

        //parse through each maze and write to .out file
        while(fileIO.getScanner().hasNextLine()){
            fileIO.parseMaze();
            Maze maze = new Maze(fileIO.getRows(), fileIO.getCols(), fileIO.getMaze());
            maze.findPath();
            fileIO.writeMazeToOutput(maze, mazeNumber);
            mazeNumber++;
        }
    }
}
