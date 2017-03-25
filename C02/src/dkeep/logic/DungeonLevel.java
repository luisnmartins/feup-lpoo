package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;




public class DungeonLevel extends Level implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public DungeonLevel(Map mymap, int Guardtype)
	{
		super(mymap, 0, Guardtype);		
	}
	
	

	
	public Level nextLevel(int enemienmb)
	{
		
		Map nextmap = new Level2Map();
		if(enemienmb == -1)
			enemienmb = ThreadLocalRandom.current().nextInt(1, 6);
		
		return new OgreLevel(nextmap, enemienmb);
	}
	
	
	
	


	

}
