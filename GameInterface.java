
/**
 * Interface that interacts with the user to figure out what game
 *  they want to play, then run the game that they want.
 *
 * @author (Tobias Ephron)
 * @version (Version 1; Oct. 29, 2019)
 */
import java.util.*;
public class GameInterface
{
    private static String ttt = "tictactoe";
    private static String oth = "othelo";
    private static String ms = "minesweeper";
    private static String bs = "battleship";
    
    public GameInterface(){
        
    }
    
    public static void main(String[] args){
        System.out.print('\u000C');
        GameInterface gi = new GameInterface();
        Players p = new Players();
        Human h = new Human();
        Computer c = new Computer(" ");
        Scanner s = new Scanner(System.in);
        boolean play = true;
        System.out.println("What do you want your gamerID to be?");
        String gamerID = s.next();
        if(gamerID.length() == 0){
            System.out.print('\u000C');
            System.out.println("gamerID will be set to 'PLAYER'");
            gamerID = "PLAYER";
            System.out.println("");
        }
        h = new Human(gamerID);
        while(play){
            System.out.print('\u000C');
            System.out.println("Welcome " + h.getUsername() + "!");
            String game = gi.chooseGame();
            System.out.print('\u000C');
            if(game.equals(ttt)){
                System.out.print('\u000C');
                TicTacToe t = new TicTacToe(h,c);
                t.play();
            }
            else if(game.equals(oth)){
                System.out.print('\u000C');
                Othelo o = new Othelo(h,c, new String[10][10]);
                o.play();
            }
            else if(game.equals(ms)){
                System.out.print('\u000C');
                MineSweeper m = new MineSweeper(h);
                m.play();
            }
            else if(game.equals(bs)){
                System.out.print('\u000C');
                BattleShip b = new BattleShip(h,c);
                b.play();
            }
            else if(gi.quit(game)){
                play = false;
                continue;
            }
            System.out.println("");
            System.out.println("Would you like to play another game? (yes/no)");
            String ans = s.next().toLowerCase();
            if(ans.charAt(0) == 'n' || gi.quit(ans)){
                play = false;
            }
        }
        //System.out.print('\u000C');
        System.out.println("Have a good day " + h.getUsername() + "!");
    }
    
    public String chooseGame(){
        Scanner sc = new Scanner(System.in);
        String ret = "";
        String ans = "";
        boolean choosing = true;
        while(choosing){
            System.out.println("What game do you want to play? : ");
            System.out.println("TicTacToe | Othelo | MineSweeper | BattleShip");
            ans = sc.next().toLowerCase();
            if(ans.equals(ttt) || ans.equals("tic") || ans.equals("ttt")){
                ret = ttt;
                choosing = false;
            }
            else if(ans.equals(oth) || ans.equals("oth")){
                ret = oth;
                choosing = false;
            }
            else if(ans.equals(ms) || ans.equals("mine") || ans.equals("ms")){
                ret = ms;
                choosing = false;
            }
            else if(ans.equals(bs) || ans.equals("battle") || ans.equals("bs")){
                ret = bs;
                choosing = false;
            }
            else if(quit(ans)){
                ret = "quit";
                choosing = false;
            }
            else{
                System.out.print('\u000C');
                System.out.println("Please enter TicTacToe, Othelo, MineSweeper, or BattleShip (or quit)");
                System.out.println("(enter as shown)");
                System.out.println("");
            }
        }
        return ret;
    }
    
    public boolean quit(String x){
        if(x.equals("quit")) return true;
        return false;
    }
}
