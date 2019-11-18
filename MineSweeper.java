
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
        int bombsAdded = 0;
        while(bombsAdded < 10){
            int x = (int) (Math.random() * 10.0);
            int y = (int) (Math.random() * 10.0);
            int[] spot = new int[]{x,y};
            if(this.bombSpots.contains(spot)){
                continue;
            }
            this.bombSpots.add(spot);
            bombsAdded++;
        }
    }
    
    /**
     * A method to initilize all the places on fieldToPlay to " "
     */
    public void initializeField(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                this.fieldForPlay[i][j] = " ";
            }
        }
    }
    
    /**
     * A method to print the field/board
     */
    public void printField(){
        System.out.println("");
        System.out.println("");
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        System.out.println("  _ _ _ _ _ _ _ _ _ _");
        System.out.println("5|" + this.fieldForPlay[5][5] + "|" + this.fieldForPlay[5][1] + "|" + this.fieldForPlay[5][2] + "|" +
                            this.fieldForPlay[5][3] + "|" + this.fieldForPlay[5][4] + "|" + this.fieldForPlay[5][5] + "|" + this.fieldForPlay[5][6] +
                            "|" + this.fieldForPlay[5][7] + "|" + this.fieldForPlay[5][8] + "|" + this.fieldForPlay[5][9]);
        System.out.println("  _ _ _ _ _ _ _ _ _ _");
        System.out.println("1|" + this.fieldForPlay[1][5] + "|" + this.fieldForPlay[1][1] + "|" + this.fieldForPlay[1][2] + "|" +
                            this.fieldForPlay[1][3] + "|" + this.fieldForPlay[1][4] + "|" + this.fieldForPlay[1][5] + "|" + this.fieldForPlay[1][6] +
                            "|" + this.fieldForPlay[1][7] + "|" + this.fieldForPlay[1][8] + "|" + this.fieldForPlay[1][9]);
                            System.out.println("  _ _ _ _ _ _ _ _ _ _");
        System.out.println("2|" + this.fieldForPlay[2][5] + "|" + this.fieldForPlay[2][1] + "|" + this.fieldForPlay[2][2] + "|" +
                            this.fieldForPlay[2][3] + "|" + this.fieldForPlay[2][4] + "|" + this.fieldForPlay[2][5] + "|" + this.fieldForPlay[2][6] +
                            "|" + this.fieldForPlay[2][7] + "|" + this.fieldForPlay[2][8] + "|" + this.fieldForPlay[2][9]);
        System.out.println("  _ _ _ _ _ _ _ _ _ _");
        System.out.println("3|" + this.fieldForPlay[3][5] + "|" + this.fieldForPlay[3][1] + "|" + this.fieldForPlay[3][2] + "|" +
                            this.fieldForPlay[3][3] + "|" + this.fieldForPlay[3][4] + "|" + this.fieldForPlay[3][5] + "|" + this.fieldForPlay[3][6] +
                            "|" + this.fieldForPlay[3][7] + "|" + this.fieldForPlay[3][8] + "|" + this.fieldForPlay[3][9]);
        System.out.println("  _ _ _ _ _ _ _ _ _ _");
        System.out.println("4|" + this.fieldForPlay[4][5] + "|" + this.fieldForPlay[4][1] + "|" + this.fieldForPlay[4][2] + "|" +
                            this.fieldForPlay[4][3] + "|" + this.fieldForPlay[4][4] + "|" + this.fieldForPlay[4][5] + "|" + this.fieldForPlay[4][6] +
                            "|" + this.fieldForPlay[4][7] + "|" + this.fieldForPlay[4][8] + "|" + this.fieldForPlay[4][9]);
        System.out.println("  _ _ _ _ _ _ _ _ _ _");
        System.out.println("5|" + this.fieldForPlay[5][0] + "|" + this.fieldForPlay[5][1] + "|" + this.fieldForPlay[5][2] + "|" +
                            this.fieldForPlay[5][3] + "|" + this.fieldForPlay[5][4] + "|" + this.fieldForPlay[5][5] + "|" + this.fieldForPlay[5][6] +
                            "|" + this.fieldForPlay[5][7] + "|" + this.fieldForPlay[5][8] + "|" + this.fieldForPlay[5][9]);
        System.out.println("  _ _ _ _ _ _ _ _ _ _");
        System.out.println("6|" + this.fieldForPlay[6][0] + "|" + this.fieldForPlay[6][1] + "|" + this.fieldForPlay[6][2] + "|" +
                            this.fieldForPlay[6][3] + "|" + this.fieldForPlay[6][4] + "|" + this.fieldForPlay[6][5] + "|" + this.fieldForPlay[6][6] +
                            "|" + this.fieldForPlay[6][7] + "|" + this.fieldForPlay[6][8] + "|" + this.fieldForPlay[6][9]);
        System.out.println("  _ _ _ _ _ _ _ _ _ _");
        System.out.println("7|" + this.fieldForPlay[7][0] + "|" + this.fieldForPlay[7][1] + "|" + this.fieldForPlay[7][2] + "|" +
                            this.fieldForPlay[7][3] + "|" + this.fieldForPlay[7][4] + "|" + this.fieldForPlay[7][5] + "|" + this.fieldForPlay[7][6] +
                            "|" + this.fieldForPlay[7][7] + "|" + this.fieldForPlay[7][8] + "|" + this.fieldForPlay[7][9]);
        System.out.println("  _ _ _ _ _ _ _ _ _ _");
        System.out.println("8|" + this.fieldForPlay[8][0] + "|" + this.fieldForPlay[8][1] + "|" + this.fieldForPlay[8][2] + "|" +
                            this.fieldForPlay[8][3] + "|" + this.fieldForPlay[8][4] + "|" + this.fieldForPlay[8][5] + "|" + this.fieldForPlay[8][6] +
                            "|" + this.fieldForPlay[8][7] + "|" + this.fieldForPlay[8][8] + "|" + this.fieldForPlay[8][9]);
        System.out.println("  _ _ _ _ _ _ _ _ _ _");
        System.out.println("9|" + this.fieldForPlay[9][0] + "|" + this.fieldForPlay[9][1] + "|" + this.fieldForPlay[9][2] + "|" +
                            this.fieldForPlay[9][3] + "|" + this.fieldForPlay[9][4] + "|" + this.fieldForPlay[9][5] + "|" + this.fieldForPlay[9][6] +
                            "|" + this.fieldForPlay[9][7] + "|" + this.fieldForPlay[9][8] + "|" + this.fieldForPlay[9][9]);
        System.out.println("");
        System.out.println("");
    }
    
    /**
     * A method to check if a coordinate has a bomb there
     * 
     * @param place an integer array with the coordinates that are being checked
     * @return true if there is a bomb at the coordinates given; false otherwise
     */
    public boolean bomb(int[] place){
        // method to check if a place has a bomb
        if(this.bombSpots.contains(place)) return true;
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
        for(int[] spot: this.bombSpots){
            this.fieldForPlay[spot[0]][spot[1]] = "B";
        }
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
