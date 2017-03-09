package dkeep.logic;

public class Level2Map extends Map {

	private static char[][] map = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	
	public Level2Map()
	{
		super(map);
	}


	
	public char getElement(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	public Boolean moveTo(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}


	
	public void openDoors() {
		// TODO Auto-generated method stub
		
	}


	
	public char[][] getMap() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
