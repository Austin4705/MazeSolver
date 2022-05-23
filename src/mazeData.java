import org.api.hyperdrive.NArrayInt;


//singleton implementation of class in order to function as object container. holds all data for the maze inclding the n dimensional array, solved path, and adjancy graph
public class mazeData {
//region singletonConstructor
    private static mazeData instance = null;
    private mazeData() {

    }

    public static mazeData getInstance() {
        if (instance == null) {
            instance = new mazeData();
        }
        return instance;
    }
//endregion
//region member
    public NArrayInt data = null;
    private int sum = 1;
//endregion

//region func
    //intialization function in singleton class to actually make the n dimesnioal data arraay, wrapped around hyperdrive library
    public void makeArray(int[] dimensions){
        data = new NArrayInt(dimensions);
        for(int p : data.dimensions())
            sum *= p;//convert to big decimal later
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
    public int getSum(){
        return sum;
    }
//endregion
}
