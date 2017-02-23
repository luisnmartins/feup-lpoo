package dkeep.logic;

public class Character {

	private int x;
	private int y;
	private char element;
	
	
	//constructor
	public Character(int xPos, int yPos, char elm)
	{
		x = xPos;
		y = yPos;
		element = elm;
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
	
}
