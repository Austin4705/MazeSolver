import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * the main aspidistras algo solve implementation of the graph*/
public class mazeSolver{

    /**default constructor, runs the solve operation*/
    mazeSolver(){
        solveGraph();
    }

    /**heart of dijkstras algorithm, runs for each start and end node pair and stores in a list the nodes req to get there*/
    private void solveGraph(){
        //while the algorithm is programmed to try and connect all start and end nodes together,
        // it doesn't necessary do multi starts in the most efficient way, just guarantees it will find a solution if
        //one is out there. Tries and guarentees most effective single case solution still
        //in the future i would like to implement A* with a geometric distance heuristic. currently i
        // think dijkstras will suffice for the project and any maze under 1000 len and 6 dimensions
        for(node start : mazeData.getInstance().adjListS.values()){
            for(node end : mazeData.getInstance().adjListE.values()){
                start = mazeData.getInstance().adjList.get(start.idx);
                end = mazeData.getInstance().adjList.get(end.idx);
                //the actual dijkstras implementation is based off of Baeldung article on it
                Set<node> settledNodes = new HashSet<node>();
                Set<node> unsettledNodes = new HashSet<node>();
                unsettledNodes.add(start);
                //filter through the settled and unsettled nodes that exist out there
                // to continuously get the most efficient path to each node
                while(!unsettledNodes.isEmpty()){
                    node curNode = getLowDist(unsettledNodes);
                    unsettledNodes.remove(curNode);
                    for(int i = 0; i < curNode.neighbors.toArray().length; i++){
                        node adjNode = (node)(curNode.neighbors.toArray())[i];
                        if(!settledNodes.contains(adjNode)){
                            calcMinDist(adjNode, curNode);
                            unsettledNodes.add(adjNode);
                        }
                    }
                    settledNodes.add(curNode);
                }
                //extract path from the end node. If no path, no list to be extracted to program runs normally
                List<node> shortestPath = end.shortestPath;
                mazeData.getInstance().listDir.add(shortestPath);
            }
        }
    }

    /**dijkstras helper function, gets the lowest distance in the group of unsettled nodes
     * @param unsettledNodes the list of nodes to look for for lowest dist
     * @return the lowest node in the group
     * */
    private static node getLowDist(Set<node> unsettledNodes){
        node lowDistNode = (node)unsettledNodes.toArray()[0];
        int lowDist = ((node) unsettledNodes.toArray()[0]).dist;
        //search through nodes
        for(node n : unsettledNodes){
            int nodeDist = n.dist;
            if(nodeDist < lowDist){
                lowDist = nodeDist;
                lowDistNode = n;
            }
        }
        return lowDistNode;
    }

    /**calculates the min distance between the source node and a cur node, stores in cur nodes path
     * @param evalN the cur node thats being evaluated to find the lowest min distance
     * @param source the soruce node of where to look
     * */
    private static void calcMinDist(node evalN, node source){
        //weight is 1, remember I went with a voxel based system. In a 2d world i can do weight and intersection system but with n dimensions
        //it gets trickier to do so
        int sourceDist = source.dist;
        if(sourceDist + 1 < evalN.dist){
            evalN.dist = sourceDist + 1;
            LinkedList<node> shortestPath = new LinkedList<>(source.shortestPath);
            shortestPath.add(source);
            evalN.shortestPath = shortestPath;
        }
    }
}
