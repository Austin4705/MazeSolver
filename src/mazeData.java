import org.api.hyperdrive.NArrayInt;

import java.util.HashMap;
import java.util.HashSet;


//singleton implementation of class in order to function as object container. holds all data for the maze inclding the n dimensional array, solved path, and adjancy graph
public class mazeData {
//region singletonConstructor
    private static mazeData instance = null;
    private mazeData() {}
    //public singleton implementation function
    public static mazeData getInstance() {
        if (instance == null) {
            instance = new mazeData();
        }
        return instance;
    }
//endregion
//region member
    //multidimensional arrary in hyperdrive library package
    public NArrayInt data = null;
    //adj list stored in the format of idx to node
    public HashMap<Integer, node> adjList = new HashMap<Integer, node>();
//endregion

//region func
    //intialization function in singleton class to actually make the n dimesnioal data arraay, wrapped around hyperdrive library
    public void makeArray(int[] dimensions){
        data = new NArrayInt(dimensions);
    }

    //function in order to return an array based on dimensions in order to properly increment loop through
    public int[] dimensionCapPiSum(){
        int[] dimArr = data.dimensions();
        int[] sumArr = new int[dimArr.length]; sumArr[0] = dimArr[0];
        for(int i = 1; i < dimArr.length; i++){
            sumArr[i] = dimArr[i] * sumArr[i-1];
        }
        return sumArr;
    }

    //functinalized the loopinng through variuable nd arrays
    public int[] loopDimensions(int num){
        int[] sumArr = dimensionCapPiSum();
        int[] curLoc = new int[sumArr.length];
        curLoc[0] = num % sumArr[0];
        for(int j = 1; j < curLoc.length; j++){// not o2. more n is very small so more like 5o max
            curLoc[j] = (num % sumArr[j]) / sumArr[j-1];
        }
        return curLoc;
    }
    //helper functions, both used to pass between data formats
    public static int charToInt(char c){
        if(c == 'O') return 0;
        if(c == 'X') return 1;
        if(c == 'S') return 2;
        if(c == 'E') return 3;
        else return -1;
    }
    //helper functions, both used to pass between data formats
    public static char intToChar(int i){
        if(i == 0) return 'O';
        if(i == 1) return 'X';
        if(i == 2) return 'S';
        if(i == 3) return 'E';
        else return 'O';
    }
    //getters
    //setters
//endregion
}
