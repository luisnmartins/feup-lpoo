package dkeep.logic;

public class Level2Map implements Map {

	private char[][] map = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	
	public char getElement(int x, int y)
	{
		return map[x][y];
	}
	
	public Boolean ValidPosition(int x, int y)
	{

		if(map[x][y] == ' ')
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
	
	
}
