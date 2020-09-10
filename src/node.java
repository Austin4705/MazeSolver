import java.util.ArrayList;
public class node {
    node (int inputx, int inputy){
        x = inputx;
        y = inputy;
    }
    public int x; //x chordnate
    public int y; //y chordnate
    public node connectedLeft; //true if there is a node connected left, null if it is connected to nothing
    public node connectedRight; //true if there is a node connected right, null if it is connected to nothing
    public node connectedUp; //true if there is a node connected up, null if it is connected to nothing
    public node connectedDown; //true if there is a node connected down, null if it is connected to nothing
}
