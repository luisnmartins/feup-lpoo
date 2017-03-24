package dkeep.logic;

public class Key implements java.io.Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int  y;
	private char symbol;
	private boolean found;	
	private boolean isKey=false; //1 - Lever, 2 - key
	
	
	/**
	 * The used constructor of an object of type key the constructor sets the found flag to its default false
	 * because when its created the key is not yet found by the hero
	 * @param x the x position on the matrix -Line
	 * @param y the y position on the matrix - Column
	 * @param symbol the represent key in the matrix - 'k' is default
	 */
	public Key(int x, int y, char symbol)
	{
		this.x = x;
		this.y = y;
		this.symbol = symbol;
		this.found = false;
		
	}
	
	/**
	 * The key can work differently in the various levels, it can work as key itself and as a lever,this function 
	 * return the flag that represents that 
	 * @return false if the key flag is false( if its a lever) or true if it is really a key
	 */
	public boolean getIsaKey()
	{
		return this.isKey;
	}
	
	/**
	 * It changes to 
	 * @param value
	 */
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


	

	public int getY() {
		return y;
	}


	

	
	public boolean isOnTop(int xPos, int yPos)
	
	{
		if(!this.getFound())
		if(this.x == xPos && this.y == yPos)
		{
			return true;
		}
		return false;
	}


	public char getSymbol() {
		return symbol;
	}


	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	
}
