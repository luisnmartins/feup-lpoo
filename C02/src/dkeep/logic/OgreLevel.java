package dkeep.logic;


public class OgreLevel extends Level implements java.io.Serializable{
		

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public OgreLevel(Map mymap, int howmanyOgres)
	{
		
		super(mymap, howmanyOgres, 0);
		mymap.getKey().setIsaKey(true);
	}
		

	
	public Level nextLevel(int enemienmb)
	{
		return null;
	}
	

}
