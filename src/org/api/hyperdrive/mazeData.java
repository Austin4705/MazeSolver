package org.api.hyperdrive;
import java.lang.Object.*;
import java.lang.reflect.Array;


//singleton implementation of class in order to function as object container.
public class mazeData {
//region singletonConstructor
    private static mazeData instance = null;
    private mazeData() {
        data = new NArray<Character>() {
            @Override
            public Character get(int idx) {
                return null;
            }

            @Override
            public void set(int idx, Character value) {

            }
        };
    }

    public static mazeData getInstance() {
        if (instance == null) {
            instance = new mazeData();
        }
        return instance;
    }
//endregion
//region member
    public NArrayInt data;
//endregion
//region func
    public void makeArray(int[] dimensions){
        data = new NArray<Character>(dimensions);
    }
    public int[] getFreshCoord(){
        int size = data.dimensions().length();

    }
//endregion
}
