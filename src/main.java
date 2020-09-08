//Maze Solver by Austin Wu
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class main {
    public static String fileName = "C:\\Users\\austi\\Files\\Intellij\\MazeSolver\\Mazes\\daedlus2.txt";

    public static void main(String[] args) {
//        System.out.print("Maze Solver Text(0), Image(1): ");
//        Scanner inputTerminal = new Scanner(System.in);
//        int type = inputTerminal.nextInt();
        fileReader mazeFile = new fileReader(fileName, 0);
        mazeFile.printMaze();
    }
}