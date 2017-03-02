package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

import dkeep.logic.Map;


public class GameState {

	//create map
	private Map currentmap;
	//private Map level1map;
	//private Map level2map;
	
	
	
	//create elements
	private Hero myHero;
	//private Ogre myOgre;
	private Guard myGuard;
	private Ogre[] myOgres;
	
	
	//flag to verify GameState
	private int gameRunning;
	
	//current level
	private int level;
	
	
	
	
	
	public void startGame()
	{
		
		myHero = new Hero(1, 1, 'H');
		myGuard = new Guard(1, 8, 'G', 0);
		level = 1;
		currentmap = new Level1Map();
		gameRunning = 0;
		
	}
	
	public void UpdateGame(char move)
	{
		
		if(level == 1)
		{
			
			UpdateGuard();
			UpdateHero(move);
			if(VerifyColisionGuard() == true)
				return;
			
		}
		else if(level == 2)
		{
			
			for(int i=0; i<myOgres.length; i++)
			{
				UpdateOgre(myOgres[i]);
			}
			UpdateHero(move);
			for(int i = 0; i< myOgres.length;i++){
				
				if( VerifyColisionOgre(myOgres[i]) == true)
					return;
			}
						
		}
//		if(VerifyColision() == true)
//			return;
		
		
	}

	
	
	
	
	
	
	
	public void UpdateHero(char move)
	{
		
		
		//Clean and update hero temporary position
		currentmap.ClearPosition(myHero.getX(), myHero.getY());
		myHero.changePosition(move,false);
		
		
		if(currentmap.ValidPosition(myHero.getXTemp(), myHero.getYTemp()) == false)
		{
			
			if(currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'k')
			{
				myHero.setX(myHero.getXTemp());
				myHero.setY(myHero.getYTemp());
				myHero.setKey(true);
				
			
				if (level == 1)
				{
					currentmap.setMapPosition(5, 0, 'S');
					currentmap.setMapPosition(6, 0, 'S');
					
					
					int i = ThreadLocalRandom.current().nextInt(1, 6);
					myOgres = new Ogre[i];
					for(int j=0; j < i ; j++)
					{
						myOgres[j] = new Ogre(1,4,'O','*');
					}
					
				}
				else
					myHero.setElm('K');
				
			}
			else if(level == 2 && myHero.getKey() == true && currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'I')
			{
				gameRunning = 1;
				myHero.setX(myHero.getXTemp());
				myHero.setY(myHero.getYTemp());
				myHero.setElm('S');
			}
			
			else if (currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'X' || currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'I' || currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'g')
			{
				myHero.setXTemp(myHero.getX());
				myHero.setYTemp(myHero.getY());
			}
			
			
			else if (currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'S')
			{
				level = 2;
				currentmap = new Level2Map();
				myHero.setKey(false);
				myHero.setElm('A');
				myHero.setX(7);
				myHero.setY(1);
				myHero.setXTemp(7);
				myHero.setYTemp(1);
				
			}
			
		}
		else
		{
			myHero.setX(myHero.getXTemp());
			myHero.setY(myHero.getYTemp());
		}
		currentmap.setMapPosition(myHero.getX(), myHero.getY(),myHero.getElement());
		
	}
	
	
	public void UpdateGuard()
	{
		if(myGuard.getCharateristic() == true && myGuard.getType() == 1)
		{
			myGuard.setElm('g');
			currentmap.setMapPosition(myGuard.getX(),myGuard.getY(), 'g');
		}
		
		else if(myGuard.getCharateristic() == true && myGuard.getType() == 2)
		{
			
			myGuard.changePosition(myGuard.getMoveGuard(), true);
			currentmap.ClearPosition(myGuard.getX(), myGuard.getY());
			myGuard.setX(myGuard.getXTemp());
			myGuard.setY(myGuard.getYTemp());
			currentmap.setMapPosition(myGuard.getX(), myGuard.getY(),myGuard.getElement());
		}
		else
		{
			myGuard.setElm('G');
			myGuard.changePosition(myGuard.getMoveGuard(),false);
			currentmap.ClearPosition(myGuard.getX(), myGuard.getY());
			myGuard.setX(myGuard.getXTemp());
			myGuard.setY(myGuard.getYTemp());
			currentmap.setMapPosition(myGuard.getX(), myGuard.getY(),myGuard.getElement());
		}
	}
	
	
	public void UpdateOgre(Ogre myOgre)
	{
		
		
			currentmap.ClearPosition(myOgre.getAttackX(), myOgre.getAttackY());
		if(myOgre.getElement() == '8')
		{
			if(myOgre.getStopOgre() == 0)
			{				
				myOgre.setElm('O');
			}
			else
			{
				myOgre.decStopOgre();
				currentmap.setMapPosition(myOgre.getX(), myOgre.getY(),myOgre.getElement());
				return;
			
			}
		}
				currentmap.ClearPosition(myOgre.getX(), myOgre.getY());
		
		
		if(myOgre.getElement() == '$' && myHero.getKey() == false)
		{
			//currentmap.setMapPosition(myOgre.getX(),myOgre.getY(), 'k');
			myOgre.setElm('O');
			
		}
		
		if(myOgre.getClubElm() == '$' && myHero.getKey() == false)
		{	
			myOgre.setClubElm('*');
			
			//currentmap.setMapPosition(myOgre.getAttackX(),myOgre.getAttackY(), 'k');
		}
		
		
		do
		{
			myOgre.changePosition(myOgre.GenerateOgre(),false);
			
			if(currentmap.ValidPosition(myOgre.getXTemp(), myOgre.getYTemp()) == false)
			{
				if(currentmap.getElement(myOgre.getXTemp(), myOgre.getYTemp()) == 'k')
				{
					myOgre.setElm('$');
					break;
				}
				if (currentmap.getElement(myOgre.getXTemp(), myOgre.getYTemp()) == 'X' || currentmap.getElement(myOgre.getXTemp(), myOgre.getYTemp()) == 'I')
				{
					myOgre.setXTemp(myOgre.getX());
					myOgre.setYTemp(myOgre.getY());
				}
				
			}
			else
			{

				break;
				
			}
				
		}while(true);
		myOgre.setX(myOgre.getXTemp());
		myOgre.setY(myOgre.getYTemp());
		
		currentmap.setMapPosition(myOgre.getX(), myOgre.getY(),myOgre.getElement());
		
		
		
		int[] club;
		do
		{
			club = myOgre.ChangeClub();
			
			if(currentmap.ValidPosition(club[0], club[1]) == false)
			{
				if(currentmap.getElement(club[0], club[1]) == 'k')
				{
					myOgre.setClubElm('$');
					break;
				}
			
			}
			else
			{
	

			
				break;
			}
		}while(true);
		myOgre.setClub(club[0], club[1]);
		currentmap.setMapPosition(club[0], club[1], myOgre.getClubElm());
			
		
		
	}
	
