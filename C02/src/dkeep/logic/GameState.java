package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

import dkeep.logic.Map;


public class GameState {

	
	
	private Hero myHero;
	private Ogre myOgre;
	private Guard myGuard;
	private int level;
	private Map mymap;
	private Vector myOgres;
	
	
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
		else if(level == 2)
		{
			
			UpdateHero(move);
			UpdateOgre();
			
		}
	}

	
	
	
	
	
	
	
	public void UpdateHero(char move)
	{
		myHero.changePosition(move,false);
		mymap.ClearPosition(myHero.getX(), myHero.getY());
		if(mymap.ValidPosition(myHero.getXTemp(), myHero.getYTemp()) == false)
		{
			if(mymap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'k')
			{
				myHero.setX(myHero.getXTemp());
				myHero.setY(myHero.getYTemp());
				myHero.setKey(true);
				if (level == 1)
				{
					mymap.setMapPosition(5, 0, 'S');
					mymap.setMapPosition(6, 0, 'S');
					myOgre = new Ogre(1,4,'0','*');
					
				}else if (level == 2)
				{
					mymap.setMapPosition(1, 0, 'S');
					
				}
				
			}
			else if (mymap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'X' || mymap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'I' || mymap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'g')
			{
				myHero.setXTemp(myHero.getX());
				myHero.setYTemp(myHero.getY());
			}
			else if (mymap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'S')
			{
				level = 2;
				mymap.ChangeLevelMap();
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
		mymap.setMapPosition(myHero.getX(), myHero.getY(),myHero.getElement());
		
	}
	
	public void UpdateGuard()
	{
		if(myGuard.getCharateristic() == true && myGuard.getType() == 1)
		{
			mymap.setMapPosition(myGuard.getX(),myGuard.getY(), 'g');
		}else if(myGuard.getCharateristic() == true && myGuard.getType() == 2)
		{
			
			myGuard.changePosition(myGuard.getMoveGuard(), true);
			mymap.ClearPosition(myGuard.getX(), myGuard.getY());
			myGuard.setX(myGuard.getXTemp());
			myGuard.setY(myGuard.getYTemp());
			mymap.setMapPosition(myGuard.getX(), myGuard.getY(),myGuard.getElement());
		}
		else
		{
		myGuard.changePosition(myGuard.getMoveGuard(),false);
		mymap.ClearPosition(myGuard.getX(), myGuard.getY());
		myGuard.setX(myGuard.getXTemp());
		myGuard.setY(myGuard.getYTemp());
		mymap.setMapPosition(myGuard.getX(), myGuard.getY(),myGuard.getElement());
		}
	}
	
	public void UpdateOgre()
	{
		do
		{
			myOgre.changePosition(myOgre.GenerateOgre(),false);
			mymap.ClearPosition(myOgre.getX(), myOgre.getY());
			if(mymap.ValidPosition(myOgre.getXTemp(), myOgre.getYTemp()) == false)
			{
				if(mymap.getElement(myOgre.getXTemp(), myOgre.getYTemp()) == 'k')
				{
					myOgre.setElm('$');
					break;
				}
				else if (mymap.getElement(myOgre.getXTemp(), myOgre.getYTemp()) == 'X' || mymap.getElement(myOgre.getXTemp(), myOgre.getYTemp()) == 'I')
				{
					myOgre.setXTemp(myOgre.getX());
					myOgre.setYTemp(myOgre.getY());
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
		mymap.setMapPosition(myOgre.getX(), myOgre.getY(),myOgre.getElement());
		
		
		int[] club;
		do
		{
			club = myOgre.ChangeClub();
			mymap.ClearPosition(myOgre.getAttackX(), myOgre.getAttackY());
			if(mymap.ValidPosition(club[0], club[1]) == false)
			{
				if(mymap.getElement(club[0], club[1]) == 'k')
				{
					
					myOgre.setClubElm('$');
					break;
				}
				else if (mymap.getElement(club[0], club[1]) == 'X' || mymap.getElement(club[0], club[1]) == 'I')
				{
					
				}
			}
			else
			{
				myOgre.setClubElm('*');
				break;
			}
		}while(true);
		myOgre.setClub(club[0], club[1]);
		mymap.setMapPosition(club[0], club[1], myOgre.getClubElm());
			
		
		
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
	public Guard getGuard()
	{
		return myGuard;
	}
}
