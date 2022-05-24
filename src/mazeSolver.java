import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class mazeSolver{
    mazeSolver(){
        solveGraph();
    }
    private void solveGraph(){
        //while the algorithm is programmed to try and connect all start and end nodes together,
        // it doesn't necessary do multi starts in the most efficient way, just guarantees it will find a solution if
        //one is out there. Tries and guarentees most effective single case solution still
        //in the future i would like to implement A* with a geometric distance heuristic. currently i think dijkstras will suffice for the project and any maze under 1000 len and 6 dimensions
        for(node start : mazeData.getInstance().adjListS.values()){
            for(node end : mazeData.getInstance().adjListE.values()){
                start = mazeData.getInstance().adjList.get(start.idx);
                end = mazeData.getInstance().adjList.get(end.idx);

                //the actual dijkstras implementation is based off of Baeldung article on it
                Set<node> settledNodes = new HashSet<node>();
                Set<node> unsettledNodes = new HashSet<node>();
                unsettledNodes.add(start);
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
                //extract path
                List<node> shortestPath = end.shortestPath;
                mazeData.getInstance().listDir.add(shortestPath);
            }
        }
    }

    //dijkstras helper function
    private static node getLowDist(Set<node> unsettledNodes){
        node lowDistNode = (node)unsettledNodes.toArray()[0];
        int lowDist = ((node) unsettledNodes.toArray()[0]).dist;
        for(node n : unsettledNodes){
            int nodeDist = n.dist;
            if(nodeDist < lowDist){
                lowDist = nodeDist;
                lowDistNode = n;
            }
        }
        return lowDistNode;
    }

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
