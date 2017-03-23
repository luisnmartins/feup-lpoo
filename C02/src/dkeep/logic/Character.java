
package dkeep.logic;


public abstract class Character implements java.io.Serializable{

	private int x;
	private int y;
	private char element;
	private int xTemp;
	private int yTemp;
	private boolean moveCharacter=true;
	private boolean IsParalyzed=false;
	
	
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
	
	public void setMoveCharacter(boolean value)
	{
		this.moveCharacter = value;
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
	
	public boolean getMoveCharacter()
	{
		return this.moveCharacter;
	}
	
	//Update character position
	public boolean update(Map currentmap, char move){
		
		if(this.getMoveCharacter())
		{
			this.changePosition(move,false);
			return true;
		}
		else
			return false;
	}
	
	public boolean verifyColision(Character c)
	{
		if((c.getX() == this.getX() && Math.abs(c.getY() - this.getY()) <= 1) || (c.getY() == this.getY() && Math.abs(c.getX() - this.getX()) <= 1))
			return true;
		else
			return false;
	}
	
	
	//Change x and y position
	public void changePosition(char move,boolean invertFlag)
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
		
		
	
	
	
	public void addElementsMatrix(char[][] map)
	{
		map[this.getX()][this.getY()] = this.getElement();
	}



	public boolean getIsParalyzed() {
		return IsParalyzed;
	}



	public void setIsParalyzed(boolean isParalyzed) {
		IsParalyzed = isParalyzed;
	}
	
	
	
	
}

