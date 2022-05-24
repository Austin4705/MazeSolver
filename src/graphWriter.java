import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class graphWriter {
        public graphWriter(){
            embeddGraph();
            writeToFile();
        }
        public void writeToFile(String fileName){
            try{
                FileWriter w = new FileWriter(fileName);
                mazeData maze = mazeData.getInstance();
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
            }
            catch (FileNotFoundException e){
                System.out.println("File not Found");
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void embeddGraph(){
            //for every list
            for(int i = 0; i < mazeData.getInstance().listDir.size(); i++)
                for(int j = 0; j < mazeData.getInstance().listDir.get(i).toArray().length; j++){
                    node n = (node)mazeData.getInstance().listDir.get(i).toArray()[j];
                    int a = mazeData.getInstance().data.get(n.idx);
                    if(!(a == 2 || a == 3)){
                        mazeData.getInstance().data.set(n.idx, 4);
                    }
                }
        }
}
