public class node {
    node (int inputj, int inputi){
        j = inputj;
        i = inputi;
    }

    //to
    public node lN = null;
    public node rN = null;
    public node uN = null;
    public node dN = null;

    public int lND = -1;
    public int rND = -1;
    public int uND = -1;
    public int dND = -1;

    //weight
    public int i; //y chordnate
    public int j; //x chordnate

    public boolean hasChecked = false;
    public boolean isPath = false;

}
