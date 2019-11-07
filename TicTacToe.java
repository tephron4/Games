
/**
 * Write a description of class TicTacToe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class TicTacToe
{
    private String[] board = new String[9];
    private static String[] exampleBoard = {"7","8","9","4","5","6","1","2","3"};
    Human th;
    Computer tc;
    Scanner s = new Scanner(System.in);
    private GameInterface g = new GameInterface();
    private String turn = "";
    
    public TicTacToe(Human th, Computer tc){
        this.th = th;
        this.tc = tc;
    }
    
    public void play(){
        System.out.println("Welcome to TicTacToe " + this.th.getUsername() + "!");
        System.out.println("");
        boolean game = true;
        while(game){
            initializeBoard();
            //printBoard(board);
            if(initializePieces().equals("quit")) return;
            //System.out.println("Here are the coordinates for the board:");
            //printBoard(exampleBoard);
            //System.out.println("Like a number pad or calculator");
            //System.out.println("");
            boolean playing = true;
            this.th.played("o");
            while(playing){
                switchTurn();
                boolean moving = true;
                int move = -1;
                if(turn.equals(this.th.piece)){
                    System.out.print('\u000C');
                    printBoard(board);
                    System.out.println("Example Board:");
                    printBoard(exampleBoard);
                }
                while(moving){    
                    move = makeMove(this.th,this.tc);
                    if(move == 21){
                        System.out.println("Are you sure you want to quit? (yes/no)");
                        String ans = s.next().toLowerCase();
                        if(ans.equals("yes")) return;
                        else{
                            continue;
                        }
                    }
                    else{
                        moving = false;
                    }
                }
                updateBoard(move);
                if(winner(turn)){
                    printBoard(board);
                    playing = false;
                    continue;
                }
                if(fullBoard()){
                    System.out.print('\u000C');
                    System.out.println("You tied");
                    printBoard(board);
                    this.th.tied("t");
                    playing = false;
                }
            }
            System.out.println("");
            this.th.printStats("t");
            if(!playAgain()){
                System.out.print('\u000C');
                System.out.println("Thanks for playing!");
                game = false;
                continue;
            }
            turn = "";
            System.out.print('\u000C');
        }
    }
    
    public boolean playAgain(){
        System.out.println("");
        System.out.println("Would you like to play another game of TicTacToe? (yes/no)");
        String ans = s.next().toLowerCase();
        if(ans.substring(0,1).equals("n") || ans.equals("quit") || ans.equals("back")) return false;
        return true;
    }
    
    public void initializeBoard(){
        for(int i=0;i<board.length;i++){
            board[i] = " ";
        }
    }
    
    public String initializePieces(){
        boolean ask = true;
        while(ask){
            System.out.println("What piece would you like to be? (X or O)");
            String ans = s.next().toLowerCase();
            if(g.quit(ans) || ans.equals("back")){
                return "quit";
            }
            else if(ans.equals("x")){
                this.th.piece = "X";
                this.tc.piece = "O";
                ask = false;
            }
            else if(ans.equals("o")){
                this.th.piece = "O";
                this.tc.piece = "X";
                ask = false;
            }
        }
        return "";
    }
    
    public void switchTurn(){
        if(turn.equals("")){
            int x = (int) (2.0 * Math.random());
            //System.out.println(x);
            if(x == 0) turn = "X";
            if(x == 1) turn = "O";
            //System.out.println("Turn set to " + turn);
        }
        else if(turn.equals("X")){
            turn = "O";
            //System.out.println("Turn switched to " + turn);
        }
        else if(turn.equals("O")){
            turn = "X";
            //System.out.println("Turn switched to " + turn);
        }
    }
    
    public int makeMove(Human hp, Computer cp){
        int mv = -1;
        boolean tf = true;
        while(tf){
            int x = -1;
            if(turn.equals(hp.piece)){
                x = hp.tPlay(board);
                if(x == 21) return 21;
                if(!validMove(x)){
                    System.out.print('\u000C');
                    System.out.println("Not a valid move");
                    printBoard(board);
                    continue;
                }
                mv = x;
                tf = false;
                continue;
            }
            if(turn.equals(cp.piece)){
                //System.out.println("Getting computer move");
                mv = cp.tMove(board,cp.piece,hp.piece);
                tf = false;
            }
        }
        return mv;
    }
    
    public void updateBoard(int x){
        board[x] = turn;
    }
    
    public void printBoard(String[] board){
        System.out.println("");
        System.out.println("");
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---|---|---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---|---|---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println("");
        System.out.println("");
    }
    
    public boolean winner(String piece){
        if(hasWon(piece)){
            if(piece.equals(this.th.piece)){
                System.out.println("You Won!");
                this.th.won("t");
                return true;
            }
            if(piece.equals(this.tc.piece)){
                System.out.println("You Lost...");
                this.th.lost("t");
                return true;
            }
        }
        return false;
    }
    
    public boolean fullBoard(){
        for(int i=0;i<board.length;i++){
            if(board[i] == " ") return false;
        }
        return true;
    }
    
    public boolean hasWon(String piece){
        int count;
        //check the columns
        // System.out.println("Checking columns");
        for(int i=0;i<3;i++){
            count = 0;
            for(int j=i;j<board.length;j+=3){
                if(board[j].equals(piece)){
                    // System.out.println(j + ": " + board[j]);
                    count++;
                }
            }
            if(count == 3){
                // System.out.println("Won on column " + i);
                return true;
            }
        }
        //check rows
        // System.out.println("Checking rows");
        for(int i=0;i<board.length;i+=3){
            count = 0;
            for(int j=i;j<i+3;j++){
                if(board[j].equals(piece)){
                    // System.out.println(j + ": " + board[j]);
                    count++;
                }
            }
            if(count == 3){
                // System.out.println("Won on row " + i);
                return true;
            }
        }
        //check diagonals
        //first diag: down to the right
        //System.out.println("Checking diag down to the right");
        count = 0;
        for(int i=0;i<board.length;i+=4){
            if(board[i].equals(piece)){
                //System.out.println(i + ": " + board[i]);
                count++;
            }
        }   
        if(count == 3){
            //System.out.println("Won on this diag (down to the right)");
            return true;
        }
        //second diag: up to the left
        //System.out.println("Checking diag down to the left");
        count = 0;
        for(int i=2;i<board.length-1;i+=2){
            if(board[i].equals(piece)){
                //System.out.println(i + ": " + board[i]);
                count++;
            }
        }   
        if(count == 3){
            //System.out.println("Won on this diag (down to the left)");
            return true;
        }
        return false;
    }
    
    public boolean validMove(int m){
        if(m < 0 || m > board.length) return false;
        if(board[m] != " ") return false;
        return true;
    }
}
