package dkeep.logic;



public class Level1Map extends Map {

	private static char[][] mapStatic= {   { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
											{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
											{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
											{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
											{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
											{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	/*private static char[][] mapStatic = {{'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', 'G', 'X'},
			{'I', ' ', ' ', ' ', 'X'},
			{'I', 'k', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};*/
	
	
	
	public Level1Map()
	{
		super(mapStatic);
	}
	
	public void setSaticMap(char[][] newmap)
	{
		mapStatic = newmap;
	}
		
	

	
}
