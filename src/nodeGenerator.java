import java.util.ArrayList;
import java.io.*;

//turns the
public class nodeGenerator {

    private node startNode = null;
    private ArrayList<ArrayList<Boolean>> inputMaze;
    private String[][] nodes;
    ArrayList<node> nodeList;

    public ArrayList<ArrayList<String>> getNodes(){
        return nodes;
    }

    public nodeGenerator(ArrayList<ArrayList<Boolean>> _inputMaze){
        inputMaze = _inputMaze;
        startNode = startNodes(inputMaze.get(0));
        nodes = new String[inputMaze.size()][inputMaze.size()];
        searchNode();
        printNodes();
    }

    //finds the starting point for the tree
    private node startNodes(ArrayList<Boolean> inputLine){
        for(int i = 0; i < inputLine.size(); i++) {
            if (!inputLine.get(i)) {
                return(new node(i, 0));
            }
        }
        System.out.println("No Start Node Found");
        return(new node(0, 0));
    }

    //Search node
    private void searchNode(){
        nodeList = new ArrayList<node>();

        for(int i = 0; i < inputMaze.size(); i++){
            for (int j = 0; j < inputMaze.get(i).size(); j++){
                //For each spot
                //If it is a maze part
                boolean isNode = false;

                if(!inputMaze.get(i).get(j)){
                    //Check if there is an intersection or corner piece
                    boolean lC = false, rC = false, uC = false, dC = false;
                    if(i + 1 <= inputMaze.size()-1 ){
                        if(!inputMaze.get(i+1).get(j)){dC = true;}
                    }
                    if(i- 1 >= 0 ){
                        if(!inputMaze.get(i-1).get(j)){uC = true;}
                    }
                    if(j + 1 <= inputMaze.get(i).size() - 1){
                        if(!inputMaze.get(i).get(j+1)){rC = true;}
                    }
                    if(j- 1 >= 0 ){
                        if(!inputMaze.get(i).get(j-1)){lC = true;}
                    }
                    //If there is a corner of any type
                    if( (lC || rC) && (uC || dC)){
                        isNode = true;
                        nodeList.add(new node(i, j));
                    }
                }

                if (isNode){
                    nodes.get(i).add("X");
                }
                else{
                    nodes.get(i).add("0");
                }

            }
        }
    }
    
    public void printNodes(){
        for (node x: nodeList) {
            System.out.println("X: " + x.x + ", Y: " + x.y);
        }
    }

}
