import java.io.IOException;
import java.util.ArrayList;

public class AI {
	char player;
	char enemy;
	
	public AI(char player, char enemy){
		this.player = player;
		this.enemy = enemy;
	}
	
	public int[] genMove(Game tempGame) throws ClassNotFoundException, IOException{
		ArrayList<int[]> freeMoves = tempGame.checkEmpty();
		ArrayList<int[]> depthMoves = new ArrayList<int[]>();
		
		for (int[] moveCoord : freeMoves){
			Game tempGame2 = tempGame.copyGame();
			tempGame2.placeMark(moveCoord[0], moveCoord[1]);
			depthMoves.add(miniMax(tempGame2,0,new int[]{moveCoord[0], moveCoord[1]}));
		}
		int max = -100;
		int[] maxMove = null;
		for (int[] moves : depthMoves){
			if (moves[2] > max){
				max = moves[2];
				maxMove = new int[]{moves[0],moves[1]};
			}
		}
		return new int[]{maxMove[0],maxMove[1],max};
	}
	
	public int[] miniMax(Game tempGame, int depth, int move[]) throws ClassNotFoundException, IOException{
		int count = 0;
		if (tempGame.checkForWin(player)){
			return new int[]{move[0],move[1],10 - depth};
		}
		if (tempGame.checkForWin(enemy)){
			return new int[]{move[0],move[1],depth - 10};
		}
		if (tempGame.isBoardFull()){
			return new int[]{move[0],move[1],0};
		}
		
		ArrayList<int[]> freeMoves = tempGame.checkEmpty();
		ArrayList<int[]> depthMoves = new ArrayList<int[]>();
		
		for (int[] moveCoord : freeMoves){
			Game tempGame2 = tempGame.copyGame();
			tempGame2.placeMark(moveCoord[0], moveCoord[1]);
			depthMoves.add(miniMax(tempGame2,depth+1,new int[]{moveCoord[0], moveCoord[1]}));
		}

		if (tempGame.getPlayer() != player){
			int min=100;
			int[] minMove = null;
			for (int[] moves : depthMoves){
				if (moves[2] < min){
					min = moves[2];
					minMove = new int[]{move[0],move[1]};
				}
			}
			return new int[]{minMove[0],minMove[1],min};
		}
		else{
			int max=-100;
			int[] maxMove = null;
			for (int[] moves : depthMoves){
				if (moves[2] > max){
					max = moves[2];
					maxMove = new int[]{move[0],move[1]};
				}
			}
			return new int[]{maxMove[0],maxMove[1],max};
		}
	}
}
