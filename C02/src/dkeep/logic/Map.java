package dkeep.logic;

public abstract class Map {

	protected char[][] map;
	
	public Map(char[][] map)
	{
		this.map = map;
	}
	
	public abstract char getElement(int x, int y);
	
	public abstract Boolean moveTo(int x, int y);
	

	//public abstract void ClearPosition(int x, int y);
	
	
	
	public abstract char[][] getMap();

	
	//public abstract void setMapPosition(int x,int y, char element);
	
	

	
	
}