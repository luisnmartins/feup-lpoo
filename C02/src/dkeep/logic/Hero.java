package dkeep.logic;

public class Hero extends Character {

	//private boolean club;
	
	public Hero(int xP, int yP, char Elm)
	{
		super(xP, yP, Elm);
		
	}

	
	
	public boolean update(Map currentmap, char move)
	{
		
		super.update(currentmap, move);
		int x_temp = this.getXTemp();
		int y_temp = this.getYTemp();
		Key tempKey = currentmap.getKey();
		if(currentmap.moveTo(x_temp, y_temp))
		{
			if(tempKey.isOnTop(x_temp, y_temp))
			{
				if(tempKey.getIsaKey())
				{
					
					tempKey.setFound();
					this.setElm('K');
				}
				else
				{
					currentmap.openDoors();
				}
				
			}
			if(currentmap.verifyMoveDoors(this))
			{
				this.setPosition(x_temp, y_temp);
				return true;
			}
			
			
		}
		this.setTempPosition(this.getX(), this.getY());
		return false;
				
		
	}
	
	
}
