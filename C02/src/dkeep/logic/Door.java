package dkeep.logic;

public class Door implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private char symbol;
	
	
	/**
	 * Constructor of the door, initializes its core variables, by the default the symbol of the 
	 * door is set to 'I' in order to represent the door closed.
	 * @param x value of the door position in the x axis
	 * @param y value of the door position in the y axis
	 */
	public Door(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.symbol = 'I';
		
		 
	}
	
	/**
	 * "Opens" the door by changing its representaion to an 'S'
	 */
	public void OpenDoor()
	{
		this.setSymbol('S');
	}
	/**
	 * Sees by analyzing the door symbol if the door is open
	 * @return true if the door is open (symbol = 'S') otherwise it returns false
	 */
	public boolean IsOpened()
	{
		if(this.getSymbol() == 'S')
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if the door is at a given position by comparing its variables
	 * @param xPos to check with x variable of the door
	 * @param yPos to check with y variable of the door
	 * @return true if the parameter represents the door position, false othewise
	 */
	public boolean doorAchieved(int xPos, int yPos)
	{
		if(xPos == this.getX() && yPos == this.getY())	
			return true;
		else
			return false;
	}
	

	/**
	 * Gets the x position of the door
	 * @return the x value of the door
	 */
	public int getX() {
		return x;
	}

 /**
 * Gets the y position of the door
 * @return the y value of the door
 */
	public int getY() {
		return y;
	}


	/**
	 * Gets the representation of the door in the game
	 * @return the symbol value of the door
	 */
	public char getSymbol() {
		return symbol; 
	}
	
	/**
	 * Changes the representation of the door to the given parameter
	 * @param symbol that represent the new door symbol
	 */
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	
	
	
	
	
}
