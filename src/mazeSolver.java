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
                //the actual dijkstras implementation is based off of Baeldung article on it, emphasis on based
                Set<node> settledNodes = new HashSet<node>();
                Set<node> unsettledNodes = new HashSet<node>();
                unsettledNodes.add(start);

            }
        }
    }

}
