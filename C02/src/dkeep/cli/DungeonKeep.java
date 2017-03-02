package dkeep.cli;


import dkeep.logic.GameState;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Scanner;

/**
 * @author luismartins
 *
 */
public class DungeonKeep {

	private GameState gs = new GameState();
	

	public void run()
	{
		gs.startGame();
		int randomInd;
		char move; // hero's movement key
		gs.printMap();
		
		while(gs.getgameRunning()==0)
		{
			
			Scanner s = new Scanner(System.in);
			move = s.next().charAt(0);
			s.close();
			
			gs.UpdateGame(move);
			gs.printMap();
			randomInd = ThreadLocalRandom.current().nextInt(1,101);
			
			
			
			//???????????
			if(randomInd >= 70 && gs.getLevel() == 1)
			{
				gs.getGuard().setCharateristic();
			
			}		
			
		}
		
	}
	

	public static void main(String[] args) {

		
		DungeonKeep dk = new DungeonKeep();
		
		dk.run();
		if(dk.gs.getgameRunning() == 1){
			System.out.println("You WIN!\n");
			
		}
		else 
			System.out.println("Game Over!");
		
		
		return;

	}
	
}
