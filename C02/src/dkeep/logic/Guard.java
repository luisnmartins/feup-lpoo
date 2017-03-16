package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

public class Guard extends Character {

	private char moveLevel1[] = { 'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd',
			'd', 'w', 'w', 'w', 'w', 'w' };
	
	private int iterator;
	
	private int guardType; // Type 1 - drunkGuard , Type 2 - SuspiciousGuard, Type 3 - NormalGuard
	private Boolean typeCharateristic;
	
	public Guard(int xP, int yP, char Elm, int type)
	{
		super(xP, yP, Elm);
		iterator = 0;
		typeCharateristic = false;
		guardType = type;
		
	}
	
	public char getMoveGuard()
	{
		char move = moveLevel1[iterator];
		if (guardType == 2 && typeCharateristic == true)
		{
			if(iterator == 0)
			{
				iterator = moveLevel1.length-1;
			}else iterator--;
			return move;
		}
		
		
		if(iterator == moveLevel1.length-1)
		{
			iterator = 0;
		}else
		iterator++;
		
		return move;
		
	}
	public void setCharateristic()
	{
		if(this.typeCharateristic == false)
		{
					typeCharateristic  = true;
					if(iterator == 0){
						
						iterator = moveLevel1.length-1;
					}
					else
						iterator--;
		}
		else {
			
			typeCharateristic = false;
			if(iterator == moveLevel1.length-1)
			{
				iterator = 0;
			}
			else iterator++;
		}
		

	}
	
	public Boolean getCharateristic()
	{
		return this.typeCharateristic;
	}

	public int getType()
	{
		return this.guardType;
	}
	
	/*public void setIterator(int newIt)
	{
		this.iterator = newIt;
	}*/
	/*public int getIterator()
	{
		return this.iterator;
	}*/

	
	
}
