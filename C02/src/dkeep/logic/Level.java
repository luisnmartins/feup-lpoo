package dkeep.logic;


import java.util.ArrayList;
import java.util.Arrays;

import dkeep.logic.Level.state;

public class Level {
	
	protected Map currentmap;
	protected Hero myHero;	
	protected ArrayList<Character> enemies = new ArrayList<Character>();
	public enum state{ RUNNING, WIN, LOSE, NEXTLEVEL, CHANGELEVEL};
	
	
	public Level(Map currentmap, int Ogrenmb, int Guardtype)
	{
		this.currentmap = currentmap;
		InitalizeElements(Ogrenmb, Guardtype);
			
	}
	
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
					{
						myHero = new Hero(i, j, 'H');
						//characters.add(myHero);
						currentmap.ClearPosition(i,j);
						break;
					}
					case 'O':
					{
						for(int startRand=0; startRand < Ogrenmb; startRand++)
						{
							enemies.add(new Ogre(i,j,'O','*'));
						}
						currentmap.ClearPosition(i, j);
						break;
					}
					case 'k':
					{
						
						currentmap.setKey(i, j);
						currentmap.ClearPosition(i, j);
						break;
					}
					case 'G':
					{
						
						Guard myGuard = new Guard(i, j, 'G');
						myGuard = myGuard.getGuardType(Guardtype, i, j, 'G');
						enemies.add(myGuard);
						currentmap.ClearPosition(i, j);
						break;
					}
					case 'I':
					{
						if(i== 0 || i == analyzemap.length || j==0 || j == analyzemap[i].length)
						{
							currentmap.setDoor(i, j);
							currentmap.ClearPosition(i, j);
						}
						else{}
						break;
					}
					default: break;
				}
			}
		}
	}
	
	

	

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
	
	public Hero getHero()
	{
		return myHero;
	}
	
	/*//TODO make hero's update move here
	public void updateHero(char move)
	{		
		myHero.changePosition(move, false);
		int x_temp = myHero.getXTemp();
		int y_temp = myHero.getYTemp();
		boolean canotleave=false;
		if(currentmap.moveTo(x_temp, y_temp))
		{
			for(int i=0; i<doors.size(); i++)
			{
				if(doors.get(i).doorAchieved(myHero) && doors.get(i).IsOpened()==false)
				{
					if(myKey.getFound())
					{
						doors.get(i).OpenDoor();
							
					} 
					myHero.setTempPosition(myHero.getX(), myHero.getY());
					canotleave = true;
					break;
				}
			}
			
			
			if(myKey.isOnTop(myHero))
			{
				if(this instanceof DungeonLevel)
				{
					for(int i=0; i<doors.size(); i++)
					{
						doors.get(i).OpenDoor();
					}
				}
				else if(this instanceof OgreLevel)
				{
					
					myKey.setFound();
					myHero.setElm('K');
				}
					
			}
			if(!canotleave)
				myHero.setPosition(x_temp, y_temp);
		}
		else
			myHero.setTempPosition(myHero.getX(), myHero.getY());
			
		
		
	}*/
	
	
	public boolean changeLevel()
	{
		if(currentmap.IsOveraDoor(myHero.getX(), myHero.getY()) != -1)
				return true;
		else
			return false;
	}
	
	
	public Level nextLevel(int enemienmb)
	{
		return null;
	}
	

	public void IstoMoveElements(boolean movement)
	{
		for(Character c: enemies)
		{
			c.setMoveCharacter(movement);
		}
	}


	public char[][] getMap(){
		
		char[][] maptocopy = currentmap.getMap();
		char[][] maptosend = new char[maptocopy.length][];
		for(int i=0; i< maptocopy.length; i++)
		{
			maptosend[i] = Arrays.copyOf(maptocopy[i], maptocopy[i].length);
		}
		currentmap.addElementsMatrix(maptosend);
		
		
		for(int i=0; i<enemies.size(); i++)
		{
			enemies.get(i).addElementsMatrix(maptosend);
			
		}	
		myHero.addElementsMatrix(maptosend);
		
		return maptosend;
		
	};
	
	
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
	
	
	
	public Character getFirstEnemie()
	{
		if(enemies!=null)
			return enemies.get(0);
		else
			return null;
	}
	
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

	
	
	
	
	

}
