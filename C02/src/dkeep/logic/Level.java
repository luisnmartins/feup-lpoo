package dkeep.logic;


public class Level {
	
	private Map currentmap;
	private Hero myHero;
	private Key myKey;
	
	
	public  char[][] getMap(){
		
		return currentmap.getMap();
	}
	
	public void setMap(Map currentmap)
	{
		this.currentmap = currentmap;
	}
	
	public void setHero(int x, int y)
	{
		myHero = new Hero(x, y, 'H');
	}
	
	public void setKey(int x, int y){
		
			myKey = new Key(x, y, 'k', this.getLevel());

	}
	
	public int getLevel()
	{
		return 0;
	}
	
	public boolean updateGame()
	{
		return true;
	}
	
	//TODO make hero's update move here
	public void updateHero(char move)
	{
		
	}
	
	
	public Level nextLevel()
	{
		return null;
	}
	
	
	
	
	
	

}
