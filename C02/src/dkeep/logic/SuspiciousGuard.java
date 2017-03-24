package dkeep.logic;

public class SuspiciousGuard extends Guard implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SuspiciousGuard(int xP, int yP, char Elm)
	{
		super(xP,  yP,  Elm);
	}
	
	
	@Override
	public char getMoveGuard()
	{
		
		if(characteristic == true)
		{
			System.out.println("susp:"+iterator+"\n");
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
	
	public boolean update(Map currentmap, char heromove)
	{
		
		this.updateCharacteristic();
		super.update(currentmap, heromove);
		return true;
	}

}
