import java.util.ArrayList;
import java.io.*;

public class nodeGenerator {
    nodeTree NodeTree = new nodeTree();

    public nodeGenerator(ArrayList<ArrayList<Boolean>> inputmaze){
        startNodes(inputmaze);
    }
    private void startNodes(ArrayList<ArrayList<Boolean>> inputmaze){//finds the starting point for the tree
        for(int counter = 0; counter < inputmaze.get(0).size(); counter++) {
            if (inputmaze.get(0).get(counter) == false) {
                NodeTree.addStartNode(counter);
                System.out.print("Starting Points (line 1, xchords): ");
                System.out.print(counter+1);
                System.out.print("\nNodes:\n");
            }
        }
    }

}
class nodeTree {
    ArrayList<node> startNodes = new ArrayList<>();
    void addStartNode(int x){
        startNodes.add(new node(x, 0));{
        }
    }
}
class node {
    node (int inputx, int inputy){
        x = inputx;
        y = inputy;
    }
    public int x; //x chordnate
    public int y; //y chordnate
    public node leftNode = null;
    public node rightNode = null;
    public node upNode = null;
    public node downNode = null;
}
