package dkeep.logic;

public class Level2Map extends Map {

	private static char[][] mapStatic = {  { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
											{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, 
											{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
											{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
											{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
											{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	
	public Level2Map()
	{
		super(mapStatic);
	}


	

	public char[][] getMap() {
		// TODO Auto-generated method stub
		return this.map;
	}
	
	
	
	
	
}
