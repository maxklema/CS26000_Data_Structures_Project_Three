import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class FileIO {

    Scanner scanner;
    int rows;
    int cols;
    String maze;
    File mazeFile;
    FileWriter fileWriter;

    public FileIO(File fileToRead) throws IOException {
        rows = 0;
        cols = 0;
        maze = "";
        mazeFile = fileToRead;
        scanner = new Scanner(mazeFile);
        fileWriter = new FileWriter("src/main/resources/MazeSolutions.out");
    }

    public void parseMaze(){
        maze = "";
        if (mazeFile != null){
            //parse number the number of columns and rows in the maze
            rows = Integer.parseInt(scanner.nextLine());
            cols = Integer.parseInt(scanner.nextLine());

            //for each row, append the maze
            for (int row = 0; row <= rows; row++){
                if (row != rows){
                    maze += scanner.nextLine() + "\n";
                } else {
                    maze += scanner.nextLine();
                }
            }
        } else {
            throw new RuntimeException("There was a problem reading the file.");
        }
    }

    public void writeMazeToOutput(Maze maze, int mazeNumber) throws IOException {
        fileWriter.write(String.format("Maze %d:\n", mazeNumber));
        fileWriter.write(String.format("%s\n", maze.asText()));
        fileWriter.write(String.format("Path exists on Maze %d: %b\n", mazeNumber, maze.pathFound()));
        if (maze.pathFound()){
            fileWriter.write(String.format("%s\n\n", maze.solutionAsText()));
        } else {
            fileWriter.write("\n");
        }
        fileWriter.flush();
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public String getMaze(){
        return maze;
    }

    public Scanner getScanner() {
        return scanner;
    }

}
