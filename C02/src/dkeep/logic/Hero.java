
package dkeep.logic;

import java.util.ArrayList;

public class Hero extends Character {

	//private boolean club;
	
	public Hero(int xP, int yP, char Elm)
	{
		super(xP, yP, Elm);
		
	}

	
	
	public boolean update(Map currentmap, char move, ArrayList<Character> enemies)
	{
		
		super.update(currentmap, move);
		int x_temp = this.getXTemp();
		int y_temp = this.getYTemp();
		Key tempKey = currentmap.getKey();
		boolean canmove = false;
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
				System.out.println("verifydoors");
				canmove=true;
				
			}
			
			
			for(Character enemie: enemies)
			{
				if(enemie.getIsParalyzed())
				{
					
					if(enemie.getX() == x_temp && enemie.getY() == y_temp)
					{	
						canmove=false;
						break;
					}	
					
				}
				
			}
			
			
			
					
			
		}
		if(canmove == false)	
		{
			this.setTempPosition(this.getX(), this.getY());
			return false;
		}
		else
		{	
			this.setPosition(this.getXTemp(), this.getYTemp());
			return true;
		}
				
		
	}
	
	
}

