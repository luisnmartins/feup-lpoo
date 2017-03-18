package dkeep.logic;

public class RookieGuard extends Guard{

	public RookieGuard(int xP, int yP, char Elm)
	{
		super(xP, yP, Elm);
	}
	
	public char getMoveGuard()
	{
		return super.getMoveGuard();
	}
	
	public boolean update(Map currentmap, char heromove)
	{
		super.update(currentmap, heromove);
		return true;
	}
	
	

	
}
