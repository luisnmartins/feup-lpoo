
package dkeep.logic;

public class Level2Map extends Map implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static char[][] mapStatic = {  { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
											{ 'I', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X' }, 
											{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
											{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
											{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, 
											{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	/**
	 * Creates a Map that represents the default OgreLevel
	 */
	public Level2Map()
	{
		super(mapStatic);
	} 

	/**
	 *  Updates the matrix map of the OgreLevel map to the given parameter
	 *  @param newmap the new map that represents the OgreLevel map
	 */
	public void setSaticMap(char[][] newmap)
	{
		mapStatic = newmap;
	}

	
}
