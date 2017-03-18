package dkeep.logic;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import dkeep.cli.DungeonKeep.state;



public class DungeonLevel extends Level{
	
	//private Guard myGuard;
	//private boolean moveGuard= true;
	
	public DungeonLevel(Map mymap, int Guardtype)
	{
		super(mymap, 0, Guardtype);
		mymap.getKey().setIsaKey(false);		
	}
	

	
	
	/*@Override
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
		
	}*/

	//TODO update guard movement
	
	

	/*public Boolean VerifyColisionGuard()
	{

			if(myGuard.getElement() != 'g')
			{
				if ((myHero.getX() == myGuard.getX() && Math.abs(myHero.getY() - myGuard.getY()) <= 1) || (myHero.getY() == myGuard.getY() && Math.abs(myHero.getX() - myGuard.getX()) <= 1))
				{
					return true;
				}
			}
			return false;

	}*/
	

	
	public Level nextLevel()
	{
		
		Map nextmap = new Level2Map();
		int rand = ThreadLocalRandom.current().nextInt(1, 6);
		return new OgreLevel(nextmap, rand);
	}
	
	
	
	/*//TODO update map to send
	public String getMap()
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
		
		String toprint="";
		
		for(int i=0; i<maptosend.length; i++)
		{
			toprint += String.valueOf(maptosend[i]);
			toprint += "\n";
		}
		
		
		return toprint;
		
	}*/


	

}
