package dkeep.logic;

public class SuspiciousGuard extends Guard{
	
	
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
	
	public boolean update(Map currentmap, char heromove)
	{
		
		this.updateCharacteristic();
		super.update(currentmap, heromove);
		return true;
	}

}
