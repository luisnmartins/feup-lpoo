package dkeep.logic;

public class Key {

	private int x;
	private int  y;
	private char symbol;
	private boolean found;
	
	
	public Key(int x, int y, char symbol)
	{
		this.x = x;
		this.y = y;
		this.symbol = symbol;
		this.found = false;
		
	}
	

	public boolean getFound()
	{
		return found;
	}
	
	
	public void setFound()
	{
		found = true;
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
	
	
}
