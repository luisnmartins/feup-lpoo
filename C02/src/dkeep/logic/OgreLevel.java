package dkeep.logic;


public class OgreLevel extends Level implements java.io.Serializable{
		

	
	/**
	 * Static variable used to do serialization, so the game is saved
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Creates a level of type OgreLevel, setting the map that defines the level and the number of enemies of type Ogre is in it
	 * @param mymap the map that the level is played on
	 * @param howmanyOgres the number of ogres that appear in the level
	 */
	public OgreLevel(Map mymap, int howmanyOgres)
	{
		
		super(mymap, howmanyOgres, 0);
		mymap.getKey().setIsaKey(true);
	}
	
	/**
	 * Creates a level of type OgreLevel, without any Ogres
	 * @param mymap the map that the level is played on
	 */
	public OgreLevel(Map mymap)
	{
		super(mymap, 0, 0);
	}
		

	/**
	 * Returns the next level after the OgreLevel(since there is none it returns null)
	 * @param enemienmb (unused variable) since it returns null
	 * @return null because there is no level after the Ogre Level
	 */
	public Level nextLevel(int enemienmb)
	{
		return null;
	}
	

}
