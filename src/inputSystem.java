import org.api.hyperdrive.NArray;
import org.api.hyperdrive.mazeData;
import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
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

    //main constructor line for file based reading.
    public inputSystem(String path){
        ArrayList<Character> input = readTxtFile(path);
        int[] dimensions = mazeData.getInstance().data.dimensions();
        int[] sumArr = mazeData.getInstance().dimensionCapPiSum();
        int[] curLoc = new int[dimensions.length];
        int num = 0;
        //effectively create a counter system that counts to variable length holding places
        //could run with mod arithmetic or seperate counter for each array, chose first for simplicity, although much slower but still constant time
        for(int i = 0; i < mazeData.getInstance().getSum(); i++){
            for(int j = 0; j < curLoc.length; j++){// not o2. more n is very small so more like 5o max
                curLoc[j] = num % sumArr[j];
            }
            num++;
            mazeData.getInstance().data.set(curLoc, mazeData.charToInt(input.get(num)));
        }
    }

    //transposes the file into a 2d array in memory. Easier to process like this
    private ArrayList<Character> readTxtFile(String fileName) {
        //opens the file and stores the maze in maze
        ArrayList<Character> inputSpace = new ArrayList<Character>();//could optimize to o(n) instead of o(2n) later
        try {
            Scanner sc = new Scanner(new FileReader(fileName));
            int n = sc.nextInt();//number of dimensions
            if(n < 1) throw new RuntimeException("Cant have less than 1 dimension");
            int[] dimensions = new int[n];
            for(int i = 0; i < n; i++){
                dimensions[i] = sc.nextInt();
                if(dimensions[i] == 1) throw new RuntimeException("Too Small Dimension");
            }
            while(sc.hasNextLine()) {
                String fileLine = sc.nextLine();
                for(int j = 0; j < fileLine.length(); j++){
                    Character c = fileLine.charAt(j);
                    inputSpace.add(c);
                }
            }
            sc.close();
            mazeData m = mazeData.getInstance(); m.makeArray(dimensions);
            if(m.getSum() != inputSpace.size()) throw new RuntimeException("ListedMazeSize doesnt match inputted Data");
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            e.printStackTrace();
        }
        return (inputSpace);
    }
}
