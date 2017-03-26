package dkeep.logic;


import java.util.ArrayList;
import java.util.Arrays;


public abstract class Level implements java.io.Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map currentmap;
	protected Hero myHero;	
	protected ArrayList<Character> enemies = new ArrayList<Character>();
	public enum state{ RUNNING, WIN, LOSE, NEXTLEVEL, CHANGELEVEL};
	
	/**
	 * Constructs a Level, setting the current map and the other elements (Hero, Enemies - Ogres/Guard, Doors and key)
	 * @param currentmap Map to use during the level
	 * @param Ogrenmb Number of Ogres that will exist during the 2nd Level
	 * @param Guardtype Guard's Type to be used in the 1st Level ( 1 - Drunken , 2 - Suspicious, 3 - Rookie)
	 */
	public Level(Map currentmap, int Ogrenmb, int Guardtype)
	{
		this.currentmap = currentmap;
		InitalizeElements(Ogrenmb, Guardtype);
			
	}
	

	/**
	 * Runs the map matrix and set the elements (Hero, Enemies - Ogres/Guard, Doors and key)
	 * @param Ogrenmb Number of Ogres to be generated
	 * @param Guardtype Guard's Type to be used in the 1st Level ( 1 - Drunken , 2 - Suspicious, 3 - Rookie)
	 */
	public void InitalizeElements(int Ogrenmb, int Guardtype)
	{
		
		char[][] analyzemap = currentmap.getMap();
		
		for(int i=0; i<analyzemap.length; i++)
		{
			for(int j=0; j<analyzemap.length; j++)
			{
				switch(analyzemap[i][j])
				{
					case 'H':
						myHero = new Hero(i, j, 'H');
						currentmap.ClearPosition(i,j);
						break;
						
					case 'O':
						for(int startRand=0; startRand < Ogrenmb; startRand++)
							enemies.add(new Ogre(i,j,'O','*'));
						currentmap.ClearPosition(i,j);
						break;
						
					case 'k':
						currentmap.setKey(i, j);
						currentmap.ClearPosition(i,j);
						break;
						
					case 'G':
						
						Guard myGuard = new Guard(i, j, 'G');
						myGuard = myGuard.getGuardType(Guardtype, i, j, 'G');
						enemies.add(myGuard);
						currentmap.ClearPosition(i,j);
						break;
						
					case 'I':
						if(i== 0 || i == analyzemap.length-1 || j==0 || j == analyzemap[i].length-1)
							currentmap.setDoor(i, j);
						currentmap.ClearPosition(i,j);
						break;
						
					default: break;
				}
				
			}
		}
	}
	
	

	
	/**
	 * Updates all the Game elements verifying collisions
	 * @param Move WASD key representing the hero's movement
	 * @return Returns the state enum of the game State (Running, NextLevel or Lose) 
	 */
	public state updateGame(char move)
	{
		
		myHero.update(currentmap, move, enemies);
		for(int i=0; i<enemies.size(); i++)
		{
			enemies.get(i).update(currentmap, ' ');
			if(enemies.get(i).verifyColision(myHero))
				return state.LOSE;
		}
		
		if(this.changeLevel())
			return state.NEXTLEVEL;
		else
			return state.RUNNING;
		
		
	}
	
	
	/**
	 * Gets the actual hero
	 * @return Returns the hero
	 */
	public Hero getHero()
	{
		return myHero;
	}
	

	/**
	 * Verifies if it's time to change to the next Level
	 * @return Returns true if it's supposed to move to the next Level, and false if not
	 */
	public boolean changeLevel()
	{
		if(currentmap.IsOveraDoor(myHero.getX(), myHero.getY()) != -1)
				return true;
		else
			return false;
	}
	
	
	/**
	 * Gives an instance of the next level with the number of enemies (Ogres)
	 * @param enemienmb Number of enemies that should exist in the next level
	 * @return Returns an instance of the new Level
	 */
	public abstract Level nextLevel(int enemienmb);
	
	
	/**
	 * Sets if it's supposed to move enemies during the game or not
	 * @param movement True if it supposed to move enemies and false if not
	 */
	public void IstoMoveElements(boolean movement)
	{
		for(Character c: enemies)
		{
			c.setMoveCharacter(movement);
		}
	}


	/**
	 * Gets a copy of the actual game's matrix without the game's elements and set them on it
	 * @return Returns a char matrix with all the game's elements 
	 */
	public char[][] getMap(){
		
		char[][] maptocopy = currentmap.getMap();
		char[][] maptosend = new char[maptocopy.length][];
		for(int i=0; i< maptocopy.length; i++)
			maptosend[i] = Arrays.copyOf(maptocopy[i], maptocopy[i].length);
		
		if(currentmap.getKey() != null && enemies != null && myHero != null)
		{
			currentmap.addElementsMatrix(maptosend);
			
			for(int i=0; i<enemies.size(); i++)
				enemies.get(i).addElementsMatrix(maptosend);
	
			myHero.addElementsMatrix(maptosend);
		}
		
		return maptosend;
		
	}
	

	
	/**
	 * Gets a copy of the actual matrix game with all the elements (hero, enemies, doors and key) and prints it as a String
	 */
	public void printMap() {
		
		
		char[][] currentmap = this.getMap();
		
		String toprint="";
		
		for(int i=0; i<currentmap.length; i++)
		{
			for(int j=0; j<currentmap[i].length; j++)
			{
				toprint += String.valueOf(currentmap[i][j]);
				toprint += ' ';
			}
			
			toprint += "\n";
		}
			
		
		System.out.print(toprint);
	}
	
	
	/**
	 * Gets the 1st enemy from the enemies' ArrayList - used mainly to get the Guard in the 1st Level
	 * @return Returns the 1st enemy from the enemies' ArrayList
	 */
	public Character getFirstEnemie()
	{
		if(enemies!=null)
			return enemies.get(0);
		else
			return null;
	}
	
	
	/**
	 * Updates the Game Status calling the updateGame function and verifying the return state to print a Status message
	 * @param move WASD char that represents the hero's movement
	 * @return Returns the state that receives from calling updateGame function
	 */
	public state updateGameStatus(char move)
	{
		
		state gameState = this.updateGame(move);
		
		switch(gameState){
		
			case LOSE:

				System.out.println("You Lose!! Try Again");
				break;
			
			case NEXTLEVEL:
			
				Level nextlevel = this.nextLevel(-1);
				if(nextlevel == null){
					
					gameState = state.WIN;
					System.out.println("Congratzz!! You're a Hero!!");
				
				}else{
					
					System.out.println("Go, go, go!! You're in the next level");
					gameState = state.CHANGELEVEL;			
					
				}break;
			
		default:
			break;
		}
		return gameState;
					
	}
	
	
	/**
	 * Gets the Map that represents the current Level
	 * @return Returns the currentMap
	 */
	public Map getCurrentMap()
	{
		return this.currentmap;
	}

	
	
	
	
	

}
