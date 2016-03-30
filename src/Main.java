import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Game ticTacToe = new Game();
		AI bot = new AI('o','x');
		Scanner sc = new Scanner(System.in);
		ticTacToe.printBoard();
		while(!ticTacToe.checkForWin('x') && !ticTacToe.checkForWin(('o')) && !ticTacToe.isBoardFull()){
			if (ticTacToe.getPlayer() == 'x'){
				System.out.println("YOUR TURN");
				int[] playerMove = new int[2];
				playerMove[0] = sc.nextInt();
				playerMove[1] = sc.nextInt();
				sc.nextLine();
				ticTacToe.placeMark(playerMove[0], playerMove[1]);
			}
			else{
				System.out.println("BOT TURN");
				int[] AImove = bot.genMove(ticTacToe.copyGame());
				ticTacToe.placeMark(AImove[0], AImove[1]);
			}
			ticTacToe.printBoard();
		}
		if(ticTacToe.checkForWin('x')){
			System.out.println("YOU WIN");
		}
		else if (ticTacToe.checkForWin('o')){
			System.out.println("YOU LOST");
		}
		else{
			System.out.println("DRAW");
		}
	}

}
