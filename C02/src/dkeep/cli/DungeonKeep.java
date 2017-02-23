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
			
			
			//First level
			if(gs.getLevel() == 1)
			{
				gs.UpdateGuard();
				gs.UpdateHero(move);
				
			
			}
			else
			{
				gs.UpdateHero(move);
				gs.UpdateOgre();
			}
			
			
		}
	}
	
	public void printMap() {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
				System.out.print(" ");

			}
			System.out.print("\n");
		}

	}

	{
		
		printMap();
		cicle();
		
	}

	public static void main(String[] args) {

		
		DungeonKeep dk = new DungeonKeep();
		dk.run();
		
		
		

	}
	
}
