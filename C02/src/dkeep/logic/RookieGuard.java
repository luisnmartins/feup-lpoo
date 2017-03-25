package dkeep.logic;

public class RookieGuard extends Guard implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RookieGuard(int xP, int yP, char Elm)
	{
		super(xP, yP, Elm);
	}
	

	
	public boolean update(Map currentmap, char heromove)
	{
		super.update(currentmap, heromove);
		return true;
	}
	
	

	
}
