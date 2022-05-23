import java.util.ArrayList;
import java.util.HashSet;

//an algorithmic class that manipulates the multidimensional arraay into a interconnected graph of lattices.
public class graphGenerator {
    graphGenerator(){
        convertGraph();
    }
    private void convertGraph(){
        //Debated between filtering out wall nodes or just adding them in and deleting after, chose first as complexity is similar
        System.out.println("Beginning Graph Transplant...");
        //add all the nodes in the respective graphs
        mazeData maze = mazeData.getInstance();
        for(int i = 0; i < maze.data.size(); i++){
            int[] curArr = maze.loopDimensions(i);
            int idx = maze.data.indexOf(curArr);
            int val = maze.data.get(curArr);
            if(val == 0 || val == 2 || val == 3){
                maze.adjList.put(idx, new node(val, idx, curArr));
            }
            if(val == 2){
                maze.adjListS.put(idx, new node(val, idx, curArr));
            }
            if(val == 3){
                maze.adjListE.put(idx, new node(val, idx, curArr));
            }
        }
        //connect them up
        for(int i = 0; i < maze.data.size(); i++){
            int[] curArr = maze.loopDimensions(i);
            ArrayList<int[]> neighborNodes = maze.neighborNodes(curArr);
            node h = maze.adjList.get(maze.data.indexOf(curArr));
            if(h != null){//should be another way of saying node being tested is not a wall or error since it doesnt exist in adjList
                for(int j = 0; j < neighborNodes.size(); j++){
                    int t = maze.data.get(neighborNodes.get(j));
                    if(t == 0 || t == 2 || t == 3){
                        //add to the currArr neighborList the neighborNode being tested
                        node n = maze.adjList.get(neighborNodes.get(j));
                        h.neighbors.add(n);
                    }
                }
            }
        }
        System.out.println("Graph Sucessfully Transplanted");
    }
}
