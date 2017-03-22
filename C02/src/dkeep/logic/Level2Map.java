package dkeep.logic;

public class Level2Map extends Map {

	private static char[][] mapStatic = {  { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
											{ 'I', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X' }, 
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

	
	public void setSaticMap(char[][] newmap)
	{
		mapStatic = newmap;
	}

	
}
