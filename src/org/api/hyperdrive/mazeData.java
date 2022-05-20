package org.api.hyperdrive;
import java.lang.Object.*;
import java.lang.reflect.Array;
//singleton implementation of class in order to function as object container.
public class mazeData {
//region singletonConstructor
    private static mazeData instance = null;
    private mazeData() {}

    public static mazeData getInstance() {
        if (instance == null) {
            instance = new mazeData();
        }
        return instance;
    }
//endregion
//region member
    public Object data;
//endregion
//region func
    public void makeArray(int[] dimensions){
        NArrayInt arr = new NArrayInt(dimensions);
        data = Array.newInstance(Character.class, dimensions);

    }
//endregion
}
