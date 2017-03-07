package dkeep.logic;


public class GameMap implements Map {
		
	char[][] map;
	
	
	public GameMap(char[][] map)
	{
		this.map = map;
		
	}
	
	public char getElement(int x, int y)
	{
		return map[x][y];
	}
	
	public Boolean moveTo(int x, int y)
	{

		if(map[x][y] == 'X' || map[x][y] == 'I')
			return true;
		else
			return false;
	}
	
	public void ClearPosition(int x, int y)
	{
		map[x][y] = ' ';
	}
	
	
	public char[][] getMap()
	{
		return map;
	}
	
	public void setMapPosition(int x,int y, char element)
	{
		map[x][y] = element;
	}
	
	//TODO
	public void openDoors()
	{
		
	}
	
	GameMap nextMap(int level)
	{
		GameMap newmap;
		
		if(level == 2)
			 newmap = new Level2Map();
		else
			newmap = new Level1Map();
		
		
		return newmap;
	}

}
