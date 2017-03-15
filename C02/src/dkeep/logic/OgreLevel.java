package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import dkeep.cli.DungeonKeep.state;

public class OgreLevel extends Level{
	
	
	private List<Ogre> Ogres = new ArrayList<Ogre>();
	private boolean moveOgres = true;
	
	public OgreLevel(Map mymap)
	{
		//Map dungeonmap = new Map(2);
		this.setMap(mymap);
		
		//analyze map to get elements and clear elements initial position in the map
				char[][] maptoanalyze = mymap.getMap();
				for(int i=0; i<maptoanalyze.length; i++)
				{
					for(int j=0; j<maptoanalyze.length; j++)
					{
						if(maptoanalyze[i][j] == ' ' || maptoanalyze[i][j] == 'X')
						{}
						
						//create hero
						else if(maptoanalyze[i][j] == 'H')
						{
								myHero = new Hero(i, j, 'A');
								mymap.ClearPosition(i, j);
								
						}
						
						//create key
						else if(maptoanalyze[i][j] == 'k')
						{
							myKey = new Key(i, j, 'k');
							mymap.ClearPosition(i, j);
						}
						
						else if(maptoanalyze[i][j] == 'O')
						{
							int rand = ThreadLocalRandom.current().nextInt(1, 6);
							for(int startRand=0; startRand < rand ; startRand++)
							{
								Ogres.add(new Ogre(i,j,'O','*'));
							}
						}
						
						//create Doors
						else if(maptoanalyze[i][j] == 'I' && (i== 0 || i == maptoanalyze.length || j==0 || j == maptoanalyze[i].length))
						{
							Door mydoor = new Door(i, j);
							doors.add(mydoor);
							mymap.ClearPosition(i, j);
						}
						
					}
				}
		
		

		
	}
	
	
	
	
	@Override
	//TODO criar o update game para os varios niveis
	public state updateGame(char move)
	{
		System.out.println("Level2");
		this.updateHero(move);
		for(int i = 0; i < Ogres.size(); i++){
			if(moveOgres)
				this.updateOgre(Ogres.get(i));
			if(VerifyColisionOgre(Ogres.get(i)))
				return state.LOSE;
		}
				
		if(changeLevel())
			return state.NEXTLEVEL;
		else
			return state.RUNNING;
	}
	
	
	//TODO update Ogre movement
	public void updateOgre(Ogre myOgre)
	{
		if(myOgre.getElement() == '8')
		{
			if(myOgre.getStopOgre() == 0)
			{				
				myOgre.setElm('O');
			}
			else
			{
				myOgre.decStopOgre();
				
				return;
			
			}
		}
		boolean movedFlag = false;
		
		do{		
		
		myOgre.changePosition(myOgre.GenerateOgre(),false);
		int x_temp = myOgre.getXTemp();
		int y_temp = myOgre.getYTemp();
		if(currentmap.moveTo(x_temp, y_temp))
		{
			if(myKey.isOnTop(myOgre))
			{
				myOgre.setElm('$');
			}else  myOgre.setElm('O');
			
			for(int i = 0; i < doors.size();i++)
			{
				if(doors.get(i).doorAchieved(myOgre))
				{
					myOgre.setTempPosition(myOgre.getX(), myOgre.getY());
					movedFlag = true;
					break;
				}
				movedFlag = false;
			}
			myOgre.setPosition(myOgre.getXTemp(), myOgre.getYTemp());
			if(!movedFlag)
			{
				break;
			}
				
			
		}else myOgre.setTempPosition(myOgre.getX(), myOgre.getY());
		}while(true);
		updateOgreClub(myOgre);

		
	}
	
	public void updateOgreClub(Ogre myOgre)
	{
		int[] club;
		boolean stopFlag = false;
		do {
			club = myOgre.ChangeClub();
			if(currentmap.moveTo(club[0], club[1]))
			{
				if(myKey.getX() == club[0] && myKey.getY() == club[1] && !myKey.getFound())
				{
					myOgre.setClubElm('$');
					
				}else myOgre.setClubElm('*');
				for(int i = 0;i < doors.size();i++)
				{
					if(club[0] == doors.get(i).getX() && club[1] == doors.get(i).getY())
					{
						stopFlag = true;
						break;
					}else stopFlag = false;
				}
				if(!stopFlag)
				{
					myOgre.setClub(club[0], club[1]);
					break;
				}
				
			}
		}while(true);
	}

	
	public Boolean VerifyColisionOgre(Ogre myOgre)
	{
			if((myHero.getX() == myOgre.getX() && Math.abs(myHero.getY() - myOgre.getY()) <= 1) || (myHero.getY() == myOgre.getY() && Math.abs(myHero.getX() - myOgre.getX()) <= 1) && myOgre.getElement()  == 'O')
			{
				if(moveOgres)
				{
					myOgre.setStopOgre(2);
					myOgre.setElm('8');
					myOgre.setClubElm(' ');
					return false;
				}
				else
					return true;
				
			}
			else if(((myHero.getX() == myOgre.getAttackX() && Math.abs(myHero.getY() - myOgre.getAttackY()) <= 1) || (myHero.getY() == myOgre.getAttackY() && Math.abs(myHero.getX() - myOgre.getAttackX()) <= 1)) && myOgre.getStopOgre() == 0 && myOgre.getClubElm() == '*')
			{
				return true;
			}
			return false;
	}
	
	
	//TODO update map to send
		public  char[][] getMap()
		{
			
			char[][] maptocopy = currentmap.getMap();
			char[][] maptosend = new char[maptocopy.length][];
			for(int i=0; i< maptocopy.length; i++)
			{ 
				maptosend[i] = Arrays.copyOf(maptocopy[i], maptocopy[i].length);
			}
			
						
			if(myKey.getFound() == false)
			{
			
				maptosend[myKey.getX()][myKey.getY()] = 'k';
			}
			
			maptosend[myHero.getX()][myHero.getY()] = myHero.getElement();
			
			Ogre no;
			for(int i=0; i<Ogres.size(); i++)
			{
				no = Ogres.get(i);
				
				maptosend[no.getAttackX()][no.getAttackY()] = no.getClubElm();
				maptosend[no.getX()][no.getY()] = no.getElement();
				
			}
			for(int i=0; i<doors.size(); i++)
			{
				maptosend[doors.get(i).getX()][doors.get(i).getY()] = doors.get(i).getSymbol();
			}

			
			
			
			return maptosend; 
			
		}
		
		
		
		public void NotMoveElements()
		{
			moveOgres = false;
		}
		
		public Ogre getFirstOgre()
		{
			return this.Ogres.get(0);
		}
	

}
