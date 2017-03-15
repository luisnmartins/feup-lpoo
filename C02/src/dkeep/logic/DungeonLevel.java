package dkeep.logic;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import dkeep.cli.DungeonKeep.state;



public class DungeonLevel extends Level{
	
	private Guard myGuard;
	private boolean moveGuard= true;
	
	public DungeonLevel(Map mymap)
	{
		//Map dungeonmap = new Map(1);
		this.setMap(mymap);
		
		
		//analyze map to get elements and clear elements initial position in the map
		char[][] maptoanalyze = mymap.getMap();
		for(int i=0; i<maptoanalyze.length; i++)
		{
			for(int j=0; j<maptoanalyze.length; j++)
			{
				if(maptoanalyze[i][j] == ' ' || maptoanalyze[i][j] == 'X')
				{}
				
				//create hero
				else if(maptoanalyze[i][j] == 'H')
				{
						myHero = new Hero(i, j, 'H');
						mymap.ClearPosition(i, j);
						
				}
				
				//create key
				else if(maptoanalyze[i][j] == 'k')
				{
					myKey = new Key(i, j, 'k');
					mymap.ClearPosition(i, j);
				}
				
				//create Guard
				else if(maptoanalyze[i][j] == 'G')
				{
					myGuard = new Guard(i, j, 'G');
					mymap.ClearPosition(i, j);
				}
				
				//create Doors
				else if(maptoanalyze[i][j] == 'I' && (i== 0 || i == maptoanalyze.length || j==0 || j == maptoanalyze[i].length))
				{
					Door mydoor = new Door(i, j);
					doors.add(mydoor);
					mymap.ClearPosition(i, j);
				}
				
			}
		}
		
		
		
		
		
		/*Door newdoor = new Door(5, 0);
		doors.add(newdoor);
		Door newdoor2 = new Door(6, 0);
		doors.add(newdoor2);*/
		
	}
	
	public void NotMoveElements()
	{
		moveGuard = false;
	}
	
	
	@Override
	public state updateGame(char move)
	{	
		
		this.updateHero(move);
		if(moveGuard)
			updateGuard();
		if(VerifyColisionGuard())
			return state.LOSE;
		else if(changeLevel())
		{
			
			return state.NEXTLEVEL;
		}
		else return state.RUNNING;
		
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
	

	
	public Level nextLevel()
	{
		
		Map nextmap = new Level2Map();
		return new OgreLevel(nextmap);
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
