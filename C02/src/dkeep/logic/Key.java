package dkeep.logic;

public class Key {

	
	private int x;
	private int  y;
	private char symbol;
	private boolean found;
	private int type;	
	
	public Key(int x, int y, char symbol, int type)
	{
		this.x = x;
		this.y = y;
		this.symbol = symbol;
		this.found = false;
		this.type = type;
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

	public int getType()
	{
		return type;
	}
	
}
