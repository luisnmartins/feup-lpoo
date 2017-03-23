
package dkeep.logic;


public abstract class Character implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private char element;
	private int xTemp;
	private int yTemp;
	private boolean moveCharacter=true;
	private boolean IsParalyzed=false;
	
	
	/*
	 * Construct a Character
	 * @param xPos Character's matrix position - Line
	 * @param yPos Character's matrix position - Column
	 * @param elm  Char that represents the Character in the matrix
	 */
	public Character(int xPos, int yPos, char elm)
	{
		x = xPos;
		y = yPos;
		element = elm;
		xTemp = xPos;
		yTemp = yPos;
	}
	
	
	
	
	/*
	 * Set the new Character's Position
	 * @param xPos Character's matrix Line
	 * @param yPos Character's matrix Column
	 */
	public void setPosition(int xPos, int yPos){
		
		x = xPos;
		y= yPos;
		
	}
	
	/*
	 * Set the new Character's element
	 * @param Elm New Char to use  
	 */	
	public void setElm(char Elm)
	{
		element = Elm;
	}
	
	/*	
	 * Set the new Character's temporary position
	 * @param xPos Character's temporary matrix Line
	 * @param yPos Character's temporary matrix Column
	 */
	public void setTempPosition(int xPos, int yPos){
		
		xTemp = xPos;
		yTemp = yPos;
		
	}
	
	/*
	 * Set If it's to move this Character during the Game
	 * @param value True if it's to move the Character and false if it's not 
	 */
	public void setMoveCharacter(boolean value)
	{
		this.moveCharacter = value;
	}

	
	/*
	 * Get the matrix's Line of the current Character position
	 * @return Returns the Character matrix's Line
	 */
	public int getX()
	{
		return x;
	}
	
	/*
	 * Get the matrix's Column of the current Character position
	 * @return Returns the Character matrix's Column
	 */
	public int getY()
	{
		return y;
	}
	
	/*
	 * Get the Character's current Symbol
	 * @return Returns the Character's current Symbol
	 */
	public char getElement()
	{
		return element;
	}
	
	
	/*
	 * Get the Character's temporary matrix line
	 * @return Returns the Character's temporary matrix line
	 */
	public int getXTemp()
	{
		return xTemp;
	}
	
	/*
	 * Get the Character's temporary matrix column
	 * @return Returns the Character's temporary matrix column
	 */
	public int getYTemp()
	{
		return yTemp;
	}
	
	/*
	 * Get the boolean value of if it's to move this Character during the Game
	 * @return True if it's to move the Character and false if it's not 
	 */
	public boolean getMoveCharacter()
	{
		return this.moveCharacter;
	}
	
	/*
	 * 
	 */
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
				if (xTemp != 0)
					xTemp -= 1;
			else xTemp += 1;
		}

		else if (move == 'a') {
	
			if(invertFlag == false)
					if(yTemp != 0)
						yTemp -= 1;
			
			else yTemp += 1;		
		}
	
		else if (move == 's') {
	
			if(invertFlag == false)
			xTemp += 1;
			else
				if(xTemp != 0)
					xTemp -= 1;
		}
	
		else if (move == 'd') {
	
			if(invertFlag == false)
				yTemp += 1;
			else 
				if(yTemp !=0)
					yTemp -= 1; 
			
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

