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
 */

public class main {

    /*This section was for testing but for convince stayed in. It provides the default
    * maze file writing location as well as the default file names if auto in out is enabled */
    public static final String subDir = "\\Mazes\\";
    public static final String fileNameIn = "maze.txt";
    public static final String fileNameOutFile = "mazeOut.txt";
    public static final String fileNameOutStl = "mazeTest.stl";
    /*booleans to determine auto in out*/
    public static boolean autoRead = false;
    public static boolean autoWrite = false;


    /*The main loop of the program*/
    public static void main(String[] args) throws InterruptedException {
        //main cdoe and user interface to read the data form the file or user
        inputSystem input = new inputSystem();
        //used to differentiate a debug mode where maze auto-loaded from final one
        if(!autoRead){
            //gets the used to pick the number of what to do, has exception for wrong one
            System.out.println("Hello, and welcome to the maze solving algorhtm. What would you want to do?");
            System.out.println("Load SelectFile:1");
            System.out.println("Load ConsolePrompt:2");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if(n == 1){
                //read from path selection; type path
                System.out.println("Enter Path:");
                String path = sc.next();
                System.out.println("Reading from Path: " + path);
                input.readTxtFile(path);
            }
            else if(n == 2){
                //read from cmd selection
                input.readCMD();
            }
            else{
                throw new RuntimeException("Selection");
            }
        }
        else{
            //this is where a auto read would happen
            System.out.println("Reading from Path: " + inputSystem.fullPathStr(fileNameIn));
            input.readTxtFile(fileNameIn);
        }
        System.out.println("Maze Successfully Loaded, beginning solving stage");
        //enter parse-stage of code, dont need to pass around data but need to start off each bit of the algorithm
        graphGenerator generator = new graphGenerator();//generates the mathematical graph
        mazeSolver solver = new mazeSolver();//solves the maze with dijkstra
        System.out.println("Maze Solved");
        graphWriter writer = new graphWriter();//object to output the code
        //used to differentiate a debug mode where maze auto-printed from final one
        if(!autoWrite){
            //figures out what to write, cmd file or stl
            System.out.println("What would you like to Output to?\n1-file, 2-cmd, 3-stl:");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if(n == 1){
                //ouptut for regular file
                System.out.println("Output Path with suffix:");
                String path = sc.next();
                writer.writeToFile(path);
            }
            else if(n == 2){
                //choose to write to cmd
                writer.writeToCmd();
                System.out.println();
            }
            else if(n == 3){
                //write to stl object after user types what parts of the maze to be included in the object
                System.out.println("Output Path with suffix:");
                String path = sc.next();
                System.out.println("Type 1-Yes, 2-No in order for:\n maze, path, start, end, whiteSpace if you want any of them included?");
                int a = sc.nextInt();
                boolean mazeInc = (a == 1);
                int b = sc.nextInt();
                boolean pathInc = (b == 1);
                int c = sc.nextInt();
                boolean startInc = (c == 1);
                int d = sc.nextInt();
                boolean endInc = (d == 1);
                int e = sc.nextInt();
                boolean whiteInc = (e == 1);
                writer.writeTo3dObj(path, mazeInc, pathInc, startInc, endInc, whiteInc);
            }
            else{
                throw new RuntimeException("Selection");
            }

        }
        else{
            //auto run portion
            writer.writeToFile(fileNameOutFile);
            writer.writeTo3dObj(fileNameOutStl, true, false, false ,false, false);
        }
        //exiting scheme
        System.out.println("Maze Solving and or Outputting Complete. Exiting Program. Have a nice day :)");
        System.exit(0);
    }

} 