	public Boolean VerifyColisionGuard()
	{

			if(myGuard.getElement() != 'g')
			{
				if ((myHero.getX() == myGuard.getX() && Math.abs(myHero.getY() - myGuard.getY()) <= 1) || (myHero.getY() == myGuard.getY() && Math.abs(myHero.getX() - myGuard.getX()) <= 1))
				{
					gameRunning = 2;
					return true;
				}
			}
			return false;

	}
	public Boolean VerifyColisionOgre(Ogre myOgre)
	{
			if((myHero.getX() == myOgre.getX() && Math.abs(myHero.getY() - myOgre.getY()) <= 1) || (myHero.getY() == myOgre.getY() && Math.abs(myHero.getX() - myOgre.getX()) <= 1))
			{
				
				myOgre.setStopOgre(2);
				myOgre.setElm('8');
				
				return true;
			}
			else if(((myHero.getX() == myOgre.getAttackX() && Math.abs(myHero.getY() - myOgre.getAttackY()) <= 1) || (myHero.getY() == myOgre.getAttackY() && Math.abs(myHero.getX() - myOgre.getAttackX()) <= 1)) && myOgre.getStopOgre() == 0)
			{
				gameRunning = 2;
				return true;
			}
			return false;
		}
		

	
	
	
	public void printMap() {
		
		
		if(currentmap.ValidPosition(8, 7) && level == 1)
			currentmap.setMapPosition(8, 7, 'k');
		
		else if(currentmap.ValidPosition(1, 7) && level == 2 && !myHero.getKey())
			currentmap.setMapPosition(1, 7, 'k');
		
		char[][] map = currentmap.getMap();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
				System.out.print(" ");

			}
			System.out.print("\n");
		}

	}
	
	public Guard getGuard()
	{
		return myGuard;
	}
	
	
	public int getgameRunning()
	{
		return this.gameRunning;
	}
	
	public int getLevel()
	{
		return level;
	}
}
