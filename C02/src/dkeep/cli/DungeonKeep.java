package dkeep.cli;


import dkeep.logic.GameState;

import java.util.Scanner;

public class DungeonKeep {

	private GameState gs = new GameState();
	
	
	
	
	public void run()
	{
		gs.startGame();
		
		char move; // hero's movement key

		while(true)
		{
			Scanner s = new Scanner(System.in);
			move = s.next().charAt(0);
			
			gs.UpdateGame(move);
			gs.printMap();
			
			
		}
	}
	

	public static void main(String[] args) {

		
		DungeonKeep dk = new DungeonKeep();
		dk.run();
		
		
		

	}
	
}
