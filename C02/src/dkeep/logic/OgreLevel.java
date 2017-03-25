package dkeep.logic;


public class OgreLevel extends Level implements java.io.Serializable{
		

	
	/**
	 * Static variable used to do serialization, so the game is saved
	 */
	private static final long serialVersionUID = 1L;


	
	public OgreLevel(Map mymap, int howmanyOgres)
	{
		
		super(mymap, howmanyOgres, 0);
		mymap.getKey().setIsaKey(true);
	}
	
	
	public OgreLevel(Map mymap)
	{
		super(mymap, 0, 0);
	}
		

	/**
	 * Returns the next level after the OgreLevel(since there is none it returns null)
	 * @param enemienbm
	 * @return null because there is no level after the Ogre Level
	 */
	public Level nextLevel(int enemienmb)
	{
		return null;
	}
	

}
