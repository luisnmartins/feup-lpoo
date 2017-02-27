package dkeep.logic;

public class Character {

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
	
	public void setX(int xPos){
		
		x = xPos;
		
	}
	public void setY(int yPos)
	{
		y = yPos;
	}
	public void setElm(char Elm)
	{
		element = Elm;
	}
	
	
	public void setXTemp(int xPos){
		
		xTemp = xPos;
		
	}
	public void setYTemp(int yPos)
	{
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
	
	public void changePosition(char move)
	{

		if (move == 'w') {

			if (xTemp != 0)
				 xTemp -= 1;
		}

		else if (move == 'a') {

			if (yTemp != 0)
				yTemp -= 1;
		}

		else if (move == 's') {

			xTemp += 1;
		}

		else if (move == 'd') {

			yTemp += 1;
		}
		
		
	}
	
	
	
	
}
