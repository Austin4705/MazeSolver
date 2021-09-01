//Maze Solver by Austin Wu
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files


public class main {
    //C:\Dev\MazeSolver\Mazes
    public static String fileName = "daedlus2.txt";

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String filePath = dir.substring(0, dir.length()) + "\\Mazes\\" + fileName;
        System.out.println("Path: " + filePath);

        fileReader mazeFile = new fileReader(filePath, fileReader.type.txt);//read the file
        mazeFile.printMaze();//print it for debugging
        nodeGenerator NodeGenerator = new nodeGenerator(mazeFile.outputMaze());//inputting parsed data into the node generator script
        mazeFile.printMazeIn(NodeGenerator.getNodes());

    }
} 