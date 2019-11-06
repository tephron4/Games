
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
    
    public void printBoard(String[][] board){
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        System.out.println("0 " + board[0][0] + "|" + board[0][1] + "|" + board[0][2] + "|" + board[0][3] + "|"
                                + board[0][4] + "|" + board[0][5] + "|" + board[0][6] + "|" + board[0][7] + "|"
                                + board[0][8] + "|" + board[0][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-");
        System.out.println("1 " + board[1][0] + "|" + board[1][1] + "|" + board[1][2] + "|" + board[1][3] + "|"
                                + board[1][4] + "|" + board[1][5] + "|" + board[1][6] + "|" + board[1][7] + "|"
                                + board[1][8] + "|" + board[1][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-");
        System.out.println("2 " + board[2][0] + "|" + board[2][1] + "|" + board[2][2] + "|" + board[2][3] + "|"
                                + board[2][4] + "|" + board[2][5] + "|" + board[2][6] + "|" + board[2][7] + "|"
                                + board[2][8] + "|" + board[2][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-");
        System.out.println("3 " + board[3][0] + "|" + board[3][1] + "|" + board[3][2] + "|" + board[3][3] + "|"
                                + board[3][4] + "|" + board[3][5] + "|" + board[3][6] + "|" + board[3][7] + "|"
                                + board[3][8] + "|" + board[3][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-");
        System.out.println("4 " + board[4][0] + "|" + board[4][1] + "|" + board[4][2] + "|" + board[4][3] + "|"
                                + board[4][4] + "|" + board[4][5] + "|" + board[4][6] + "|" + board[4][7] + "|"
                                + board[4][8] + "|" + board[4][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-");
        System.out.println("5 " + board[5][0] + "|" + board[5][1] + "|" + board[5][2] + "|" + board[5][3] + "|"
                                + board[5][4] + "|" + board[5][5] + "|" + board[5][6] + "|" + board[5][7] + "|"
                                + board[5][8] + "|" + board[5][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-");
        System.out.println("6 " + board[6][0] + "|" + board[6][1] + "|" + board[6][2] + "|" + board[6][3] + "|"
                                + board[6][4] + "|" + board[6][5] + "|" + board[6][6] + "|" + board[6][7] + "|"
                                + board[6][8] + "|" + board[6][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-");
        System.out.println("7 " + board[7][0] + "|" + board[7][1] + "|" + board[7][2] + "|" + board[7][3] + "|"
                                + board[7][4] + "|" + board[7][5] + "|" + board[7][6] + "|" + board[7][7] + "|"
                                + board[7][8] + "|" + board[7][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-");
        System.out.println("8 " + board[8][0] + "|" + board[8][1] + "|" + board[8][2] + "|" + board[8][3] + "|"
                                + board[8][4] + "|" + board[8][5] + "|" + board[8][6] + "|" + board[8][7] + "|"
                                + board[8][8] + "|" + board[8][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-");
        System.out.println("9 " + board[9][0] + "|" + board[9][1] + "|" + board[9][2] + "|" + board[9][3] + "|"
                                + board[9][4] + "|" + board[9][5] + "|" + board[9][6] + "|" + board[9][7] + "|"
                                + board[9][8] + "|" + board[9][9]);
    }
    
    public int[] getMove(){
        int[] mv = new int[2];
        
        return mv;
    }
    
    public void updateBoard(String piece, int[] move){
        board[move[0]][move[1]] = piece;
    }
    
    public boolean fullBoard(){
        for(int i=0;i<board[0].length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == " ") return false;
            }
        }
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
