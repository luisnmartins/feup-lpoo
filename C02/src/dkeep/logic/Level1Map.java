
package dkeep.logic;



public class Level1Map extends Map implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static char[][] mapStatic= {   	{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
											{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
											{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
											{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
											{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
											{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	/**
	 * Creates a Map that represents the default DungeonLevel
	 */
	public Level1Map()
	{
		super(mapStatic);
	}
	
	/**
	 * Updates the matrix map of the DungeonLevel map to the given parameter
	 * @param newmap the new map that represents the DungeonLevel map
	 */
	public void setSaticMap(char[][] newmap)
	{
		mapStatic = newmap;
	}
		
	

	
}

