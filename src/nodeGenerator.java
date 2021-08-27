import java.util.ArrayList;
import java.io.*;

//turns the
public class nodeGenerator {
    private node startNode = null;
    private ArrayList<ArrayList<Boolean>> inputMaze;
    ArrayList<node> nodeList;

    public nodeGenerator(ArrayList<ArrayList<Boolean>> _inputMaze){
        inputMaze = _inputMaze;
        startNode = startNodes(inputMaze.get(0));
        searchNode();
        printNodes();
    }

    //finds the starting point for the tree
    private node startNodes(ArrayList<Boolean> inputLine){
        for(int i = 0; i < inputLine.size(); i++) {
            if (inputLine.get(i) == false) {
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
                if(!inputMaze.get(i).get(j)){
                    //Check if there is an intersection or corner piece
                    boolean lC = false, rC = false, uC = false, dC = false;
                    if(++i <= inputMaze.size()-1 ){
                        if(!inputMaze.get(++i).get(j)){uC = true;}
                    }
                    if(--i >=0 ){
                        if(!inputMaze.get(--i).get(j)){dC = true;}
                    }
                    if(j++ <=inputMaze.get(i).size()-1 ){
                        if(!inputMaze.get(i).get(++j)){rC = true;}
                    }
                    if(--j >=0 ){
                        if(!inputMaze.get(i).get(--j)){lC = true;}
                    }
                    //If there is a corner of any type
                    if( (lC || rC) && (uC || dC)){
                        nodeList.add(new node(i, j));
                    }
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
/*


 */
