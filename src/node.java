import java.util.HashSet;

//basic container class for a node. Stores position of node adj graph, and value. More like a struct if anything
public class node {
    public int value = 0;
    public HashSet<node> neighbors = new HashSet<node>();
    public int idx = 0;
    public node(){};
    public int[] dimensions;
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
