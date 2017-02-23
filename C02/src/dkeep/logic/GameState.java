package dkeep.logic;

public class GameState {

	private char[][] map = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	private char[][] map2 = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	private Hero myHero;
	private Ogre myOgre;
	private Guard myGuard;
	int level;
	
	
	public void startGame()
	{
		myHero = new Hero(1, 1, 'H');
		//Monster = new Ogre(1, 4, 'O', '*');
		myGuard = new Guard(1, 8, 'G');
		level = 1;
		
	}
	
	public void UpdateHero(char move)
	{
		changePosition();
		
	}
	
	public void UpdateGuard()
	{
		
	}
	
	public void UpdateOgre()
	{
		
	}
	
	
	public Boolean VerifyPosition(Character piece)
	{
		
		if (map[piece.getX()][piece.getY()] == 'I' || map[piece.getX()][piece.getY()] == 'X')
		{
			return false;
		}
		
		if(piece instanceof Hero)
		{
			if(map[piece.getX()][piece.getY()] == 'k')
				piece.set
		}
		
		
			
		return true;
	}
	
	public void changePosition(char move, Character piece)
	{
		int x = piece.getX();
		int y = piece.getY();
		
		if (move == 'w') {

			if (x != 0)
				 x -= 1;
		}

		else if (move == 'a') {

			if (y != 0)
				y -= 1;
		}

		else if (move == 's') {

			x += 1;
		}

		else if (move == 'd') {

			y += 1;
		}
		
		
	}
	
	
	
	public int getLevel()
	{
		return level;
	}
	
	

	
	
	
	
	
	
	
	
}
