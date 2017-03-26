package dkeep.logic;

public class SuspiciousGuard extends Guard implements java.io.Serializable{
	
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a Guard of the Suspicious type(the one that randomly starts walking in the opposite direction)
	 * @param xP  Suspicious's matrix position - Line
	 * @param yP Suspicious's matrix position - Column
	 * @param Elm Char that represents the Suspicious in the matrix
	 */
	public SuspiciousGuard(int xP, int yP, char Elm)
	{
		super(xP,  yP,  Elm);
	}
	
	
	@Override
	public char getMoveGuard()
	{
		
		if(characteristic == true)
		{
			char move = moveLevel1[iterator];
			if(iterator == 0)
			{
				iterator = moveLevel1.length-1;
			}else 
				iterator--;
			
			return move;
		}
		else
			return super.getMoveGuard();
	}
	
	/**
	 * Updates the guard characteristic and then Updates the guard position by using his stored move set and checking if his characteristic is activated,in case that characteristic is important to the guard move turn
	 */
	public boolean update(Map currentmap, char heromove)
	{
		
		this.updateCharacteristic();
		super.update(currentmap, heromove);
		return true;
	}

}
