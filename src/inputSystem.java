import org.api.hyperdrive.NArray;
import org.api.hyperdrive.mazeData;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class inputSystem {
    private ArrayList<String> Map;
    public inputSystem(){

    }
    public inputSystem(String path){
        ArrayList<String> input;
    }
    //transposes the file into a 2d array in memory. Easier to process like this
    private ArrayList<String> readTxtFile(String fileName) {
        //opens the file and stores the maze in maze
        ArrayList<String> inputSpace = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(new FileReader(fileName));
            int n = sc.nextInt();//number of dimensions
            int[] dimensions = new int[n];
            for(int i = 0; i < n; i++){
                dimensions[i] = sc.nextInt();
            }
            mazeData m = mazeData.getInstance(); m.makeArray(dimensions);
            while(sc.hasNextLine()) {
                String fileLine = sc.nextLine();
                Reader reader = new InputStreamReader(new ByteArrayInputStream(fileLine.getBytes()));
//                Reader reader = new InputStreamReader(fileLine);
                while(reader.ready()){
                    Character c = Character(reader.read());
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (inputSpace);
    }
}
