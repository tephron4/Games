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
    
    public Othelo(Human oh, Computer oc, String[][] board){
        this.oh = oh;
        this.oc = oc;
        this.board = board;
    }
    
    public String[][] getBoard(){
        return this.board;
    }
    
    public void setBoard(String[][] b){
        this.board = b;
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
                this.switchTurn();
                if(turn.equals(this.oh.piece)){
                    this.printBoard();
                }   
                boolean moving = true;
                int[] move = new int[2];
                while(moving){
                    move = this.getMove();
                    if(move[0] == 21){
                        System.out.println("Are you sure you want to quit? (yes/no)");
                        String ans = s.next().toLowerCase();
                        if(ans.equals("yes")) return;
                        else{
                            continue;
                        }
                    }   
                    moving = false;
                }   
                this.updateBoard(turn, move);
                if(this.fullBoard()){
                    //System.out.print('\u000C');
                    this.printBoard();
                    String winner = this.winner();
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
                //System.out.print('\u000C');
                System.out.println("Thanks for playing " + this.oh.getUsername() + "!");
                playing = false;
                continue;
            }   
            turn = "";
            //System.out.print('\u000C');
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
        for(int i=0;i<this.board[0].length;i++){
            for(int j=0;j<this.board[0].length;j++){
                if((i==4&j==4)||(i==5&&j==5)){
                    this.board[i][j] = "X";
                }   
                else if((i==4&j==5)||(i==5&&j==4)){
                    this.board[i][j] = "O";
                }
                else{
                    this.board[i][j] = " ";
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
                //System.out.print('\u000C');
                continue;
            }
            else if(ans.equals("x")){
                this.oh.piece = "X";
                this.oc.piece = "O";
                choosing = false;
            }
            else if(ans.equals("o")){
                this.oh.piece = "O";
                this.oc.piece = "X";
                choosing = false;
            }
            else if(ans.equals("quit")){
                return "quit";
            }
            else{
                //System.out.print('\u000C');
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
        else if(turn.equals("X")) turn = "O";
        else if(turn.equals("O")) turn = "X";
        System.out.println("Switched turn to: " + turn);
    }
    
    public int[] getMove(){
        int[] mv = new int[2];
        if(turn.equals(this.oh.piece)){
            System.out.println("Getting human move");
            mv = this.oh.oPlay(this.board);
        }
        if(turn.equals(this.oc.piece)){
            System.out.println("Getting computer move");
            mv = this.oc.oPlay(this.board,this.oc.piece,this.oh.piece);
        }
        return mv;
    }
    
    public void updateBoard(String piece, int[] move){
        int bl = this.board[0].length;
        this.board[move[0]][move[1]] = piece;
        int r = move[0];
        int c = move[1];
        int[] end = new int[]{-1,-1};
        // check if should flip row
        // first the postitive direction
        if(c != bl-1){
            for(int col=c+1;col<bl;col++){
                if(this.board[r][col] == " ") break;
                if(this.board[r][col] == piece){
                    end = new int[]{r,col};
                    break;
                }
            }
        }
        if(end[0] != -1){
            //System.out.println("Flipping from [" + move[0] + "," + move[1] + "] to [" + end[0] + "," + end[1] + "]");
            this.flip(piece,move,end);
        }
        end = new int[]{-1,-1};
        // now the negative direction
        if(c != 0){
            for(int col=c-1;col>=0;col--){
                if(this.board[r][col] == " ") break;
                if(this.board[r][col] == piece){
                    end = new int[]{r,col};
                    break;
                }
            }
        }
        if(end[0] != -1){
            //System.out.println("Flipping from [" + move[0] + "," + move[1] + "] to [" + end[0] + "," + end[1] + "]");
            this.flip(piece,move,end);
        }
        end = new int[]{-1,-1};
        // check if should flip column
        // first the postive direction
        if(r != bl-1){
            for(int row=r+1;row<bl;row++){
                if(this.board[row][c] == " ") break;
                if(this.board[row][c] == piece){
                    end = new int[]{row,c};
                    break;
                }
            }
        }
        if(end[0] != -1){
            //System.out.println("Flipping from [" + move[0] + "," + move[1] + "] to [" + end[0] + "," + end[1] + "]");
            this.flip(piece,move,end);
        }
        end = new int[]{-1,-1};
        // now the negative direction
        if(r != 0){
            for(int row=r-1;row>=0;row--){
                if(this.board[row][c] == " ") break;
                if(this.board[row][c] == piece){
                    end = new int[]{row,c};
                    break;
                }
            }
        }
        if(end[0] != -1){
            //System.out.println("Flipping from [" + move[0] + "," + move[1] + "] to [" + end[0] + "," + end[1] + "]");
            this.flip(piece,move,end);
        }
        end = new int[]{-1,-1};
        // check if should flip diagonal
        // first down right
        if(r != bl-1 && c != bl-1){
            for(int row=r+1;row<bl;row++){
                for(int col=c+1;col<bl;col++){
                    if(this.board[row][col] == " ") break;
                    if(this.board[row][col] == piece){
                        end = new int[]{row,col};
                        break;
                    }
                }
            }
        }
        if(end[0] != -1){
            //System.out.println("Flipping from [" + move[0] + "," + move[1] + "] to [" + end[0] + "," + end[1] + "]");
            this.flip(piece,move,end);
        }
        end = new int[]{-1,-1};
        // now down left
        if(r != bl-1 && c != 0){
            for(int row=r+1;row<bl;row++){
                for(int col=c-1;col>=0;col--){
                    if(this.board[row][col] == " ") break;
                    if(this.board[row][col] == piece){
                        end = new int[]{row,col};
                        break;
                    }
                }
            }
        }
        if(end[0] != -1){
            //System.out.println("Flipping from [" + move[0] + "," + move[1] + "] to [" + end[0] + "," + end[1] + "]");
            this.flip(piece,move,end);
        }
        end = new int[]{-1,-1};
        // now up right
        if(r != 0 && c != bl-1){
            for(int row=r-1;row>=0;row--){
                for(int col=c+1;col<bl;col++){
                    if(this.board[row][col] == " ") break;
                    if(this.board[row][col] == piece){
                        end = new int[]{row,col};
                        break;
                    }
                }
            }
        }
        if(end[0] != -1){
            //System.out.println("Flipping from [" + move[0] + "," + move[1] + "] to [" + end[0] + "," + end[1] + "]");
            this.flip(piece,move,end);
        }
        end = new int[]{-1,-1};
        // now up left
        if(r != 0 && c != 0){
            for(int row=r-1;row>=0;row--){
                for(int col=c-1;col>=0;col--){
                    if(this.board[row][col] == " ") break;
                    if(this.board[row][col] == piece){
                        end = new int[]{row,col};
                        break;
                    }
                }
            }
        }
        if(end[0] != -1){
            //System.out.println("Flipping from [" + move[0] + "," + move[1] + "] to [" + end[0] + "," + end[1] + "]");
            this.flip(piece,move,end);
        }
    }
    
    public void flip(String piece, int[] start, int[] end){
        if(start[0] == end[0]){
            for(int col=Math.min(start[1],end[1]);col<=Math.max(start[1],end[1]);col++){
                //System.out.println("flipped [" + start[0] + "," + col + "]");
                this.board[start[0]][col] = piece;
            }
        }
        if(start[1] == end[1]){
            for(int row=Math.min(start[0],end[0]);row<=Math.max(start[0],end[0]);row++){
                //System.out.println("flipped [" + row + "," + start[1] + "]");
                this.board[row][start[1]] = piece;
            }
        }
        int[] first = new int[2];
        int[] last = new int[2];
        if(start[0] < end[0]){
            first = start;
            last = end;
        }
        else if(start[0] > end[0]){
            first = end;
            last = start;
        }
        if(first[1] < last[1]){
            int row = first[0];
            int col = first[1];
            while(row<=last[0] && col<=last[0]){
                //System.out.println("flipped [" + start[0] + "," + col + "]");
                this.board[row][col] = piece;
                row++;
                col++;
            }
            /*for(int row=first[0];row<=last[0];row++){
                for(int col=first[1];col<=last[1];col++){
                    System.out.println("flipped [" + start[0] + "," + col + "]");
                    board[row][col] = piece;
                }
            }*/
        }
        if(first[1] > last[1]){
            int row = first[0];
            int col = first[1];
            while(row<=last[0] && col>=last[1]){
                //System.out.println("flipped [" + start[0] + "," + col + "]");
                this.board[row][col] = piece;
                row++;
                col--;
            }
            /*for(int row=first[0];row<=last[0];row++){
                for(int col=first[1];col>=last[1];col--){
                    System.out.println("flipped [" + start[0] + "," + col + "]");
                    board[row][col] = piece;
                }
            }*/
        }
    }
    
    public boolean fullBoard(){
        for(int i=0;i<this.board[0].length;i++){
            for(int j=0;j<this.board[0].length;j++){
                if(this.board[i][j] == " ") return false;
            }
        }
        return true;
    }
    
    public String winner(){
        int hScore = score(this.oh.piece);
        int cScore = score(this.oc.piece);
        if(hScore > cScore) return "human";
        if(cScore > hScore) return "computer";
        else return "tie";
    }
    
    public int score(String piece){
        int count = 0;
        for(int i=0;i<this.board[0].length;i++){
            for(int j=0;j<this.board[0].length;j++){
                if(this.board[i][j] == piece) count++; 
            }
        }
        return count;
    }
    
    public void printBoard(){
        System.out.println("");
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        System.out.println("0 " + this.board[0][0] + "|" + this.board[0][1] + "|" + this.board[0][2] + "|" + this.board[0][3] + "|"
                                + this.board[0][4] + "|" + this.board[0][5] + "|" + this.board[0][6] + "|" + this.board[0][7] + "|"
                                + this.board[0][8] + "|" +this.board[0][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-+-");
        System.out.println("1 " +this.board[1][0] + "|" +this.board[1][1] + "|" +this.board[1][2] + "|" +this.board[1][3] + "|"
                                +this.board[1][4] + "|" +this.board[1][5] + "|" +this.board[1][6] + "|" +this.board[1][7] + "|"
                                +this.board[1][8] + "|" +this.board[1][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-+-");
        System.out.println("2 " +this.board[2][0] + "|" +this.board[2][1] + "|" +this.board[2][2] + "|" +this.board[2][3] + "|"
                                +this.board[2][4] + "|" +this.board[2][5] + "|" +this.board[2][6] + "|" +this.board[2][7] + "|"
                                +this.board[2][8] + "|" +this.board[2][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-+-");
        System.out.println("3 " +this.board[3][0] + "|" +this.board[3][1] + "|" +this.board[3][2] + "|" +this.board[3][3] + "|"
                                +this.board[3][4] + "|" +this.board[3][5] + "|" +this.board[3][6] + "|" +this.board[3][7] + "|"
                                +this.board[3][8] + "|" +this.board[3][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-+-");
        System.out.println("4 " +this.board[4][0] + "|" +this.board[4][1] + "|" +this.board[4][2] + "|" +this.board[4][3] + "|"
                                +this.board[4][4] + "|" +this.board[4][5] + "|" +this.board[4][6] + "|" +this.board[4][7] + "|"
                                +this.board[4][8] + "|" +this.board[4][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-+-");
        System.out.println("5 " +this.board[5][0] + "|" +this.board[5][1] + "|" +this.board[5][2] + "|" +this.board[5][3] + "|"
                                +this.board[5][4] + "|" +this.board[5][5] + "|" +this.board[5][6] + "|" +this.board[5][7] + "|"
                                +this.board[5][8] + "|" +this.board[5][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-+-");
        System.out.println("6 " +this.board[6][0] + "|" +this.board[6][1] + "|" +this.board[6][2] + "|" +this.board[6][3] + "|"
                                +this.board[6][4] + "|" +this.board[6][5] + "|" +this.board[6][6] + "|" +this.board[6][7] + "|"
                                +this.board[6][8] + "|" +this.board[6][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-+-");
        System.out.println("7 " +this.board[7][0] + "|" +this.board[7][1] + "|" +this.board[7][2] + "|" +this.board[7][3] + "|"
                                +this.board[7][4] + "|" +this.board[7][5] + "|" +this.board[7][6] + "|" +this.board[7][7] + "|"
                                +this.board[7][8] + "|" +this.board[7][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-+-");
        System.out.println("8 " +this.board[8][0] + "|" +this.board[8][1] + "|" +this.board[8][2] + "|" +this.board[8][3] + "|"
                                +this.board[8][4] + "|" +this.board[8][5] + "|" +this.board[8][6] + "|" +this.board[8][7] + "|"
                                +this.board[8][8] + "|" +this.board[8][9]);
        System.out.println("  -+-+-+-+-+-+-+-+-+-");
        System.out.println("9 " +this.board[9][0] + "|" +this.board[9][1] + "|" +this.board[9][2] + "|" +this.board[9][3] + "|"
                                +this.board[9][4] + "|" +this.board[9][5] + "|" +this.board[9][6] + "|" +this.board[9][7] + "|"
                                +this.board[9][8] + "|" +this.board[9][9]);
        System.out.println("");
    }
    
    public boolean validMove(int[] move){
        if(this.board[move[0]][move[1]] != " ") return false;
        return true;
    }
}