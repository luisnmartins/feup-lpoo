package dkeep.logic;


public abstract class Character {

	private int x;
	private int y;
	private char element;
	private int xTemp;
	private int yTemp;
	
	
	//constructor
	public Character(int xPos, int yPos, char elm)
	{
		x = xPos;
		y = yPos;
		element = elm;
		xTemp = xPos;
		yTemp = yPos;
	}
	
	//setters
	
	public void setPosition(int xPos, int yPos){
		
		x = xPos;
		y= yPos;
		
	}
	
	public void setElm(char Elm)
	{
		element = Elm;
	}
	
		
	public void setTempPosition(int xPos, int yPos){
		
		xTemp = xPos;
		yTemp = yPos;
		
	}

	
	//getters
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public char getElement()
	{
		return element;
	}
	
	public int getXTemp()
	{
		return xTemp;
	}
	
	public int getYTemp()
	{
		return yTemp;
	}
	
	public void changePosition(char move,Boolean invertFlag)
	{

		if (move == 'w') {

			
				if(invertFlag == false)
				{
					if (xTemp != 0)
					{
						xTemp -= 1;
					}
				}
				
				else xTemp += 1;
			
			
		}

		else if (move == 'a') {

			if(invertFlag == false)
				{
					if(yTemp != 0)
					{
						yTemp -= 1;
					}
				}
				
				else yTemp += 1;
			
				
		}

		else if (move == 's') {

			if(invertFlag == false)
			xTemp += 1;
			else {
				if(xTemp != 0)
				xTemp -= 1;
			}
		}

		else if (move == 'd') {

			if(invertFlag == false)
			yTemp += 1;
			else {
				if(yTemp !=0)
				yTemp -= 1;
			}
		}
		
		
	}
	
	
	
	
	
}
