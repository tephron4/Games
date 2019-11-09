
/**
 * Write a description of class MineSweeper here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class MineSweeper
{
    private String[][] fieldForPlay = new String[9][9];
    private String[][] fieldWithBombs = new String[9][9];
    private ArrayList<int[]> bombSpots = new ArrayList<int[]>();
    Human mh = new Human();
    
    private int bombs = 10;
    
    public MineSweeper(Human mh){
        this.mh = mh;
        this.fieldForPlay = fieldForPlay;
        this.fieldWithBombs = fieldWithBombs;
        this.bombSpots = bombSpots;
    }
    
    public void play(){
        // main method that runs through playing the game
    }
    
    public void initializeBombSpots(){
        // method to add to the list of where the 10 bombs are on the board
    }
    
    public void initializeBoard(){
        // puts the 10 bombs on fieldWithBombs
        // "C" for where there is no bomb and "B" for where there is a bomb
    }
    
    public void printField(){
        // prints out the fieldForPlay;
    }
    
    public boolean bomb(int[] place){
        // method to check if a place has a bomb
        return false;
    }
    
    public void flag(int[] place){
        // method to flag a spot
        // will act as a spot where the player can't move
    }
    
    public void move(int[] place){
        // method that runs the main part of making a move on the board
    }
    
    public boolean done(){
        // checks if all spots on the board except the bombs have been uncovered
        return true;
    }
    
    
}
