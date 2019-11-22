
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
    private String[][] field = new String[9][9];
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
        this.field = field;
        this.bombSpots = bombSpots;
    }
    
    /**
     * The main method that will run the game
     */
    public void play(){
        // main method that runs through playing the game
        this.initializeField();
        this.printField();
        this.flag(new int[]{4,3});
        this.printField();
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
     * A method to initilize all the places on field to " "
     */
    public void initializeField(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                this.field[i][j] = " ";
            }
        }
    }
    
    /**
     * A method to print the field/board
     */
    public void printField(){
        System.out.println("");
        System.out.println("");
        System.out.println("  0 1 2 3 4 5 6 7 8");
        System.out.println("  - - - - - - - - -");
        System.out.println("5|" + this.field[5][5] + "|" + this.field[5][1] + "|" + this.field[5][2] + "|" +
                            this.field[5][3] + "|" + this.field[5][4] + "|" + this.field[5][5] + "|" + this.field[5][6] +
                            "|" + this.field[5][7] + "|" + this.field[5][8] + "|");
        System.out.println("  - - - - - - - - -");
        System.out.println("1|" + this.field[1][5] + "|" + this.field[1][1] + "|" + this.field[1][2] + "|" +
                            this.field[1][3] + "|" + this.field[1][4] + "|" + this.field[1][5] + "|" + this.field[1][6] +
                            "|" + this.field[1][7] + "|" + this.field[1][8] + "|");
        System.out.println("  - - - - - - - - -");
        System.out.println("2|" + this.field[2][5] + "|" + this.field[2][1] + "|" + this.field[2][2] + "|" +
                            this.field[2][3] + "|" + this.field[2][4] + "|" + this.field[2][5] + "|" + this.field[2][6] +
                            "|" + this.field[2][7] + "|" + this.field[2][8] + "|");
        System.out.println("  - - - - - - - - -");
        System.out.println("3|" + this.field[3][5] + "|" + this.field[3][1] + "|" + this.field[3][2] + "|" +
                            this.field[3][3] + "|" + this.field[3][4] + "|" + this.field[3][5] + "|" + this.field[3][6] +
                            "|" + this.field[3][7] + "|" + this.field[3][8] + "|");
        System.out.println("  - - - - - - - - -");
        System.out.println("4|" + this.field[4][5] + "|" + this.field[4][1] + "|" + this.field[4][2] + "|" +
                            this.field[4][3] + "|" + this.field[4][4] + "|" + this.field[4][5] + "|" + this.field[4][6] +
                            "|" + this.field[4][7] + "|" + this.field[4][8] + "|");
        System.out.println("  - - - - - - - - -");
        System.out.println("5|" + this.field[5][0] + "|" + this.field[5][1] + "|" + this.field[5][2] + "|" +
                            this.field[5][3] + "|" + this.field[5][4] + "|" + this.field[5][5] + "|" + this.field[5][6] +
                            "|" + this.field[5][7] + "|" + this.field[5][8] + "|");
        System.out.println("  - - - - - - - - -");
        System.out.println("6|" + this.field[6][0] + "|" + this.field[6][1] + "|" + this.field[6][2] + "|" +
                            this.field[6][3] + "|" + this.field[6][4] + "|" + this.field[6][5] + "|" + this.field[6][6] +
                            "|" + this.field[6][7] + "|" + this.field[6][8] + "|");
        System.out.println("  - - - - - - - - -");
        System.out.println("7|" + this.field[7][0] + "|" + this.field[7][1] + "|" + this.field[7][2] + "|" +
                            this.field[7][3] + "|" + this.field[7][4] + "|" + this.field[7][5] + "|" + this.field[7][6] +
                            "|" + this.field[7][7] + "|" + this.field[7][8] + "|");
        System.out.println("  - - - - - - - - -");
        System.out.println("8|" + this.field[8][0] + "|" + this.field[8][1] + "|" + this.field[8][2] + "|" +
                            this.field[8][3] + "|" + this.field[8][4] + "|" + this.field[8][5] + "|" + this.field[8][6] +
                            "|" + this.field[8][7] + "|" + this.field[8][8] + "|");
        System.out.println("  - - - - - - - - -");
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
     * The main method to get a move and do what's needed to execute the move
     */
    public void move(){
        // method that runs the main part of making a move on the board
    }

    /**
     * A method to execute the move
     *
     * @param place an integer array with the coordinates to be move to
     */
    public void updateField(int[] place){

    }

    /**
     * A method to count the number of bombs directly around a given place
     *
     * @param place an integer array with the coordinates to check around
     */
    public int bombsArround(int[] place){
        int count = 0;
        if(place[0] == 0){
            if(place[1] == 0){
                if(this.bomb(new int[]{0,1})) count++;
                if(this.bomb(new int[]{1,1})) count++;
                if(this.bomb(new int[]{1,0})) count++;
            }
            else if(place[1] == 8){
                if(this.bomb(new int[]{0,7})) count++;
                if(this.bomb(new int[]{1,7})) count++;
                if(this.bomb(new int[]{1,8})) count++;
            }
            else{
                if(this.bomb(new int[]{0,place[1]-1})) count++;
                if(this.bomb(new int[]{1,place[1]-1})) count++;
                if(this.bomb(new int[]{1,place[1]})) count++;
                if(this.bomb(new int[]{1,place[1]+1})) count++;
                if(this.bomb(new int[]{0,place[1]+1})) count++;
            }
        }
        else if(place[0] == 8){
            if(place[1] == 0){
                if(this.bomb(new int[]{7,0})) count++;
                if(this.bomb(new int[]{7,1})) count++;
                if(this.bomb(new int[]{8,1})) count++;
            }
            else if(place[1] == 8){
                if(this.bomb(new int[]{8,7})) count++;
                if(this.bomb(new int[]{7,7})) count++;
                if(this.bomb(new int[]{7,8})) count++;
            }
            else{
                if(this.bomb(new int[]{8,place[1]-1})) count++;
                if(this.bomb(new int[]{7,place[1]-1})) count++;
                if(this.bomb(new int[]{7,place[1]})) count++;
                if(this.bomb(new int[]{7,place[1]+1})) count++;
                if(this.bomb(new int[]{8,place[1]+1})) count++;
            }
        }
        else if(place[1] == 0){
            if(this.bomb(new int[]{place[0]-1,0})) count++;
            if(this.bomb(new int[]{place[0]-1,1})) count++;
            if(this.bomb(new int[]{place[0],1})) count++;
            if(this.bomb(new int[]{place[0]+1,1})) count++;
            if(this.bomb(new int[]{place[0]+1,0})) count++;
        }
        else if(place[1] == 8){
            if(this.bomb(new int[]{place[0]-1,8})) count++;
            if(this.bomb(new int[]{place[0]-1,7})) count++;
            if(this.bomb(new int[]{place[0],7})) count++;
            if(this.bomb(new int[]{place[0]+1,7})) count++;
            if(this.bomb(new int[]{place[0]+1,8})) count++;
        }
        else{
            if(this.bomb(new int[]{place[0],place[1]-1})) count++;
            if(this.bomb(new int[]{place[0]-1,place[1]-1})) count++;
            if(this.bomb(new int[]{place[0]-1,place[1]})) count++;
            if(this.bomb(new int[]{place[0]-1,place[1]+1})) count++;
            if(this.bomb(new int[]{place[0],place[1]+1})) count++;
            if(this.bomb(new int[]{place[0]+1,place[1]+1})) count++;
            if(this.bomb(new int[]{place[0]+1,place[1]})) count++;
            if(this.bomb(new int[]{place[0]+1,place[1]-1})) count++;
        }
        return count;
    }

    /**
     * A method to flag a place: will put an "F" on the field/board
     * 
     * @param place an integer array with the coordinates to be flagged
     */
    public void flag(int[] place){
        // method to flag a spot
        this.field[place[0]][place[1]] = "F";
    }
    
    /**
     * A method for adding "B" to the field/board wherever there is a bomb
     */
    public void addBombSpots(){
        // method that displays the bombs on the field when the game ends
        for(int[] spot: this.bombSpots){
            this.field[spot[0]][spot[1]] = "B";
        }
    }
    
    /**
     * A method to check if the player has moved on all the spots without bombs
     * 
     * @return true if the player has moved to all non-bomb spots; false otherwise
     */
    public boolean done(){
        // checks if all spots on the board except the bombs have been uncovered
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(!this.bomb(new int[]{i,j}) && this.field[i][j].equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * A method to check that a given place is a valid place to move
     * 
     * @param place an integer array with the coordinates to be checked
     */
    public boolean validMove(int[] place){
        if(this.field[place[0]][place[1]].equals(" ")) return true;
        return false;
    }
}
