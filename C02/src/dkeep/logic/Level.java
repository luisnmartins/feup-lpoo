package dkeep.logic;

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

	

	public int getLevel()
	{
		return 0;
	}
	
	
	//return 0 - if is to continue
	//return 1 - if hero loses
	//return 2 - if is to change level
	public abstract int updateGame(char move);
	
	//TODO make hero's update move here
	public void updateHero(char move)
	{
		System.out.println("Update Hero");
		myHero.changePosition(move, false);
		int x_temp = myHero.getXTemp();
		int y_temp = myHero.getYTemp();
		if(currentmap.moveTo(x_temp, y_temp))
		{
			if(myKey.isOnTop(myHero))
			{
				if(this instanceof DungeonLevel)
				{
					for(int i=0; i<doors.size(); i++)
					{
						doors.get(i).OpenDoor();
					}
				}
				else
					myKey.setFound();
			}
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
	

	
	
	
	
	

}
