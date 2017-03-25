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
	 * Construct a Level setting the map and the other elements
	 * @param currentmap Map to use during the level
	 * @param Ogrenmb Number of Ogres that will exists during the 2nd Level
	 * @param Guardtype Guard's Type to be used in the 1st Level ( 1 - Drunken , 2 - Suspicious, 3 - Rookie)
	 */
	public Level(Map currentmap, int Ogrenmb, int Guardtype)
	{
		this.currentmap = currentmap;
		InitalizeElements(Ogrenmb, Guardtype);
			
	}
	

	/**
	 * Runs the map matrix and sets the elements (Hero, enemies - Ogres/Guard, Doors and key)
	 * @param Ogrenmb That of Ogres to be generated
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
	 * Updates all the Game elements verifying even colisions
	 * @param Move char WASD that will represent the hero's movemnet
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
	 * Get the 
	 * @return
	 */
	public Hero getHero()
	{
		return myHero;
	}
	

	/**
	 * Verifies if it's time to change to the next Level
	 * @return Returns true if it's supposed to move to the next Level, and false if it's not
	 */
	public boolean changeLevel()
	{
		if(currentmap.IsOveraDoor(myHero.getX(), myHero.getY()) != -1)
				return true;
		else
			return false;
	}
	
	
	/**
	 * Function that give an instance of the next level with enemiemb number of enemies (Ogres)
	 * @param enemienmb Number of enemies that should exist in the next level
	 * @return Returns an instance of the new Level
	 */
	public abstract Level nextLevel(int enemienmb);
	
	
	/**
	 * 
	 * @param movement
	 */
	public void IstoMoveElements(boolean movement)
	{
		for(Character c: enemies)
		{
			c.setMoveCharacter(movement);
		}
	}


	/**
	 * 
	 * @return
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
	 * 
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
	 * 
	 * @return
	 */
	public Character getFirstEnemie()
	{
		if(enemies!=null)
			return enemies.get(0);
		else
			return null;
	}
	
	
	/**
	 * 
	 * @param move
	 * @return
	 */
	public state updateGameStatus(char move)
	{
		
		state gameState = this.updateGame(move);
		
		switch(gameState)
		{
			case LOSE:
			{
				System.out.println("You Lose!! Try Again");
				break;
			}
			case NEXTLEVEL:
			{
				Level nextlevel = this.nextLevel(-1);
				if(nextlevel == null)
				{
					gameState = state.WIN;
					System.out.println("Congratzz!! You're a Hero!!");
				}
				else
				{
					
					System.out.println("Go, go, go!! You're in the next level");
					gameState = state.CHANGELEVEL;			
					
				}
				break;
			}
		default:
			break;
		}
		return gameState;
					
	}
	
	
	/**
	 * Get the Map that represents the current Level
	 * @return
	 */
	public Map getCurrentMap()
	{
		return this.currentmap;
	}

	
	
	
	
	

}
