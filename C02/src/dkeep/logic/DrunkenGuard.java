package dkeep.logic;


public class DrunkenGuard extends Guard implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
			this.setIsParalyzed(true);

		}
		else
			super.update(currentmap, heromove);
		
		return true;
	}
	
	public boolean verifyColision(Character c)
	{
		if(this.getElement() == 'g')
		{
			return false;
		}
		else
			return super.verifyColision(c);
	}
	
}
