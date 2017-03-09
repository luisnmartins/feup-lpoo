package dkeep.logic;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class DungeonLevel extends Level{
	
	private Guard myGuard;
	
	
	public DungeonLevel()
	{
		Map dungeonmap = new Level1Map();
		this.setMap(dungeonmap);
		myHero = new Hero(1, 1, 'H');
		myKey = new Key(8, 7, 'k');
		myGuard = new Guard(1, 8, 'G');
		
		//create Doors
		Door newdoor = new Door(5, 0);
		doors.add(newdoor);
		Door newdoor2 = new Door(6, 0);
		doors.add(newdoor2);
		
	}
	
	//TODO criar o update game para os varios niveis
	@Override
	public int updateGame(char move)
	{	
		System.out.println("Update Game");
		this.updateHero(move);
		updateGuard();
		if(VerifyColisionGuard())
			return 1;
		else if(changeLevel())
		{
				return 2;
		}
		else return 0;
		
	}
	
	//TODO update guard movement
	public void updateGuard()
	{
		if(myGuard.getType()!= 3) 
		{
			int randomInd = ThreadLocalRandom.current().nextInt(1,101);
			if(randomInd >= 70)
			{
				myGuard.setCharateristic();
			}
		}
			
		if(myGuard.getType() == 1 && myGuard.getCharateristic())
		{
				myGuard.setElm('g');

		}
		else
		{
			myGuard.setElm('G');
			myGuard.changePosition(myGuard.getMoveGuard(), myGuard.getCharateristic());
			myGuard.setPosition(myGuard.getXTemp(), myGuard.getYTemp());
		}
			

	
		
	}
	
	//TODO Verify
	public Boolean VerifyColisionGuard()
	{

			if(myGuard.getElement() != 'g')
			{
				if ((myHero.getX() == myGuard.getX() && Math.abs(myHero.getY() - myGuard.getY()) <= 1) || (myHero.getY() == myGuard.getY() && Math.abs(myHero.getX() - myGuard.getX()) <= 1))
				{
					return true;
				}
			}
			return false;

	}
	
	
	public int getLevel()
	{
		return 1;
	}
	
	public Level nextLevel()
	{
		return null;
	}
	
	
	
	//TODO update map to send
	public  char[][] getMap()
	{
		
		char[][] maptocopy = currentmap.getMap();
		char[][] maptosend = new char[maptocopy.length][];
		for(int i=0; i< maptocopy.length; i++)
		{
			maptosend[i] = Arrays.copyOf(maptocopy[i], maptocopy[i].length);
		}
		
		
		maptosend[myKey.getX()][myKey.getY()] = 'k';
		maptosend[myHero.getX()][myHero.getY()] = myHero.getElement();
		maptosend[myGuard.getX()][myGuard.getY()] = myGuard.getElement();
		for(int i=0; i<doors.size(); i++)
		{
			maptosend[doors.get(i).getX()][doors.get(i).getY()] = doors.get(i).getSymbol();
		}
		
		
		return maptosend;
		
	}


	

}
