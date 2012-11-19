/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Javalabra;

/**
 *File handling, saving and reading high score from file
 * @author jzvaris
 */
import java.io.*;

public class gameFiles {

    private int score;
    private String player;

    public gameFiles() {
        this("Default", 0);
    }
    
    /**
     * 
     * @param player name of the player that will be saved in file
     * @param score the given score of player
     */

    public gameFiles(String player, int score) {
        this.score = score;
        if (score < 0) {
            this.score = 0;
        }
        this.player = player;
    }
    
    /**
     * 
     * @param player name of the player that will be saved in file
     */

    public gameFiles(String player) {
        this(player, 0);
    }
    
    /**
     * 
     * @param score set the score for this gameFiles-object
     */
    
    public void setScore(int score){
        if (this.score<score){
            this.score=score;
        }
    }
    
    /**
     * 
     * @return true if saveScore was successful, else false
     */

    public boolean saveScore() {
        int temp = readScoreFromFile();
        if (temp < this.score) {
            saveToFile(this.score, this.player);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     * @return boolean to tell if resetting score.txt succeeded
     */
    
    public boolean resetScore() {
        return saveToFile(0, "default");
    }
    
    /**
     * Method to read score from scores.txt file, returns -1 if score couldn't be read
     * 
     * @return highscore read from file
     */
    
    public int readHighScore(){
        return readScoreFromFile();
    }
    
    /**
     * 
     * @return the score of this gameFiles-object
     */
    
    public int getScore(){
        return this.score;
    }
    
    /**
     * 
     * @return the name of this player
     */
    
    public String getNick(){
        return this.player;
    }
    

    
    /*
     * Method to read high score from scores.txt file
     */
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
            //System.err.println("Error: " + e.getMessage());
            return -1;
        }
    }

    /*
     * Method to write given score to scores.txt file
     */
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