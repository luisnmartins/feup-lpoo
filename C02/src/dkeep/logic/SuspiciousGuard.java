package dkeep.logic;

public class SuspiciousGuard extends Guard{
	
	
	public SuspiciousGuard(int xP, int yP, char Elm)
	{
		super(xP,  yP,  Elm);
	}
	
	public char getMoveGuard()
	{
		this.updateCharacteristic();
		if(characteristic)
		{
			char move = moveLevel1[iterator];
			if(iterator == 0)
			{
				iterator = moveLevel1.length-1;
			}else iterator--;
			return move;
		}
		else
			return super.getMoveGuard();
	}

}
