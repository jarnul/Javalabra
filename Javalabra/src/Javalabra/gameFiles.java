/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Javalabra;

/**
 *
 * @author jzvaris
 */
import java.io.*;

public class gameFiles {

    private int score;
    private String player;

    public gameFiles() {
        this("Default", 0);
    }

    public gameFiles(String player, int score) {
        this.score = score;
        if (score < 0) {
            this.score = 0;
        }
        this.player = player;
    }

    public gameFiles(String player) {
        this(player, 0);
    }

    public boolean saveScore() {
        int temp = readScoreFromFile();
        if (temp < this.score) {
            saveToFile(this.score, this.player);
            return true;
        } else {
            return false;
        }
    }
    
    public int readHighScore(){
        return readScoreFromFile();
    }

    private int readScoreFromFile() {
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream("scores.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int temp = 0;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                strLine = strLine.replaceAll("[^\\d]", "");
                temp = Integer.parseInt(strLine);
            }
            //Close the input stream
            in.close();
            return temp;
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            return -1;
        }
    }

    private boolean saveToFile(int highScore, String player) {
        try {
            // Create file 
            FileWriter fstream = new FileWriter("scores.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("Highscore is: " + Integer.toString(highScore) + " by " + player);
            //Close the output stream
            out.close();
            return true;
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
}
