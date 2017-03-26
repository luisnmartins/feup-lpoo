package dkeep.logic;

public class RookieGuard extends Guard implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;

	
	/**
	 * Creates a Guard of the Rookie type(its the normal guard without any characteristic to distinguish him)
	 * @param xP  Rookie's matrix position - Line
	 * @param yP Rookie's matrix position - Column
	 * @param Elm Char that represents the Rookie in the matrix
	 */
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
