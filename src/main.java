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
    public static final String fileName = "maze.txt";
    public static final String filePath =  System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()) + "\\Mazes\\" + fileName;

    public static void main(String[] args) {
        //main cdoe and user interface to read the data form the file or user
        inputSystem i;
        if(filePath == null){
            System.out.println("Hello, and welcome to the maze solving algorhtm. What would you want to do?");
            System.out.println("Load SelectFile:1");
            System.out.println("Load ConsolePrompt:2");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if(n == 1){
                System.out.println("Enter Path:");
                String s = sc.nextLine();
                System.out.println("Reading from Path: " + s);
                i = new inputSystem(s);
            }
            else if(n == 2){
                System.out.println("Not Functional Yet");
                i = new inputSystem();
            }
            else{
                throw new RuntimeException("Selection");
            }
        }
        else{
            System.out.println("Reading from Path: " + filePath);
            i = new inputSystem(filePath);
        }
        System.out.println("Maze Successfully Loaded");
        //enter parsestage of code, dont need to pass around data but need to start off each bit of the algorithm
        graphGenerator g = new graphGenerator();
    }

} 