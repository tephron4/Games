
/**
 * Write a description of class Computer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Computer
{
    String piece;
    
    public Computer(String piece){
        this.piece = piece;
    }
    
    public boolean validMove(String[] board, int m){
        if(!board[m].equals(" ")){
            //System.out.println(m + " is not a valid move. board[" + m + "] = " + board[m]);
            return false;
        }
        //System.out.println(m + " is a valid move. board[" + m + "] = " + board[m]);
        return true;
    }
    
    public boolean validMove(String[][] board, int x, int y){
        if(board[x][y].equals(" ")) return true;
        return false;
    }
    
    public int[] oPlay(String[][] board, String cPiece, String hPiece){
        int[] move = new int[2];
        int highScore = 0;
        int bestRow = -1;
        int bestCol = -1;
        String[][] boardCopy;
        Othelo o = new Othelo(new Human(), new Computer(cPiece));
        for(int row=0;row<board[0].length;row++){
            boardCopy = board;
            for(int col=0;col<board[0].length;col++){
                if(validMove(board, row, col)){
                    boardCopy = o.updateBoard(boardCopy, piece, new int[]{row,col});
                    int score = o.score(board,cPiece);
                    if(score > highScore){
                        highScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }
        move = new int[]{bestRow,bestCol};
        return move;
    }
    
    /** 
     * Gets the computer's game move
     * 
     * @param board the game board
     * @param cp the computer's game piece
     * @param hp the human player's game piece
     * @return a length 2 array with coordinates for a move
     */
    public int tMove(String[] board, String cPiece, String hPiece){
        int mv = -1;
        //System.out.println("Checking if computer can win");
        int cWin = tCanWin(board,cPiece);
        if(cWin < board.length && cWin >= 0){
            mv = cWin;
            return mv;
        }
        //System.out.println("Checking if human can win");
        int hWin = tCanWin(board,hPiece);
        if(hWin < board.length && hWin >= 0){
            mv = hWin;
            return mv;
        }
        //System.out.println("Checking if middle is open");
        int openMiddle = tOpenMiddle(board);
        if(openMiddle < board.length && openMiddle >= 0){
            mv = openMiddle;
            return mv;
        }
        //System.out.println("Checking if a corner is open");
        int openCorner = tOpenCorner(board);
        if(openCorner < board.length && openCorner >= 0){
            mv = openCorner;
            return mv;
        }
        //System.out.println("Getting random move");
        mv = tRandomMove(board);
        return mv;
    }
    
    /** 
     * Generates a random game move    
     * 
     * @param board the game board
     * @return a length 2 array with coordinates for a move
     */
    public int tRandomMove(String[] board){
        int move = -1;
        boolean tf = true;
        while(tf){
            int x = (int) (Math.random() * board.length);
            if(!validMove(board,x)){
                move = x;
                continue;
            }
            //System.out.println("Valid move for computer");
            tf = false;
        }
        //System.out.println("Comp is making a random move");
        return move;
    }
    
    /**
     * Checks if a player can win with their next turn.
     * 
     * @param board the game board
     * @param piece the player's piece that you want to check for
     * @return an array containing the coordinates of where the player can move to win.
     */
    public int tCanWin(String[] board, String piece){
        int count;
        int mv = -1;
        //check the columns
        //System.out.println("Checking if " + piece + " can win");
        for(int i=0;i<3;i++){
            count = 0;
            mv = -1;
            for(int j=i;j<board.length;j+=3){
                if(board[j].equals(piece)) count++;
                //System.out.println("board[" + j + "] = " + board[j]);
                if(!board[j].equals(piece) && validMove(board, j)){
                    mv = j;
                }
            }
            if(count == 2 && mv != -1){
                //System.out.println(piece + " can win in column " + i);
                return mv;
            }
        }
        mv = -1;
        //check rows
        for(int i=0;i<board.length;i+=3){
            count = 0;
            mv = -1;
            for(int j=i;j<i+3;j++){
                if(board[j].equals(piece)) count++;
                //System.out.println("board[" + j + "] = " + board[j]);
                if(!board[j].equals(piece) && validMove(board, j)){
                    mv = j;
                }
            }
            if(count == 2 && mv != -1){
                //System.out.println(piece + " can win in row " + i);
                return mv;
            }
        }
        mv = -1;
        //check diagonals
        //first diag: down to the right
        count = 0;
        for(int i=0;i<board.length;i+=4){
            if(board[i].equals(piece)) count++;
            if(!board[i].equals(piece) && validMove(board,i)) mv = i;
        }   
        if(count == 2 && mv != -1){
            //System.out.println(piece + " can win in diag dow to right");
            return mv;
        }
        mv = -1;
        //second diag: up to the left
        count = 0;
        for(int i=2;i<board.length-1;i+=2){
            if(board[i].equals(piece)) count++;
            if(!board[i].equals(piece) && validMove(board,i)) mv = i;
        }   
        if(count == 2 && mv != -1){
            //System.out.println(piece + " can win in diag up to left");
            return mv;
        }
        //System.out.println(piece + " cannot win anywhere");
        mv = -1;
        return mv;
    }
    
    /**
     * Checks if the middle of the board is open.
     * 
     * @param board the game board
     * @return an array containing the coordinates of the middle if the middle is open.
     */
    public int tOpenMiddle(String[] board){
        if(validMove(board,4)){
            System.out.println("Middle is open");
            return 4;
        }
        return -1;
    }
    
    /**
     * Checks if one of the corners is open.
     * 
     * @param board the game board
     * @return an array containing the coordinates of a corner if a corner is open
     */
    public int tOpenCorner(String[] board){
        if(validMove(board,0)){
            System.out.println("top left corner is open");
            return 0;
        }
        if(!validMove(board,0)) System.out.println("top left corner is not open");
        if(validMove(board,2)){
            System.out.println("top right corner is open");
            return 2;
        }
        if(!validMove(board,2)) System.out.println("top right corner is not open");
        if(validMove(board,6)){
            System.out.println("bottom left corner is open");
            return 6;
        }
        if(!validMove(board,6)) System.out.println("bottom left corner is not open");
        if(validMove(board,8)){
            System.out.println("bottom right corner is open");
            return 8;
        }
        if(!validMove(board,8)) System.out.println("bottom right corner is not open");
        return -1;
    }
}
