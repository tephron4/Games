
/**
 * Class for the MineSweeper game
 *
 * @author (Tobias Ephron)
 * @version (Version 1, Nov. 14, 2019)
 */
import java.util.*;
public class MineSweeper
{
    /** A 2D array of Strings for the board/field to play on */
    private String[][] fieldForPlay = new String[9][9];
    /** An array of integer arrays for the coordinates of the bombs */
    private ArrayList<int[]> bombSpots = new ArrayList<int[]>();
    /** A new instance of a Human */
    Human mh = new Human();
    /** A variable for the number of bombs */
    private int bombs = 10;
    
    /**
     * A constructor method for the MineSweeper class
     * 
     * @param mh a Human object
     */
    public MineSweeper(Human mh){
        this.mh = mh;
        this.fieldForPlay = fieldForPlay;
        this.bombSpots = bombSpots;
    }
    
    /**
     * The main method that will run the game
     */
    public void play(){
        // main method that runs through playing the game
    }
    
    /**
     * A method to get all the coordinates for the bombs at random
     */
    public void initializeBombSpots(){
        // method to add to the list of where the 10 bombs are on the board
        int bombs = 0;
        while(bombs < 10){
            int x = (int) (Math.random() * 10.0);
            bombs++;
        }
    }
    
    /**
     * A method to print the field/board
     */
    public void printField(){
        // prints out the fieldForPlay;
    }
    
    /**
     * A method to check if a coordinate has a bomb there
     * 
     * @param place an integer array with the coordinates that are being checked
     * @return true if there is a bomb at the coordinates given; false otherwise
     */
    public boolean bomb(int[] place){
        // method to check if a place has a bomb
        return false;
    }
    
    /**
     * A method to flag a place: will put an "F" on the field/board
     * 
     * @param place an integer array with the coordinates to be flagged
     */
    public void flag(int[] place){
        // method to flag a spot
    }
    
    /**
     * The main method to get and exicute a move
     */
    public void move(){
        // method that runs the main part of making a move on the board
    }
    
    /**
     * A method for adding "B" to the field/board wherever there is a bomb
     */
    public void addBombSpots(){
        // method that displays the bombs on the field when the game ends
    }
    
    /**
     * A method to check if the player has moved on all the spots without bombs
     * 
     * @return true if the player has moved to all non-bomb spots; false otherwise
     */
    public boolean done(){
        // checks if all spots on the board except the bombs have been uncovered
        return true;
    }
}
