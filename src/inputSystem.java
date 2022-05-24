import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class inputSystem {
    public inputSystem(){}

    public void readTxtFile(String fileName) {
        fileName = inputSystem.fullPathStr(fileName);
        //opens the file and stores the maze in maze
        //transposes the file into a 2d array in memory. Easier to process like this
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
            if(m.data.size() != inputSpace.size()) throw new RuntimeException("ListedMazeSize doesnt match inputted Data");
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            e.printStackTrace();
        }
        processAfterInput(inputSpace);
    }


    //marginally different from the file reader one, mostly to handle diff input space. Could combine but dont want to.
    public void readCMD(){
        ArrayList<Character> inputSpace = new ArrayList<Character>();//could optimize to o(n) instead of o(2n) later
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Type Number of Dimensions:");
            int n = sc.nextInt();//number of dimensions
            if(n < 1) throw new RuntimeException("Cant have less than 1 dimension");
            int[] dimensions = new int[n];
            System.out.println("Type Dimensions:");
            for(int i = 0; i < n; i++){
                dimensions[i] = sc.nextInt();
                if(dimensions[i] == 1) throw new RuntimeException("Too Small Dimension");
            }
            mazeData m = mazeData.getInstance(); m.makeArray(dimensions);
            for(int i = 0; i < mazeData.getInstance().data.size(); i++){
                String s = sc.nextLine();
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
        processAfterInput(inputSpace);
    }

    private void processAfterInput(ArrayList<Character> inputSpace){
        //effectively create a counter system that counts to variable length holding places
        //could run with mod arithmetic or separate counter for each array, chose first for simplicity, although much slower but still constant time
        for(int i = 0; i < mazeData.getInstance().data.size(); i++){
            int[] curLoc = mazeData.getInstance().loopDimensions(i);
            mazeData.getInstance().data.set(curLoc, mazeData.charToInt(inputSpace.get(i)));
        }
    }

    public static String fullPathStr(String subPath){
        return System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length()) + main.subDir + subPath;
    }
}
