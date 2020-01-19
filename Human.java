
/**
 * Write a description of class Human here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Human
{
    /** A string for the player's piece */
    String piece;
    /** A string for the player's username */
    private String username;
    //private String password;
    /** Integers to keep track of the players TicTacToe stats */
    private int tWins;
    private int tLosses;
    private int tTies;
    private int tGamesPlayed;
    /** Integers to keep track of the players Othelo stats */
    private int oWins;
    private int oLosses;
    private int oTies;
    private int oGamesPlayed;
    /** Integers to keep track of the players MineSweeper stats */
    private int mWins;
    private int mLosses;
    private int mGamesPlayed;
    /** A basic Scanner object */
    Scanner sc = new Scanner(System.in);
    
    /**
     * An empty Human constructor
     */
    public Human(){}
    
    /**
     * A Human constructor with username
     *
     * @param username a String for the player's username
     */
    public Human(String username){
        this.username = username;
        //this.password = password;
        this.piece = piece;
        this.tWins = 0;
        this.tLosses = 0;
        this.tTies = 0;
        this.tGamesPlayed = 0;
        this.oWins = 0;
        this.oLosses = 0;
        this.oTies = 0;
        this.oGamesPlayed = 0;
        this.mWins = 0;
        this.mLosses = 0;
        this.mGamesPlayed = 0;
    }
    
    /**
     * A method to get a player's TicTacToe play
     *
     * @param board the game board (a String array)
     * @return an integer corresponding to where they want to move on the board
     */
    public int tPlay(String[] board){
        int mv = -1;
        boolean gettingMove = true;
        while(gettingMove){
            mv = -1;
            System.out.println("");
            System.out.println("Where do you want to move?");
            String ans = sc.next();
            int answer = -1;
            if(ans.toLowerCase().equals("quit")) return 21;
            try{
                answer = Integer.parseInt(ans);
            }
            catch(NumberFormatException e){
                System.out.println("Please git a valid input");
                continue;
            }
            if(answer == 7 || answer == 8 || answer == 9) mv = answer - 7;
            else if(answer == 4 || answer == 5 || answer == 6) mv = answer - 1;
            else if(answer == 1 || answer == 2 || answer == 3) mv = answer + 5;
            gettingMove = false;
        }
        return mv;
    }
    
    /**
     * A method for getting the player's Othelo move
     * 
     * @param board the game board (a 2D String array)
     * @return an integer array containing the coordinates of their move
     */
    public int[] oPlay(String[][] board){
        int[] mv = new int[2];
        boolean gettingMove = true;
        while(gettingMove){
            mv = new int[2];
            System.out.println("");
            System.out.println("Where do you want to move?");
            String ans = sc.next();
            String row = "";
            String column = "";
            int a = -1;
            if(ans.length() < 2 || ans.length() > 4){
                System.out.println("Please give a valid input");
                continue;
            }
            if(ans.toLowerCase().equals("quit")){
                mv[0] = 21;
                mv[1] = 21;
                gettingMove = false;
                continue;
            }
            try{
                row = ans.substring(0,1);
                column = ans.substring(1,2);
                mv[0] = Integer.parseInt(row);
                mv[1] = Integer.parseInt(column);
            }
            catch(NumberFormatException e){
                System.out.println("Please give a valid input");
                continue;
            }
            if(!validMove(board,mv)){
                System.out.println("Not a valid move");
                continue;
            }
            if(validMove(board,mv)){
                gettingMove = false;
            }
        }
        return mv;
    }
    
    /**
     * A method for checking that a move is valid on a 1D board
     */
    public boolean validMove(String[] board, int move){
        if(board[move].equals(" ")) return true;
        return false;
    }

    /**
     * A method for checking that a move is valid on a 2D board
     */
    public boolean validMove(String[][] board, int[] move){
        if(board[move[0]][move[1]].equals(" ")) return true;
        return false;
    }
    
    /**
     * A methdo for printing the player's stats for a given game
     * 
     * @param game a String for the name of the game
     */
    public void printStats(String game){
        System.out.println("");
        System.out.println("");
        if(game.equals("t")) System.out.println("TicTacToe Game Stats:");
        if(game.equals("o")) System.out.println("Othelo Game Stats:");
        if(game.equals("m")) System.out.println("MineSweeper Game Stats:");
        System.out.println("GamesPlayed: " + this.gamesPlayed(game));
        System.out.println("Wins: " + this.wins(game));
        System.out.println("Losses: " + this.losses(game));
        if(!game.equals("m")) System.out.println("Ties: " + this.ties(game));
        if(this.losses(game) == 0){
            System.out.println("Win/Loss Ratio: " + this.wins(game));
        }
        else{
            double ratio = this.wins(game) / this.losses(game);
            System.out.println("Win/Loss Ratio: " + ratio);
        }
        System.out.println("");
    }

    /** 
     * A method for incrementing the win stat of a given game
     */
    public void won(String game){
        if(game.equals("t")) this.tWins++;
        if(game.equals("o")) this.oWins++;
        if(game.equals("m")) this.mWins++;
    }

    /** 
     * A method for incrementing the loss stat of a given game
     */
    public void lost(String game){
        if(game.equals("t")) this.tLosses++;
        if(game.equals("o")) this.oLosses++;
        if(game.equals("m")) this.mLosses++;
    }

    /** 
     * A method for incrementing the tie stat of a given game
     */
    public void tied(String game){
        if(game.equals("t")) this.tTies++;
        if(game.equals("o")) this.oTies++;
    }

    /** 
     * A method for incrementing the number of games a given game played
     */
    public void played(String game){
        if(game.equals("t")) this.tGamesPlayed++;
        if(game.equals("o")) this.oGamesPlayed++;
        if(game.equals("m")) this.mGamesPlayed++;
    }
    
    /** 
     * A method for getting the player's username
     *
     * @return a String: the player's username
     */
    public String getUsername(){
        return this.username;
    }
    /*public boolean checkPassword(String p){
        return (this.password.equals(p));
    }*/

    /** 
     * A method for getting the wins of a given game
     *
     * @param game a String representing the game
     * @return an integer representing the number of wins in a given game
     */
    public int wins(String game){
        if(game.equals("t")) return this.tWins;
        if(game.equals("o")) return this.oWins;
        if(game.equals("m")) return this.mWins;
        return 0;
    }

    /** 
     * A method for getting the losses of a given game
     *
     * @param game a String representing the game
     * @return an integer representing the number of losses in a given game
     */
    public int losses(String game){
        if(game.equals("t")) return this.tLosses;
        if(game.equals("o")) return this.oLosses;
        if(game.equals("m")) return this.mLosses;
        return 0;
    }

    /** 
     * A method for getting the ties of a given game
     *
     * @param game a String representing the game
     * @return an integer representing the number of ties in a given game
     */
    public int ties(String game){
        if(game.equals("t")) return this.tTies;
        if(game.equals("o")) return this.oTies;
        return 0;
    }

    /** 
     * A method for getting the number of games played of a given game
     *
     * @param game a String representing the game
     * @return an integer representing the number of games played in a given game
     */
    public int gamesPlayed(String game){
        if(game.equals("t")) return this.tGamesPlayed;
        if(game.equals("o")) return this.oGamesPlayed;
        if(game.equals("m")) return this.mGamesPlayed;
        return 0;
    }
}
