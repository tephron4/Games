
/**
 * Classic game of Othelo
 *
 * @author (Tobias Ephron)
 * @version (Version 1.0 Nov. 5, 2019)
 */
import java.util.*;
public class Othelo
{
    Scanner s = new Scanner(System.in);
    
    private String[][] board = new String[10][10];
    Human oh;
    Computer oc;
    String turn = "";
    
    public Othelo(Human oh, Computer oc){
        this.oh = oh;
        this.oc = oc;
    }
    
    public void play(){
        
    }
    
    public void initializeBoard(){
        for(int i=0;i<board[0].length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j] = " ";
            }
        }
    }
    
    public String initializePieces(){
        boolean choosing = true;
        while(choosing){
            System.out.println("What piece would you like to be? (X or O)");
            String ans = s.next().toLowerCase();
            if(ans.length() == 0){
                System.out.print('\u000C');
                continue;
            }
            if(ans.equals("x")){
                this.oh.piece = "X";
                this.oc.piece = "O";
                choosing = false;
            }
            if(ans.equals("o")){
                this.oh.piece = "O";
                this.oc.piece = "X";
                choosing = false;
            }
            if(ans.equals("quit")){
                return "quit";
            }
            else{
                System.out.print('\u000C');
                System.out.println("Please give a valid input");
                System.out.println("");
                continue;
            }
        }
        return "";
    }
    
    public void switchTurn(){
        if(turn.equals("")){
            int x = (int) (Math.random() * 2.0);
            if(x == 0) turn = "X";
            if(x == 1) turn = "O";
        }
        if(turn.equals("X")) turn = "O";
        if(turn.equals("O")) turn = "X";
    }
    
    public int[] getMove(){
        int[] mv = new int[2];
        
        return mv;
    }
    
    public void printBoard(String[][] board){
        
    }
    
    public void updateBoard(int[] move){
        
    }
    
    public boolean fullBoard(){
     
        return true;
    }
    
    public boolean hasWinner(){
        
        return false;
    }
    
    public boolean hasWon(String piece){
        
        return false;
    }
    
    public boolean validMove(int[] move){
        
        return true;
    }
}
