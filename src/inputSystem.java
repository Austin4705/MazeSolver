import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/*The class for handling complicated maze inputs into the program*/
public class inputSystem {
    /*default constructor*/
    public inputSystem(){}

    /**
    * if the user wants to input from text file, reads from it
    * @param fileName name of the file to read from
    */
    public void readTxtFile(String fileName) {
        //discovers actual file path
        fileName = inputSystem.fullPathStr(fileName);
        //opens the file and stores the maze in maze
        //transposes the file into a 2d array in memory. Easier to process like this
        ArrayList<Character> inputSpace = new ArrayList<Character>();//could optimize to o(n) instead of o(2n) later
        try {
            //opens file scanner. Reads the num of dimensions, gathers a array from it
            Scanner sc = new Scanner(new FileReader(fileName));
            int n = sc.nextInt();//number of dimensions
            if(n < 1) throw new RuntimeException("Cant have less than 1 dimension");
            int[] dimensions = new int[n];
            for(int i = 0; i < n; i++){
                dimensions[i] = sc.nextInt();
                //cant have 0sz dim
                if(dimensions[i] == 1) throw new RuntimeException("Too Small Dimension");
            }
            //this reads the file assuming the file has exactly right amount of characters
            while(sc.hasNextLine()) {
                String fileLine = sc.nextLine();
                for(int j = 0; j < fileLine.length(); j++){
                    Character c = fileLine.charAt(j);
                    inputSpace.add(c);
                }
            }
            //close file
            sc.close();
            //create and write to mazedata
            mazeData m = mazeData.getInstance(); m.makeArray(dimensions);
            if(m.data.size() != inputSpace.size()) throw new RuntimeException("ListedMazeSize doesnt match inputted Data");
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            e.printStackTrace();
        }
        //shared process func for all input type
        processAfterInput(inputSpace);
    }

    /**Reads the cmd
     *marginally different from the file reader one, mostly to handle diff input space. Could combine but dont want to.
 *  */
    public void readCMD(){
        ArrayList<Character> inputSpace = new ArrayList<Character>();//could optimize to o(n) instead of o(2n) later
        try {
            //opens scanner, reads dim number form cmd, then the dim sz, and then the array from it
            Scanner sc = new Scanner(System.in);
            System.out.println("Type Number of Dimensions:");
            int n = sc.nextInt();//number of dimensions
            if(n < 1) throw new RuntimeException("Cant have less than 1 dimension");
            int[] dimensions = new int[n];
            System.out.println("Type Dimensions:");
            for(int i = 0; i < n; i++){
                dimensions[i] = sc.nextInt();
                if(dimensions[i] < 1) throw new RuntimeException("Too Small Dimension");
            }
            System.out.println("Print The Data. Goes in Incrementing Order or dimensions mod the space");
            //input style has to only read enough stuff becuase its a cmd
            mazeData m = mazeData.getInstance(); m.makeArray(dimensions);
            for(int i = 0; i < mazeData.getInstance().data.size(); i++){
                String s = sc.next();
                inputSpace.add(s.charAt(0));
                for(int j = 1; j < s.length() && i < mazeData.getInstance().data.size(); j++){
                    inputSpace.add(s.charAt(j));
                    i++;
                }
            }
            if(m.data.size() != inputSpace.size()) throw new RuntimeException("ListedMazeSize doesnt match inputted Data");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //shared process func for all input type
        processAfterInput(inputSpace);
    }

    /**shared function to parse into the nd array
     * @param inputSpace
     * the list of characters read form diff mediums
     * */
    private void processAfterInput(ArrayList<Character> inputSpace){
        //effectively create a counter system that counts to variable length holding places
        //could run with mod arithmetic or separate counter for each array, chose first for simplicity, although much slower but still constant time
        for(int i = 0; i < mazeData.getInstance().data.size(); i++){
            int[] curLoc = mazeData.getInstance().loopDimensions(i);
            mazeData.getInstance().data.set(curLoc, mazeData.charToInt(inputSpace.get(i)));
        }
    }

    /**helper function to get the path str auto generated so user has to only type small amount
     * @param subPath
     * the file name
     * */
    public static String fullPathStr(String subPath){
        return System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()) + main.subDir + subPath;
    }
}
