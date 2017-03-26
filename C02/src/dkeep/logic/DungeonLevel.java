package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;




public class DungeonLevel extends Level implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Creates a level of type DungeonLevel,setting the map that defines the level and the type of guard that appears in the level
	 * @param mymap the map where the level is played on
	 * @param Guardtype the type of guard is created in the game (1-Drunken; 2-Suspicious; 3-Rookie)
	 */
	public DungeonLevel(Map mymap, int Guardtype)
	{
		super(mymap, 0, Guardtype);		
	}
	
	

	/**
	 * Defines and creates the next level after the DungeonLevel, wich is a Level of type OgreLevel
	 * @param enemienmb the number of ogres created on the OgreLevel (-1 if the number of ogres created is random)
	 * @return the new OgreLevel created
	 */
	public Level nextLevel(int enemienmb)
	{
		
		Map nextmap = new Level2Map();
		if(enemienmb == -1)
			enemienmb = ThreadLocalRandom.current().nextInt(1, 6);
		
		return new OgreLevel(nextmap, enemienmb);
	}
	
	
	
	


	

}
