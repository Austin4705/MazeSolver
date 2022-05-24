import org.api.hyperdrive.NArrayInt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/*singleton implementation of class in order to function as object container.
*holds all data for the maze including the n dimensional array, solved path, and adjacency graph
*acts as a sort of bridge in the middle between all the sub parts of algorithm, holding the data
**/
public class mazeData {
//region singletonConstructor
    //normal singleton implementation
    private static mazeData instance = null;
    /**defualt private constructor*/
    private mazeData() {}
    /**public singleton implementation function*/
    public static mazeData getInstance() {
        if (instance == null) {
            instance = new mazeData();
        }
        return instance;
    }
//endregion
//region member
    //multidimensional array in hyper-drive library package
    public NArrayInt data = null;
    //adj list stored in the format of idx to node as well as start and end nodes,
    //remember each node contains a list of all connected nodes, makes it adj list
    public HashMap<Integer, node> adjList = new HashMap<Integer, node>();
    public HashMap<Integer, node> adjListS = new HashMap<Integer, node>();
    public HashMap<Integer, node> adjListE = new HashMap<Integer, node>();
    //list in order of the path used for each pass of start to end for each combo after being solved
    public ArrayList<List<node>> listDir = new ArrayList<List<node>>();
//endregion

//region func
    /**initialization function in singleton class to actually make the n dimensional data array,
     * wrapped around hyperdrive library*/
    public void makeArray(int[] dimensions){
        data = new NArrayInt(dimensions);
    }

    /**function in order to return an array based on dimensions in order to properly increment loop through
     * essentially each element is the multiplicative sum of the cur value times everything before it,
     * like a prefix multiple sum. The name is a bad math reference*/
    public int[] dimensionCapPiSum(){
        int[] dimArr = data.dimensions();
        int[] sumArr = new int[dimArr.length]; sumArr[0] = dimArr[0];
        for(int i = 1; i < dimArr.length; i++){
            sumArr[i] = dimArr[i] * sumArr[i-1];
        }
        return sumArr;
    }

    /**
     * functionality the looping through variable nd arrays. Time complexity o(dimension).
     * Uses nice properties of modular arithmetic
     * @param num number idx to loop through
     * */
    public int[] loopDimensions(int num){
        int[] sumArr = dimensionCapPiSum();
        int[] curLoc = new int[sumArr.length];
        curLoc[0] = num % sumArr[0];
        for(int j = 1; j < curLoc.length; j++){// not o2. more n is very small so more like 5o max
            curLoc[j] = (num % sumArr[j]) / sumArr[j-1];
        }
        return curLoc;
    }

    /**returns list of all neighboring nodes to the current node
     * @param cur current dimensions to look around at
     * */
    public ArrayList<int[]> neighborNodes(int[] cur){
        //loop through all spots plus or minus and add it to the list if it doesnt overbound
        ArrayList<int[]> retArr = new ArrayList<int[]>();
        for(int i = 0; i < data.dimensions().length;  i++){
            int[] tmpPlus = cur.clone(); tmpPlus[i] = tmpPlus[i]+1;
            if(!(tmpPlus[i] >= data.dimensions()[i]))
                retArr.add(tmpPlus);
            int[] tmpMinus = cur.clone(); tmpMinus[i] = tmpMinus[i]-1;
            if(!(tmpMinus[i] < 0))
                retArr.add(tmpMinus);
        }
        return retArr;
    }

    /**helper functions, both used to pass between data formats
     * @param c char to help*/
    public static int charToInt(char c){
        if(c == 'O') return 0;
        if(c == 'X') return 1;
        if(c == 'S') return 2;
        if(c == 'E') return 3;
        if(c == 'P') return 4;
        else return -1;
    }
    /**helper functions, both used to pass between data formats
     * @param i int to help out convert*/
    public static char intToChar(int i){
        if(i == 0) return 'O';
        if(i == 1) return 'X';
        if(i == 2) return 'S';
        if(i == 3) return 'E';
        if(i == 4) return 'P';
        else return 'O';
    }
    //getters
    //setters
//endregion
}
