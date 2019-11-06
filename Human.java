
/**
 * Write a description of class Human here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Human
{
    String piece;
    private String username;
    //private String password;
    private int tWins;
    private int tLosses;
    private int tTies;
    private int tGamesPlayed;
    private int oWins;
    private int oLosses;
    private int oTies;
    private int oGamesPlayed;
    
    Scanner sc = new Scanner(System.in);
    
    public Human(){};
    
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
    }
    
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
    
    public int[] oPlay(){
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
        }
        return mv;
    }
    
    public boolean validMove(String[] board, int move){
        if(board[move].equals(" ")) return true;
        return false;
    }
    
    public void printStats(String game){
        if(game.equals("t")){
            System.out.println("TicTacToe Game Stats:");
            System.out.println("Wins: " + this.tWins);
            System.out.println("Losses: " + this.tLosses);
            System.out.println("Ties: " + this.tTies);
            if(this.tLosses == 0){
                System.out.println("Win/Loss Ratio: " + this.tWins);
            }
            else{
                double ratio = this.tWins / this.tLosses;
                System.out.println("Win/Loss Ratio: " + ratio);
            }
        }
        if(game.equals("o")){
            System.out.println("Othelo Game Stats:");
            System.out.println("Wins: " + this.oWins);
            System.out.println("Losses: " + this.oLosses);
            System.out.println("Ties: " + this.oTies);
            if(this.oLosses == 0){
                System.out.println("Win/Loss Ratio: " + this.oWins);
            }
            else{
                double ratio = this.oWins / this.oLosses;
                System.out.println("Win/Loss Ratio: " + ratio);
            }
        }
        System.out.println("");
    }
    public void won(String game){
        if(game.equals("t")) this.tWins++;
        if(game.equals("o")) this.oWins++;
    }
    public void lost(String game){
        if(game.equals("t")) this.tLosses++;
        if(game.equals("o")) this.oLosses++;
    }
    public void tied(String game){
        if(game.equals("t")) this.tTies++;
        if(game.equals("o")) this.oTies++;
    }
    
    public String getUsername(){
        return this.username;
    }
    /*public boolean checkPassword(String p){
        return (this.password.equals(p));
    }*/
    public int Wins(String game){
        if(game.equals("t")) return this.tWins;
        if(game.equals("o")) return this.oWins;
        return 0;
    }
    public int Losses(String game){
        if(game.equals("t")) return this.tLosses;
        if(game.equals("o")) return this.oLosses;
        return 0;
    }
    public int tTies(String game){
        if(game.equals("t")) return this.tTies;
        if(game.equals("o")) return this.oTies;
        return 0;
    }
    public int tGamesPlayed(String game){
        if(game.equals("t")) return this.tGamesPlayed;
        if(game.equals("o")) return this.oGamesPlayed;
        return 0;
    }
}
