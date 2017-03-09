package dkeep.logic;

import java.util.ArrayList;
import java.util.List;

public class OgreLevel extends Level{
	
	
	List<Ogre> Ogres = new ArrayList<Ogre>();
	
	public OgreLevel()
	{
		Map dungeonmap = new Level2Map();
		this.setMap(dungeonmap);
		myHero = new Hero(7, 1, 'H');
		myKey = new Key(1, 7, 'k');

		
	}
	
	
	
	
	@Override
	//TODO criar o update game para os varios niveis
	public int updateGame(char move)
	{
		
		
		return 2;
	}
	
	
	//TODO update Ogre movement
	public void updateOgre()
	{
		
	}


}
