package dkeep.logic;

import dkeep.logic.Map;


public class GameState {

	
	
	private Hero myHero;
	private Ogre myOgre;
	private Guard myGuard;
	private int level;
	private Map mymap;
	
	
	public void startGame()
	{
		myHero = new Hero(1, 1, 'H');
		//Monster = new Ogre(1, 4, 'O', '*');
		myGuard = new Guard(1, 8, 'G', 0);
		level = 1;
		mymap = new Map();
		
	}
	
	public void UpdateGame(char move)
	{
		if(level == 1)
		{
			UpdateGuard();
			UpdateHero(move);
				
			
		}
		else
		{
			UpdateHero(move);
			UpdateOgre();
		}
	}

	
	
	
	
	
	
	
	public void UpdateHero(char move)
	{
		myHero.changePosition(move);
		if(mymap.ValidPosition(myHero.getXTemp(), myHero.getYTemp()) == false)
		{
			if(mymap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'k')
			{
				myHero.setX(myHero.getXTemp());
				myHero.setY(myHero.getYTemp());
				myHero.setKey(true);
			}
		}
		else
		{
			myHero.setX(myHero.getXTemp());
			myHero.setY(myHero.getYTemp());
		}
		
	}
	
	public void UpdateGuard()
	{
		myGuard.changePosition(myGuard.getMoveGuard());
		myGuard.setX(myGuard.getXTemp());
		myGuard.setY(myGuard.getYTemp());
		
	}
	
	public void UpdateOgre()
	{
		do
		{
			myOgre.changePosition(myOgre.GenerateOgre());
			if(mymap.ValidPosition(myOgre.getXTemp(), myOgre.getYTemp()) == false)
			{
				if(mymap.getElement(myOgre.getXTemp(), myOgre.getYTemp()) == 'k')
				{
					myOgre.setElm('$');
					break;
				}
			}
			else
			{
				myOgre.setElm('O');
				break;
			}
				
		}while(true);
		myOgre.setX(myOgre.getXTemp());
		myOgre.setY(myOgre.getYTemp());
		
		
		
		int[] club;
		do
		{
			club = myOgre.ChangeClub();
			if(mymap.ValidPosition(club[1], club[2]) == false)
			{
				if(mymap.getElement(club[1], club[2]) == 'k')
				{
					
					myOgre.setClubElm('$');
					break;
				}
			}
			else
			{
				myOgre.setClubElm('*');
				break;
			}
		}while(true);
		myOgre.setClub(club[1], club[2]);
			
		
		
	}

	
	
	public void printMap() {
		
		char[][] map = mymap.getMap();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
				System.out.print(" ");

			}
			System.out.print("\n");
		}

	}
}
