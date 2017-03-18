package dkeep.logic;

public class Door {

	private int x;
	private int y;
	private char symbol;
	private boolean canOpen;
	
	
	public Door(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.symbol = 'I';
		this.canOpen = false;
		 
	}
	
	public void OpenDoor()
	{
		this.setSymbol('S');
	}
	
	public boolean IsOpened()
	{
		if(this.getSymbol() == 'S')
			return true;
		else
			return false;
	}
	
	
	//Level2
	
	public void setcanOpen(boolean value)
	{
		canOpen = true;
	}
	
	
	
	public boolean getcanOpen()
	{
		return canOpen;
	}
	
	
	
	public boolean doorAchieved(int xPos, int yPos)
	{
		if(xPos == this.getX() && yPos == this.getY())	
			return true;
		else
			return false;
	}
	
	/*public boolean IsAdjacent(Character car)
	{
		if((car.getX() == this.getX() && Math.abs(car.getY()-this.getY()) == 1) || (car.getY() == this.getY() && Math.abs(car.getX()-this.getX()) == 1))
		{
			return true;
		}
		else
			return false;
			
	}*/

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

	public char getSymbol() {
		return symbol; 
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	
	
	
	
	
}
