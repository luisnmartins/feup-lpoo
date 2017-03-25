package dkeep.logic;


public class DrunkenGuard extends Guard implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor of a DrukenGuard, it uses the same constructor of it parent class, Guard
	 * @param xP the DrunkenGuard matrix position - Line
	 * @param yP the DrunkenGuard matrix position - Column
	 * @param Elm Char that represents the Guard in the matrix
	 */
	public DrunkenGuard(int xP, int yP, char Elm)
	{
		super(xP, yP, Elm);
	}
	
	/**
	 * It uses the function getMoveGuard of the parent class (see Guard, getMoveGuard documentation)
	 * @return the result of getMoveGuard of the class Guard
	 */
	public char getMoveGuard()
	{
		return super.getMoveGuard();
	}
	
	/**
	 * Updates the guard characteristic and updates the Drunken symbol ('g') in case the characteristic is activated accordingly
	 * In case the characteristic is not activated the function updates the guard position by using his stored move set 
	 * 
	 */
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
	
	
	/**
	 * @return true if the two character are next to eachother (or in same pos) else if the Drunken characteristic is activated return false
	 */
	public boolean verifyColision(Character c)
	{
		if(this.characteristic)
		{
			return false;
		}
		else
			return super.verifyColision(c);
	}
	
}
