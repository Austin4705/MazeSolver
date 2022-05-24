import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class graphWriter {
        public graphWriter(){
            embeddGraph();
        }

        //takes the maze in the multidimensional array that has the embedding of the path to solve the maze, uses it to write into a suggested file
        public void writeToFile(String fileName){
            try{
                fileName = inputSystem.fullPathStr(fileName);
                FileOutputStream x = new FileOutputStream(fileName); x = null;
                FileWriter w = new FileWriter(fileName);
                mazeData maze = mazeData.getInstance();
                w.write("Solved Maze:\n");
                w.write(mazeData.intToChar(maze.data.get(new int[maze.data.dimensions().length])));
                for(int i = 1; i < maze.data.size(); i++){
                    int[] curArr = maze.loopDimensions(i);
                    int[] prevArr = maze.loopDimensions(i-1);
                    for(int j = 0; j < curArr.length; j++){
                        if(curArr[j] < prevArr[j]){
                            w.write("\n");
                        }
                    }
                    w.write(mazeData.intToChar(maze.data.get(curArr)));
                }
                w.close();
            }
            catch (FileNotFoundException e){
                System.out.println("File not Found");
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //again, could make it a more combined command and pass around either system.out or a file but this is simpler
        public void writeToCmd(){
            mazeData maze = mazeData.getInstance();
            System.out.print(mazeData.intToChar(maze.data.get(new int[maze.data.dimensions().length])));
            for(int i = 1; i < maze.data.size(); i++){
                int[] curArr = maze.loopDimensions(i);
                int[] prevArr = maze.loopDimensions(i-1);
                for(int j = 0; j < curArr.length; j++){
                    if(curArr[j] < prevArr[j]){
                        System.out.print("\n");
                    }
                }
                System.out.print(mazeData.intToChar(maze.data.get(curArr)));
            }
        }

        //takes the path lists that were generated by dijkstras and embedds them into the actual multidimensional array
        public void embeddGraph(){
            //for every list
            for(int i = 0; i < mazeData.getInstance().listDir.size(); i++){
                for(int j = 0; j < mazeData.getInstance().listDir.get(i).toArray().length; j++){
                    node n = (node)mazeData.getInstance().listDir.get(i).toArray()[j];
                    int a = mazeData.getInstance().data.get(n.idx);
                    if(!(a == 2 || a == 3)){
                        mazeData.getInstance().data.set(n.idx, 4);
                    }
                }
            }
        }

        public void writeTo3dObj(String fileName){
            try{
                fileName = inputSystem.fullPathStr(fileName);
                FileOutputStream x = new FileOutputStream(fileName); x = null;
                mazeData maze = mazeData.getInstance();
                if(maze.data.dimensions().length <= 3){
                    FileWriter w = new FileWriter(fileName);
                    int[] dim = new int[3];
                    StringBuilder s = new StringBuilder("solid Maze\n");
                    for(int i = 0; i < maze.data.size(); i++){
                        int val = maze.data.get(i);
                        if(val == 1){
                            int[] xd = maze.loopDimensions(i);
                            if(maze.data.dimensions().length >= 1) dim[0] = xd[0];
                            if(maze.data.dimensions().length >= 2) dim[1] = xd[1];
                            if(maze.data.dimensions().length >= 3) dim[2] = xd[2];
                            s.append(cube(dim));
                        }
                    }
                    s.append("endsolid Maze");
                    w.write(s.toString());
                    w.close();
                }
                else{
                    throw new RuntimeException("Over 3 dimensions");
                }
            }
            catch (FileNotFoundException e){
                System.out.println("File not Found");
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private String cube(int[] xd){
            StringBuilder s = new StringBuilder();
            for(int i = 0; i < 8; i++){
                for(int j = i; j < 8; j++){
                    for(int k = j; k < 8; k++){
                        if(!(i == j || j == k)){
                            int[] iA = pointPlus(pointId(i), xd);
                            int[] jA = pointPlus(pointId(j), xd);
                            int[] kA = pointPlus(pointId(k), xd);
                            s.append(oneFace(iA, jA, kA));
                        }
                    }
                }
            }
            return s.toString();
        }

        private int[] pointId(int id){
            int[][] ida = {
                    {0, 0 ,0},
                    {1, 0, 0}, {0, 1, 0}, {0, 0, 1},
                    {1, 1, 0}, {1, 0, 1}, {0, 1, 1},
                    {1, 1, 1}
            };
            return ida[id];
        }
        private int[] pointPlus(int[] cur, int[] xyz){
            cur[0] += xyz[0]; cur[1] += xyz[1]; cur[2] += xyz[2];
            return cur;
        }
        private String oneFace(int[] a, int[] b, int[] c) {
            String s = "";
            s += "facet normal 0 0 0\n";
            s += "\touter loop\n";
            s += "vertex " + a[0] + ", " + a[1] + ", " + a[2] + ",\n";
            s += "vertex " + b[0] + ", " + b[1] + ", " + b[2] + ",\n";
            s += "vertex " + c[0] + ", " + c[1] + ", " + c[2] + ",\n";
            s += "\tendloop\n";
            s += "endfacet\n";
            return s;
        }


}
