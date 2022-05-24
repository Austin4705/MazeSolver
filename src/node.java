import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**basic container class for a node. Stores position of node adj graph, and value. More like a struct if anything*/
public class node {

    //main data stored in each node
    //value of the node, (what type (wall start end path etc))
    public int value = 0;
    //list of touching nodes
    public HashSet<node> neighbors = new HashSet<node>();
    //id in the nd arrary, remember had to abstract to nd dimensions
    public int idx = 0;
    //coords to the node in the nD array
    public int[] dimensions;
    //if It's used to find the actual path
    public boolean usedAsPath = false;
    //dist, used for dijkstras
    public int dist = Integer.MAX_VALUE;
    //list of the nodes used to find the shortest path to it
    public List<node> shortestPath = new LinkedList<node>();

    //constructors that initialize the node. Very self-explanatory, overloaded syntax cleaners
    // that all do the same thing so no need to re-comment. can take in either
    // nothing, value and id, value id and neighbored, value id neighbors and dimensions, or value id and dimensions
    public node(){};
    public node(int _value, int _idx){
        value = _value;
        idx = _idx;
    }
    public node(int _value, int _idx, HashSet<node> _neighbors){
        value = _value;
        idx = _idx;
        neighbors = _neighbors;
    }
    public node(int _value, int _idx, HashSet<node> _neighbors, int[] _dimensions){
        value = _value;
        idx = _idx;
        neighbors = _neighbors;
        dimensions = _dimensions;
    }
    public node(int _value, int _idx, int[] _dimensions){
        value = _value;
        idx = _idx;
        dimensions = _dimensions;
    }
}
