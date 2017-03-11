package dkeep.logic;

public class Map {
	
	
	/*private static char[][] levelOneMap= {  { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
											{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
											{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
											{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
											{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
											{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };*/
	
	/*private static char[][] levelTwoMap = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };*/

	
	protected char[][] map;
	
	public Map(char[][] map)
	{
		
		this.map = map;
		/*if(level == 1)
		{
			this.map = levelOneMap;
		}else if (level == 2)
		{
			this.map = levelTwoMap;
		}*/
	}
	
	public char getElement(int x, int y) {
		return this.map[x][y];
	}
	
	public  Boolean moveTo(int x, int y) {
		if(map[x][y] == 'X' || map[x][y] == 'I')
			return false;
		else
			return true;
	}
	

	public void ClearPosition(int x, int y)
	{
		map[x][y]  = ' ';
	}
	
	
	
	public char[][] getMap()
	{
		return this.map;
	};

	
	//public abstract void setMapPosition(int x,int y, char element);
	
	

	
	
}