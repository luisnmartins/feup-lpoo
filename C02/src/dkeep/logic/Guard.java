package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

public class Guard extends Character implements java.io.Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected char moveLevel1[] = { 'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd',
			'd', 'w', 'w', 'w', 'w', 'w' };
	
	protected int iterator;
	
	protected boolean characteristic;
	
	
	//private int guardType; // Type 1 - drunkGuard , Type 2 - SuspiciousGuard, Type 3 - NormalGuard
	//private Boolean typeCharateristic;
	

	public Guard(int xP, int yP, char Elm)
	{
		super(xP, yP, Elm);
		iterator = 0;
		
	}
	
	public char getMoveGuard()
	{
		char move = moveLevel1[iterator];
		
		
		if(iterator != moveLevel1.length-1)
		{
			iterator++;
			
		}else
			iterator = 0;
		
		return move;
		
	}
	
	public void updateCharacteristic()
	{
		int randomInd = ThreadLocalRandom.current().nextInt(1,101);
		if(randomInd >= 70)
		{
		
			if(this.characteristic == false)
			{
				characteristic  = true;
				if(iterator == 0){
					
					iterator = moveLevel1.length-1;
				}
				else
				iterator--;
			}
			else
			{
			
				characteristic = false;
				if(iterator == moveLevel1.length-1)
				{
					iterator = 0;
				}
				else iterator++;
			}
			
		}

	}
	

	
	public boolean update(Map currentmap, char heromove)
	{

		if(this.getMoveCharacter())
		{
			this.setElm('G');
			this.setIsParalyzed(false);
			if(this instanceof SuspiciousGuard && this.characteristic)
			{
				this.changePosition(this.getMoveGuard(),true);
			}else 
				this.changePosition(getMoveGuard(),false);
			
			this.setPosition(this.getXTemp(), this.getYTemp());
		
			return true;
		}
		else
			return false;
	
		
	}
	
	public Guard getGuardType(int type, int xP, int yP, char Elm)
	{
		switch(type)
		{
			case 1:
			{
				return new DrunkenGuard(xP, yP, Elm);
			
			}
			case 2:
			{
				return new SuspiciousGuard(xP, yP, Elm);
			}
			case 3:
			{
				return new RookieGuard(xP, yP, Elm);
			}
			default:
				return null;
		}
		
	}
	
	
	
}
