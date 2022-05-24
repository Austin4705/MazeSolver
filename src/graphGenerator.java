import java.util.ArrayList;
import java.util.HashSet;
/**
 *an algorithmic class that manipulates the multidimensional arraay into a interconnected graph of lattices.
 */
public class graphGenerator {
    /*The default constructor, calls functions required to operate */
    graphGenerator(){
        convertGraph();
    }
    /**
    *converts the array into a graph structure, called in constructor
    * */
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
        //connect them up in the list of all connected nodes that each node stores
        for(int i = 0; i < maze.data.size(); i++){
            int[] curArr = maze.loopDimensions(i);
            ArrayList<int[]> neighborNodes = maze.neighborNodes(curArr);
            //should just be another way of saying node being tested is not a wall or error since it doesn't exist in adjList
            if(maze.adjList.get(maze.data.indexOf(curArr)) != null){
                for(int j = 0; j < neighborNodes.size(); j++){
                    int t = maze.data.get(neighborNodes.get(j));
                    if(t == 0 || t == 2 || t == 3){
                        //add to the currArr neighborList the neighborNode being tested
                        maze.adjList.get(maze.data.indexOf(curArr)).neighbors.add(
                            maze.adjList.get(
                                    maze.data.indexOf(
                                            neighborNodes.get(j)
                                    )
                            )
                        );
                    }
                }
            }
        }
        System.out.println("Graph Sucessfully Transplanted");
    }
}
