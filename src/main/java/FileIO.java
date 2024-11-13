/*
 * NAME: Maxwell Klema
 * PROJECT: Project Three
 * COURSE: CS26000
 * INSTRUCTOR: KIM, B.
 * LAB TIME: N/A
 * DUE DATE: 11/14/24
 *
 * DESCRIPTION
 * 	FileIO handles input and output operations with parsing and write methods
 *
 * CONSTRUCTOR METHODS
 *	FileIO
 *		Creates a FileIO object which takes in a .in maze file to later parse. It also creates a scanner and fileWriter.
 *
 * INSTANCE VARIABLES
 * 	scanner
 * 		A scanner object to parse through .in maze files.
 *  rows
 *      An integer to store the number of rows in the maze.
 *  cols
 *     An integer to store the number of columns in the maze.
 *  maze
 *     A string object to temporarily store the maze to create a new Maze Object.
 *  mazeFile
 *     The file object that contains the input mazes.
 *  fileWriter
 *      The fileWriter object to write solution mazes to a .out file.
 *
 * INSTANCE METHODS
 * 	parseMaze
 * 		Parses the number of rows and column in a particular maze and parses the maze itself.
 * writeMazeToOutput
 * 		takes in a solution maze object and writes it to a .out file
 * getRows
 * 		returns the number of rows in the current maze.
 * getCols
 *      returns the number of cols in the current maze.
 * getMaze
 *      returns the string representation of the current maze.
 * getScanner
 *      returns the scanner object.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {

    Scanner scanner; // Scanner to read the maze file
    int rows; // Number of rows in the maze
    int cols; // Number of columns in the maze
    String maze; // String representation of the maze
    File mazeFile; // File object representing the maze input file
    FileWriter fileWriter; // FileWriter to write maze solutions to output file

    //
    // Creates a FileIO object which initializes scanner, fileWriter, and reads maze file
    //
    public FileIO(File fileToRead) throws IOException {
        rows = 0;
        cols = 0;
        maze = "";
        mazeFile = fileToRead;
        scanner = new Scanner(mazeFile);
        fileWriter = new FileWriter("src/main/resources/MazeSolutions.out");
    } //end FileIO

    //
    // Parses the maze file, setting the number of rows and columns and the maze layout
    //
    public void parseMaze() {
        maze = "";
        if (mazeFile != null) {
            //parse number of columns and rows in the maze
            rows = Integer.parseInt(scanner.nextLine());
            cols = Integer.parseInt(scanner.nextLine());

            //for each row, append the maze
            for (int row = 0; row <= rows; row++) {
                if (row != rows) {
                    maze += scanner.nextLine() + "\n";
                } else {
                    maze += scanner.nextLine();
                }
            }
        } else {
            throw new RuntimeException("There was a problem reading the file.");
        }
    } //end parseMaze

    //
    // Writes the maze solution to output file, including path status and solution
    //
    public void writeMazeToOutput(Maze maze, int mazeNumber) throws IOException {
        fileWriter.write(String.format("Maze %d:\n", mazeNumber));
        fileWriter.write(String.format("%s\n", maze.asText()));
        fileWriter.write(String.format("Path exists on Maze %d: %b\n", mazeNumber, maze.pathFound()));
        if (maze.pathFound()) {
            fileWriter.write(String.format("%s\n\n", maze.solutionAsText()));
        } else {
            fileWriter.write("\n");
        }
        fileWriter.flush();
    } //end writeMazeToOutput

    //
    // Returns the number of rows in the maze
    //
    public int getRows() {
        return rows;
    } //end getRows

    //
    // Returns the number of columns in the maze
    //
    public int getCols() {
        return cols;
    } //end getCols

    //
    // Returns the string representation of the maze
    //
    public String getMaze() {
        return maze;
    } //end getMaze

    //
    // Returns the scanner object used to read the maze file
    //
    public Scanner getScanner() {
        return scanner;
    } //end getScanner
} //end FileIO

