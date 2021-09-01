import java.util.ArrayList;
import java.io.*;
import java.lang.Object;
import java.util.HashMap;
import java.util.Objects;

//turns the
public class nodeGenerator {

    private ArrayList<ArrayList<Boolean>> inputMaze; //Boolean input maze of walls of walkway
    //inputMaze -> nodes -> used to find the collision --\/
    // L--> nodeMap -> hash when a collision with node  -> startNode
    // L--> nodeList -> List to index each node         ---^
    private node startNode = null; //tree of the nodes, also the final version finishes off the tree
    private node endNode = null;
    private ArrayList<ArrayList<String>> nodes; //List of the parsed nodes
    private HashMap<String, node> nodeMap = new HashMap<>(); //Un ordered hash map of the nodes
    ArrayList<node> nodeList; //Array list of the nodes

    public ArrayList<ArrayList<String>> getNodes(){
        return nodes;
    }
    static enum dir{
      left, right, up, down
    };

    //Constructor, basic flow of the class
    public nodeGenerator(ArrayList<ArrayList<Boolean>> _inputMaze){
        inputMaze = _inputMaze;
        startNode = startNodes(inputMaze.get(0));
        nodes = new ArrayList<ArrayList<String>>();
        searchNode();
        buildTree();
        endNode(inputMaze.get(inputMaze.size()-1), inputMaze.size()-1);
    }

    //returns the tree (what we use the class to find)
    public node returnTree(){return startNode;}
    public node returnEnd(){return endNode;}

    //TODO could parallelize
    //looks if there is a node in each direction for each node
    private void buildTree(){
        for (var x: nodeList) {
            findObj(x, dir.left);
            findObj(x, dir.right);
            findObj(x, dir.up);
            findObj(x, dir.down);
        }
    }

    //looks in a direction if there is a node and adds it to the node tree if there is
    private void findObj(node base, dir direction){
        node foundNode = null;
        boolean isNodeFound = false;
        int i = base.i, j = base.j;
        int d = 1;
        switch (direction){
            case up -> {
                for (;i >= 0; i--) {
                    if(i == base.i)
                        continue;
                   if(Objects.equals(nodes.get(i).get(j), "O"))
                       break;
                    if(Objects.equals(nodes.get(i).get(j), "H")){
                        d++;
                        continue;
                    }
                    if(Objects.equals(nodes.get(i).get(j), "X")){
                        String t = i + ", " + j;
                        foundNode = nodeMap.get(t);
                        isNodeFound = true;
                        base.uND = d;
                        break;
                    }
                }
            }
            case down -> {
                for (; i < nodes.size(); i++) {
                    if(i == base.i)
                        continue;
                    if(Objects.equals(nodes.get(i).get(j), "O"))
                        break;
                    if(Objects.equals(nodes.get(i).get(j), "H")){
                        d++;
                        continue;
                    }
                    if(Objects.equals(nodes.get(i).get(j), "X")){
                        String t = i + ", " + j;
                        foundNode = nodeMap.get(t);
                        isNodeFound = true;
                        base.dND = d;
                        break;
                    }
                }
            }
            case left -> {
                for (; j >= 0; j--) {
                    if(j == base.j)
                        continue;
                    if(Objects.equals(nodes.get(i).get(j), "O"))
                        break;
                    if(Objects.equals(nodes.get(i).get(j), "H")){
                        d++;
                        continue;
                    }
                    if(Objects.equals(nodes.get(i).get(j), "X")){
                        String t = i + ", " + j;
                        foundNode = nodeMap.get(t);
                        isNodeFound = true;
                        base.lND = d;
                        break;
                    }
                }
            }
            case right -> {
                for (; j < nodes.get(i).size(); j++) {
                    if(j == base.j)
                        continue;
                    if(Objects.equals(nodes.get(i).get(j), "O"))
                        break;
                    if(Objects.equals(nodes.get(i).get(j), "H")){
                        d++;
                        continue;
                    }
                    if(Objects.equals(nodes.get(i).get(j), "X")){
                        String t = i + ", " + j;
                        foundNode = nodeMap.get(t);
                        isNodeFound = true;
                        base.rND = d;
                        break;
                    }
                }
            }
        }

        //Iterate and find
        if(isNodeFound){
            String t = i + ", " + j;
            foundNode = nodeMap.get(t);
            switch (direction){
                case up -> {
                     base.uN = foundNode;
                }
                case down -> {
                    base.dN = foundNode;
                }
                case left -> {
                    base.lN = foundNode;
                }
                case right -> {
                    base.rN = foundNode;
                }
            }
        }


    }

    //finds the starting point for the tree, mostly used just if there isn't one
    private node startNodes(ArrayList<Boolean> inputLine){
        for(int i = 0; i < inputLine.size(); i++) {
            if (!inputLine.get(i)) {
                return(new node(i, 0));
            }
        }
        System.out.println("No Start Node Found");
        return(new node(0, 0));
    }

    //locates the end node
    private node endNode(ArrayList<Boolean> inputLine, int jSize){
        for(int i = 0; i < inputLine.size(); i++) {
            if (!inputLine.get(i)) {
                String t = jSize + ", " + i;//its reversed here but ok
                node tN = nodeMap.get(t);
                endNode = tN;
                break;
            }
        }
        System.out.println("No End Node Found");
        return(new node(0, 0));
    }

    //Search the maze for all the squares that are nodes, also look for first node, set if wall or not node walkway too, and actually find first node
    private void searchNode(){
        nodeList = new ArrayList<node>();
        boolean firstNodeFlag = false;
        for(int i = 0; i < inputMaze.size(); i++){
            nodes.add(new ArrayList<String>());
            for (int j = 0; j < inputMaze.get(i).size(); j++){
                //For each spot
                //If it is a maze part
                boolean isNode = false;

                if(!inputMaze.get(i).get(j)){
                    //Check if there is an intersection or corner piece
                    boolean lC = false, rC = false, uC = false, dC = false;
                    int counter = 0;
                    if(i + 1 <= inputMaze.size()-1 ){
                        if(!inputMaze.get(i+1).get(j)){dC = true; counter++;}
                    }
                    if(i- 1 >= 0 ){
                        if(!inputMaze.get(i-1).get(j)){uC = true; counter++;}
                    }
                    if(j + 1 <= inputMaze.get(i).size() - 1){
                        if(!inputMaze.get(i).get(j+1)){rC = true; counter++;}
                    }
                    if(j- 1 >= 0 ){
                        if(!inputMaze.get(i).get(j-1)){lC = true; counter++;}
                    }
                    //If there is a corner of any type

                    if( ((lC || rC) && (uC || dC)) || counter != 2 /*|| i == inputMaze.size()-1*/
                    ){
                        isNode = true;
                        node tNode = new node(j, i);
                        nodeList.add(tNode);
                        String t = i + ", " + j;
                        nodeMap.put(t, tNode);
                        if(!firstNodeFlag){
                            startNode = tNode;
                            firstNodeFlag = true;
                        }
                    }
                }

                if (isNode){
                    nodes.get(i).add("X");
                }
                else if (!inputMaze.get(i).get(j)){
                    nodes.get(i).add("H");
                }
                else{
                    nodes.get(i).add("O");
                }

            }
        }
    }

    //Prints out a list of the nodes
    public void printNodes(){
        for (node x: nodeList) {
            System.out.println("X: " + x.j + ", Y: " + x.i);
        }
    }

}
