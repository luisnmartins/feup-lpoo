package dkeep.logic;

import java.util.ArrayList;
import java.util.List;

public class OgreLevel extends Level{
	
	
	List<Ogre> Ogres = new ArrayList<Ogre>();
	
	public OgreLevel()
	{
		Map dungeonmap = new Level2Map();
		this.setMap(dungeonmap);
		this.setHero(7, 1);
		this.setKey(1, 7);
		
	}
	
	
	
	
	
	//TODO criar o update game para os varios niveis
	public boolean UpdateGame()
	{
		return true;
	}
	
	
	//TODO update Ogre movement
	public void updateOgre()
	{
		
	}


}
