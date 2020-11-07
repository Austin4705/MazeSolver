import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class fileReader {
    private ArrayList<ArrayList<Boolean>> maze; //what the maze is stored in

    public enum type{
        txt,
        png
    }

    fileReader(String fileName, type _type) {//constructor
        if(_type == type.txt){
            this.maze = readTxtFile(fileName);//reads file
        }
    }

    public ArrayList<ArrayList<Boolean>> outputMaze() {
        return (maze);
    }

    public void printMaze() {
        System.out.print("Maze loaded being printed:\n");
        for (int counter = 0; counter < this.maze.size(); counter++) {
            for (int counterNested = 0; counterNested < this.maze.get(counter).size(); counterNested++) {
                if (this.maze.get(counter).get(counterNested)) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }

    private ArrayList<ArrayList<Boolean>> readTxtFile(String fileName) {//opens the file and stores the maze in maze
        ArrayList<ArrayList<Boolean>> inputMaze = new ArrayList<ArrayList<Boolean>>();
        try {
            File myObj = new File(fileName);//init file stuff
            Scanner myReader = new Scanner(myObj);
            //ArrayList<ArrayList<Boolean>> mazes = new ArrayList<ArrayList<Boolean>>();//
            for (int i = 0; myReader.hasNextLine(); i++) {
                String data = myReader.nextLine();
                inputMaze.add(new ArrayList<Boolean>());
                for (int counter = 0; counter < data.length(); counter++) {
                    if (data.charAt(counter) == '#' || data.charAt(counter) == '1') {
                        inputMaze.get(i).add(true);
                    } else if(data.charAt(counter) == ' ' || data.charAt(counter) == '0'){
                        inputMaze.get(i).add(false);
                    }
                    else{}
                }
            }
            myReader.close();

        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return (inputMaze);
    }


}