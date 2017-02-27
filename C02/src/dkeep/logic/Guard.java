package dkeep.logic;

public class Guard extends Character {

	private char moveLevel1[] = { 'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd',
			'd', 'w', 'w', 'w', 'w', 'w', 'w' };
	
	private int iterator;
	
	public Guard(int xP, int yP, char Elm, int it)
	{
		super(xP, yP, Elm);
		iterator = it;
	}
	
	public char getMoveGuard()
	{
		char move = moveLevel1[iterator];
		iterator++;
		return move;
		
	}

	
	
	
}
