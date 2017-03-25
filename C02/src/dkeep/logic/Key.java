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
	 * Sets the IsaKey value to the given parameter (IsaKey is a flag that checks if the key is really a key or just a lever)
	 * @param value the new value of IsaKey
	 */
	public void setIsaKey(boolean value)
	{
		this.isKey = value;
	}
	
	/**
	 * Gets the variable that shows if the key has been found
	 * @return found variable
	 */
	public boolean getFound()
	{
		return found;
	}
	
	/**
	 * Sets the found variable to true and the key symbol to ' ' so that means that the key has been found
	 * by the hero ( this is only useful when the key is really a key and not a lever)
	 */
	public void setFound()
	{
		found = true;
		this.symbol = ' ';
	}

	/**
	 * Gets the x position of the key
	 * @return the x variable
	 */
	public int getX() {
		return x;
	}


	
	/**
	 * Gets the y position of the key
	 * @return the y variable
	 */
	public int getY() {
		return y;
	}


	

	/**
	 * Checks if ,the key has not been already found, the key is at the given position
	 * @param xPos position to compare to the x of the key
	 * @param yPos position to compare to the y of the key
	 * @return true if the given position corresponds to the key position(and key is not yet found) otherwise returns false
	 */
	public boolean isOnTop(int xPos, int yPos)
	
	{
		if(!this.getFound())
		if(this.x == xPos && this.y == yPos)
		{
			return true;
		}
		return false;
	}

	/**
	 * Gets the key representation in the map
	 * @return the symbol variable
	 */
	public char getSymbol() {
		return symbol;
	}

	
	
	
}
