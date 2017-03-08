package dkeep.logic;

public class DungeonLevel extends Level{
	
	private Guard myGuard;
	
	
	public DungeonLevel()
	{
		Map dungeonmap = new Level1Map();
		this.setMap(dungeonmap);
		this.setHero(1, 1);
		this.setKey(8, 7);
		myGuard = new Guard(1, 8, 'G');
		
	}
	
	//TODO criar o update game para os varios niveis
	public boolean UpdateGame()
	{
		return true;
	}
	
	//TODO update guard movement
	public void updateGuard()
	{
		
	}
	
	
	public int getLevel()
	{
		return 1;
	}
	
	
	

}
