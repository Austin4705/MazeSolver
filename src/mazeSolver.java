import java.util.HashSet;
import java.util.Set;

public class mazeSolver{
    mazeSolver(){
        solveGraph();
    }
    private void solveGraph(){
        //while the algorithm is programmed to try and connect all start and end nodes together,
        // it doesn't necessary do multi starts in the most efficient way, just guarantees it will find a solution if
        //one is out there. Tries and guarentees most effective single case solution still
        for(node start : mazeData.getInstance().adjListS.values()){
            for(node end : mazeData.getInstance().adjListE.values()){
                //the actual dijkstras implementation is based off of Baeldung article on it
                Set<node> settledNodes = new HashSet<node>();
                Set<node> unsettledNodes = new HashSet<node>();
                unsettledNodes.add(start);
                while(unsettledNodes.size() != 0){
                    node curNode = getLowDist(unsettledNodes);
                    unsettledNodes.remove(curNode);

                }
            }
        }
    }

    //dijkstras helper function
    private static node getLowDist(Set<node> unsettledNodes){
        node lowDistNode = null;
        int lowDist = Integer.MAX_VALUE;
        for(node n : unsettledNodes){
            int nodeDist = n.dist;
            if(nodeDist < lowDist){
                lowDist = nodeDist;
                lowDistNode = n;
            }
        }
        return lowDistNode;
    }

    private static void calcMinDist(node evalN, node source, int edgeWeight){
        //weight is 1, remember I went with a voxel based system. In a 2d world i can do weight and intersection system but with n dimensions
        //it gets trickier to do so
        int sourceDist = source.dist;
        if(sourceDist + edgeWeight < evalN.dist){
            evalN.set
        }
    }
}
