
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
	
	/**
	 * Constructor of a guard type by setting its initial position and the element that represents it in the matrix
	 * a guard is defined by its predefined move set wich is stored in moveLevel1 variable, so the constructor puts the variable
	 * iterator at 0, variable that represents the current move type of the guard in the array of char
	 * @param xP  Guard's matrix position - Line
	 * @param yP Guard's matrix position - Column
	 * @param Elm Char that represents the Guard in the matrix
	 */
	public Guard(int xP, int yP, char Elm)
	{
		super(xP, yP, Elm);
		iterator = 0;
		
	}
	
	/**
	 * Gets the move that the guard is supposed to do by using the variable iterator to check the move char in 
	 * predefined move set array, and updates the iterator accordingly so that the next move set is ready to be check
	 * @return the char that represents the move of the guard 
	 */
	public char getMoveGuard()
	{
		System.out.println("normal:"+iterator+"\n");
		char move = moveLevel1[iterator];
		
		if(iterator == moveLevel1.length-1)
		{
			iterator=0;
			
		}else
			iterator++;
		
		return move;
		
	}
	
	/**
	 * In this game, there are three types of guard (Rookie,Suspicious and Drunken) the last two are distinguished
	 * by their specific characteristics ( for example, the Suspicious guard can start walking in the opposite direction
	 * , its move set is not entirely predefined, while the drunken can randomly fall asleep,this function simply 
	 * updates their characteristics flag, randomly (trying with a 30% chance), that means if the flag is set to 
	 * false it changes to true and vice versa, note that the variable iterator needs to be updated because of the
	 * suspicious guard ability
	 */
	public void updateCharacteristic()
	{
		int randomInd = ThreadLocalRandom.current().nextInt(1,101);
		if(randomInd >= 70){
		
			if(this.characteristic == false){
				characteristic  = true;
				
				if(iterator == 0){
					
					iterator = moveLevel1.length-1;
				}else
				iterator--;
			
			}else{
			
				characteristic = false;
				if(iterator == moveLevel1.length-1){
					
					iterator = 0;
					
				}else iterator++;
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
		else return false;
	
		
	}
	
	
	/**
	 * As said there are three types of guards in the game,this function check the parameter type and creates
	 * and returns an guard accordingly 
	 * @param type an int to represent the guard type is going to be created
	 * @param xP represents the x position of the guard, variable to call the guard constructor
	 * @param yP represents the y position of the guard, variable to call the guard constructor
	 * @param Elm represents the element of the new guard, used in the guard constructor
	 * @return the new created guard of the specific type
	 */
	public Guard getGuardType(int type, int xP, int yP, char Elm)
	{
		switch(type)
		{
			case 1:
				return new DrunkenGuard(xP, yP, Elm);
			case 2:
				return new SuspiciousGuard(xP, yP, Elm);
			case 3:
				return new RookieGuard(xP, yP, Elm);
			default:
				return null;
		}
		
	}
	
	
	
}

