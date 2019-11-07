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
        System.out.println("Welcome to Othelo " + this.oh.getUsername() + "!");
        boolean playing = true;
        while(playing){
            if(initializePieces().equals("quit")) return;
            initializeBoard();
            boolean inGame = true;
            this.oh.played("o");
            while(inGame){
                switchTurn();
                if(turn.equals(this.oh.piece)){
                    printBoard(board);
                }   
                boolean moving = true;
                int[] move = new int[2];
                while(moving){
                    move = getMove();
                    if(move[0] == 21){
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
                board = updateBoard(board, turn, move);
                if(fullBoard()){
                    System.out.print('\u000C');
                    printBoard(board);
                    String winner = winner();
                    if(winner.equals("human")){
                        this.oh.won("o");
                        System.out.println("You won!");
                    }
                    if(winner.equals("computer")){
                        this.oh.lost("o");
                        System.out.println("You lost...");
                    }
                    if(winner.equals("tie")){
                        this.oh.tied("o");
                        System.out.println("You tied.");
                    }
                    System.out.println("");
                    inGame = false;
                }
            }
            this.oh.printStats("o");
            if(!playAgain()){
                System.out.print('\u000C');
                System.out.println("Thanks for playing " + this.oh.getUsername() + "!");
                playing = false;
                continue;
            }   
            turn = "";
            System.out.print('\u000C');
        }
    }
    
    public boolean playAgain(){
        System.out.println("");
        System.out.println("Would you like to play another game of Othelo? (yes/no)");
        String ans = s.next().toLowerCase();
        if(ans.substring(0,1).equals("n") || ans.equals("quit") || ans.equals("back")) return false;
        return true;
    }
    
    public void initializeBoard(){
        for(int i=0;i<board[0].length;i++){
            for(int j=0;j<board[0].length;j++){
                if((i==4&j==4)||(i==5&&j==5)){
                    board[i][j] = "X";
                }   
                else if((i==4&j==5)||(i==5&&j==4)){
                    board[i][j] = "O";
                }
                else{
                    board[i][j] = " ";
                }
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
        if(turn.equals(this.oh.piece)){
            mv = this.oh.oPlay(board);
        }
        if(turn.equals(this.oc.piece)){
            mv = this.oc.oPlay(board,this.oc.piece,this.oh.piece);
        }
        return mv;
    }
    
    public String[][] updateBoard(String[][] board, String piece, int[] move){
        int bl = board[0].length;
        board[move[0]][move[1]] = piece;
        int r = move[0];
        int c = move[1];
        int[] end = new int[]{-1,-1};
        // check if should flip row
        // first the postitive direction
        if(c != bl-1){
            for(int col=c+1;col<bl;col++){
                if(board[r][col] == " ") break;
                if(board[r][col] == piece){
                    end = new int[]{r,col};
                    break;
                }
            }
        }
        if(end[0] != -1) board = flip(board,piece,move,end);
        end = new int[]{-1,-1};
        // now the negative direction
        if(c != 0){
            for(int col=c-1;col>=0;col--){
                if(board[r][col] == " ") break;
                if(board[r][col] == piece){
                    end = new int[]{r,col};
                    break;
                }
            }
        }
        if(end[0] != -1) board = flip(board,piece,move,end);
        end = new int[]{-1,-1};
        // check if should flip column
        // first the postive direction
        if(r != bl-1){
            for(int row=r+1;row<bl;row++){
                if(board[row][c] == " ") break;
                if(board[row][c] == piece){
                    end = new int[]{row,c};
                    break;
                }
            }
        }
        if(end[0] != -1) board = flip(board,piece,move,end);
        end = new int[]{-1,-1};
        // now the negative direction
        if(r != 0){
            for(int row=r-1;row>=0;row--){
                if(board[row][c] == " ") break;
                if(board[row][c] == piece){
                    end = new int[]{row,c};
                    break;
                }
            }
        }
        if(end[0] != -1) board = flip(board,piece,move,end);
        end = new int[]{-1,-1};
        // check if should flip diagonal
        // first down right
        if(r != bl-1 && c != bl-1){
            for(int row=r+1;row<bl;row++){
                for(int col=c+1;col<bl;col++){
                    if(board[row][col] == " ") break;
                    if(board[row][col] == piece){
                        end = new int[]{row,col};
                        break;
                    }
                }
            }
        }
        if(end[0] != -1) board = flip(board,piece,move,end);
        end = new int[]{-1,-1};
        // now down left
        if(r != bl-1 && c != 0){
            for(int row=r+1;row<bl;row++){
                for(int col=c-1;col>=0;col--){
                    if(board[row][col] == " ") break;
                    if(board[row][col] == piece){
                        end = new int[]{row,col};
                        break;
                    }
                }
            }
        }
        if(end[0] != -1) board = flip(board,piece,move,end);
        end = new int[]{-1,-1};
        // now up right
        if(r != 0 && c != bl-1){
            for(int row=r-1;row>=0;row--){
                for(int col=c+1;col<bl;col++){
                    if(board[row][col] == " ") break;
                    if(board[row][col] == piece){
                        end = new int[]{row,col};
                        break;
                    }
                }
            }
        }
        if(end[0] != -1) board = flip(board,piece,move,end);
        end = new int[]{-1,-1};
        // now up left
        if(r != 0 && c != 0){
            for(int row=r-1;row>=0;row--){
                for(int col=c-1;col>=0;col--){
                    if(board[row][col] == " ") break;
                    if(board[row][col] == piece){
                        end = new int[]{row,col};
                        break;
                    }
                }
            }
        }
        if(end[0] != -1) board = flip(board,piece,move,end);
        return board;
    }
    
    public String[][] flip(String[][] board, String piece, int[] start, int[] end){
        if(start[0] == end[0]){
            for(int col=Math.min(start[1],end[1]);col<=Math.max(start[1],end[1]);col++){
                board[start[0]][col] = piece;
            }
            return board;
        }
        int[] first = new int[2];
        int[] last = new int[2];
        if(start[0] < end[0]){
            first = start;
            last = end;
        }
        if(start[0] > end[0]){
            first = end;
            last = start;
        }
        if(first[1] < last[1]){
            for(int row=first[0];row<=last[0];row++){
                for(int col=first[1];col<=last[1];col++){
                    board[row][col] = piece;
                }
            }
            return board;
        }
        if(first[1] > last[1]){
            for(int row=first[0];row<=last[0];row++){
                for(int col=first[1];col>=last[1];col--){
                    board[row][col] = piece;
                }
            }
            return board;
        }
        return board;
    }
    
    public boolean fullBoard(){
        for(int i=0;i<board[0].length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == " ") return false;
            }
        }
        return true;
    }
    
    public String winner(){
        int hScore = score(board, this.oh.piece);
        int cScore = score(board, this.oc.piece);
        if(hScore > cScore) return "human";
        if(cScore > hScore) return "computer";
        else return "tie";
    }
    
    public int score(String[][] board, String piece){
        int count = 0;
        for(int i=0;i<board[0].length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == piece) count++; 
            }
        }
        return count;
    }
    
    public void printBoard(String[][] board){
        System.out.println("");
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
        System.out.println("");
    }
    
    public boolean validMove(int[] move){
        if(board[move[0]][move[1]] != " ") return false;
        return true;
    }
}