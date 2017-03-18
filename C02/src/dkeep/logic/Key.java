package dkeep.logic;

public class Key {

	
	private int x;
	private int  y;
	private char symbol;
	private boolean found;	
	private boolean isKey=false; //1 - Level, 2 - key
	
	public Key(int x, int y, char symbol)
	{
		this.x = x;
		this.y = y;
		this.symbol = symbol;
		this.found = false;
		
	}
	

	public boolean getIsaKey()
	{
		return this.isKey;
	}
	
	public void setIsaKey(boolean value)
	{
		this.isKey = value;
	}
	
	public boolean getFound()
	{
		return found;
	}
	
	
	public void setFound()
	{
		found = true;
		this.symbol = ' ';
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}

	
	public boolean isOnTop(int xPos, int yPos)
	
	{
		if(!this.getFound())
		if(this.x == xPos && this.y == yPos)
		{
			return true;
		}else 
			return false;
		else 
			return false;
	}


	public char getSymbol() {
		return symbol;
	}


	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	
}
