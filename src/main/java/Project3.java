/*
 * NAME: Maxwell Klema
 * PROJECT: Project Three
 * COURSE: CS26000
 * INSTRUCTOR: KIM, B.
 * LAB TIME: N/A
 * DUE DATE: 11/14/24
 *
 * DESCRIPTION
 * 	Project3 includes the main driver that iterates over the maze input file, solves each maze, and writes it to a .out file
 *
 * INSTANCE VARIABLES
 * 	fileIO
 * 		The primary fileIO object that is used to perform read and write operations
 */

import Exceptions.InvalidLocationException;
import java.io.File;
import java.io.IOException;

public class Project3 {

    public static void main(String[] args) throws IOException, InvalidLocationException {

        //read in the maze input file
        FileIO fileIO = new FileIO(new File("src/main/resources/Mazes_Fall_2024_CS_26000-03.in")); //The primary FileIO object for read and write operations
        int mazeNumber = 1; //keep track of which maze is being solved

        //parse through each maze and write to .out file
        while(fileIO.getScanner().hasNextLine()){
            fileIO.parseMaze();
            Maze maze = new Maze(fileIO.getRows(), fileIO.getCols(), fileIO.getMaze());
            maze.findPath();
            fileIO.writeMazeToOutput(maze, mazeNumber);
            mazeNumber++;
        }

        System.out.println("\nMaze Solution written to /src/main/resources/MazeSolutions.out"); //write to console when finished
    } //end main
} //end Project3