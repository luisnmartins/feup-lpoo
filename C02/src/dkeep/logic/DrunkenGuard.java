package dkeep.logic;


public class DrunkenGuard extends Guard{

	public DrunkenGuard(int xP, int yP, char Elm)
	{
		super(xP, yP, Elm);
	}
	
	public char getMoveGuard()
	{
		return super.getMoveGuard();
	}
	
	public boolean update(Map currentmap, char heromove)
	{
		
		this.updateCharacteristic();
		if(this.characteristic)
		{
			this.setElm('g');

		}
		else
			super.update(currentmap, heromove);
		
		return true;
	}
	
}
