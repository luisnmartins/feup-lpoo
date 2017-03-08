package dkeep.logic;

public abstract class Map {

	private char[][] map;
	
	public Map(char[][] map)
	{
		this.map = map;
	}
	
	public abstract char getElement(int x, int y);
	
	public abstract Boolean moveTo(int x, int y);
	

	public abstract void ClearPosition(int x, int y);
	
	public abstract void openDoors();
	
	
	public abstract char[][] getMap();

	
	//public abstract void setMapPosition(int x,int y, char element);

	
	
}