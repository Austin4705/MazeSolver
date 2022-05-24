//Maze Solver by Austin Wu
import org.api.hyperdrive.NArray;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files



/*
Libraries and sources for referenced code used:
https://www.baeldung.com/java-dijkstra
https://github.com/adamierymenko/hyperdrive
https://github.com/junit-team/junit5
 */

public class main {
    public static final String subDir = "\\Mazes\\";
    public static final String fileNameIn = "maze.txt";
    public static final String fileNameOutFile = "mazeOut.txt";
    public static final String fileNameOutStl = "mazeTest.stl";
    public static boolean autoRead = false;
    public static boolean autoWrite = false;

    public static void main(String[] args) throws InterruptedException {
        //main cdoe and user interface to read the data form the file or user
        inputSystem input = new inputSystem();
        //used to differentiate a debug mode where maze auto-loaded from final one
        if(!autoRead){
            System.out.println("Hello, and welcome to the maze solving algorhtm. What would you want to do?");
            System.out.println("Load SelectFile:1");
            System.out.println("Load ConsolePrompt:2");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if(n == 1){
                System.out.println("Enter Path:");
                String path = sc.next();
                System.out.println("Reading from Path: " + path);
                input.readTxtFile(path);
            }
            else if(n == 2){
                input.readCMD();
            }
            else{
                throw new RuntimeException("Selection");
            }
        }
        else{
            System.out.println("Reading from Path: " + inputSystem.fullPathStr(fileNameIn));
            input.readTxtFile(fileNameIn);
        }
        System.out.println("Maze Successfully Loaded, beginning solving stage");
        //enter parsestage of code, dont need to pass around data but need to start off each bit of the algorithm
        graphGenerator generator = new graphGenerator();
        mazeSolver solver = new mazeSolver();
        System.out.println("Maze Solved");
        graphWriter writer = new graphWriter();
        //used to differentiate a debug mode where maze auto-printed from final one
        if(!autoWrite){
            System.out.println("What would you like to Output to?\n1-file, 2-cmd, 3-stl:");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if(n == 1){
                System.out.println("Output Path with suffix:");
                String path = sc.next();
                writer.writeToFile(path);
            }
            else if(n == 2){
                writer.writeToCmd();
                System.out.println();
            }
            else if(n == 3){
                System.out.println("Output Path with suffix:");
                String path = sc.next();
                writer.writeTo3dObj(path);
            }
            else{
                throw new RuntimeException("Selection");
            }

        }
        else{
            writer.writeToFile(fileNameOutFile);
            writer.writeTo3dObj(fileNameOutStl);
        }
        System.out.println("Maze Solving and or Outputting Complete. Exiting Program. Have a nice day :)");
        System.exit(0);
    }

} 