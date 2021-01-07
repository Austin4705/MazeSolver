//Maze Solver by Austin Wu
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class main {
    public static String fileName = "C:\\Users\\austi\\Files\\Intellij\\MazeSolver\\Mazes\\daedlus2.txt";

    public static void main(String[] args) {
        fileReader mazeFile = new fileReader(fileName, fileReader.type.txt);//read the file
        mazeFile.printMaze();//print it for debugging
        nodeGenerator NodeGenerator = new nodeGenerator(mazeFile.outputMaze());//inputting parsed data into the node generator script
    }
} 