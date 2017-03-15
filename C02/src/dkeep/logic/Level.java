package dkeep.logic;

import dkeep.cli.DungeonKeep.state;
import java.util.ArrayList;

public abstract class Level {
	
	protected Map currentmap;
	protected Hero myHero;
	protected Key myKey;
	protected ArrayList<Door> doors = new ArrayList<Door>();
	
	
	public  char[][] getMap(){
		
		return currentmap.getMap();
	}
	

	public void setMap(Map currentmap)
	{
		this.currentmap = currentmap;
	}

	

	
	//return 0 - if is to continue
	//return 1 - if hero loses
	//return 2 - if is to change level
	public abstract state updateGame(char move);
	
	//TODO make hero's update move here
	public void updateHero(char move)
	{		
		myHero.changePosition(move, false);
		int x_temp = myHero.getXTemp();
		int y_temp = myHero.getYTemp();
		boolean canotleave=false;
		if(currentmap.moveTo(x_temp, y_temp))
		{
			for(int i=0; i<doors.size(); i++)
			{
				if(doors.get(i).doorAchieved(myHero) && doors.get(i).IsOpened()==false)
				{
					if(myKey.getFound())
					{
						doors.get(i).OpenDoor();
							
					} 
					myHero.setTempPosition(myHero.getX(), myHero.getY());
					canotleave = true;
					break;
				}
			}
			
			
			if(myKey.isOnTop(myHero))
			{
				if(this instanceof DungeonLevel)
				{
					for(int i=0; i<doors.size(); i++)
					{
						doors.get(i).OpenDoor();
					}
				}
				else if(this instanceof OgreLevel)
				{
					
					myKey.setFound();
					myHero.setElm('K');
				}
					
			}
			if(!canotleave)
				myHero.setPosition(x_temp, y_temp);
		}
		else
			myHero.setTempPosition(myHero.getX(), myHero.getY());
			
		
		
	}
	
	
	public Hero getHero()
	{
		return myHero;
	}
	
	public boolean changeLevel()
	{
		for(int i=0; i<doors.size(); i++)
		{
			if(doors.get(i).doorAchieved(myHero))
				return true;
		}
		return false;
	}
	
	
	public Level nextLevel()
	{
		return null;
	}
	
	public Map nextMap()
	{
		return null;
	}
	
	public abstract void NotMoveElements();
	
	public boolean DoorsAreOpened()
	{
		return doors.get(0).IsOpened();
	}
	
	
	public Key getKey()
	{
		return this.myKey;
	}
	

	
	
	
	
	

}
