import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class inputSystem {
    private ArrayList<String> Map
    public inputSystem(){

    }
    public inputSystem(String path){
        ArrayList<String> input
    }
    //transposes the file into a 2d array in memory. Easier to process like this
    private ArrayList<String> readTxtFile(String fileName) {
        //opens the file and stores the maze in maze
        ArrayList<String> inputSpace = new ArrayList<String>();
        try {
            File myObj = new File(fileName);//init file stuff
            BufferedReader r = new BufferedReader(new FileReader(fileName));

            Scanner myReader = new Scanner(myObj);
            for (int i = 0; myReader.hasNextLine(); i++) {
                inputSpace.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            e.printStackTrace();
        }
        return (inputSpace);
    }
}
