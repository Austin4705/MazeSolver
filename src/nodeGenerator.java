import java.util.ArrayList;

public class nodeGenerator {
    nodeTree NodeTree = new nodeTree();
    public nodeGenerator(ArrayList<ArrayList<Boolean>> inputmaze){
        startNodes(inputmaze);
    }
    private void startNodes(ArrayList<ArrayList<Boolean>> inputmaze){//finds the starting point for the tree
        for(int counter = 0; counter < inputmaze.get(0).size(); counter++)
            if(inputmaze.get(0).get(counter) == true){
                NodeTree.addStartNode(counter);
            }
    }

}
