package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OgreLevel extends Level{
	
	
	List<Ogre> Ogres = new ArrayList<Ogre>();
	
	public OgreLevel()
	{
		Map dungeonmap = new Map(2);
		this.setMap(dungeonmap);
		myHero = new Hero(7, 1, 'H');
		myKey = new Key(1, 7, 'k');
		int i = ThreadLocalRandom.current().nextInt(1, 6);
		for(int j=0; j < i ; j++)
		{
			Ogres.add(new Ogre(1,4,'O','*'));
		}

		
	}
	
	
	
	
	@Override
	//TODO criar o update game para os varios niveis
	public int updateGame(char move)
	{
		this.updateHero(move);
		for(int i = 0; i < Ogres.size(); i++){
			this.updateOgre(Ogres.get(i));
			if(VerifyColisionOgre(Ogres.get(i)))
				return 1;
		}
		
		
		if(changeLevel())
			return 2;
		else
			return 0;
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
		myOgre.changePosition(myOgre.GenerateOgre(),false);
		int x_temp = myOgre.getXTemp();
		int y_temp = myOgre.getYTemp();
		if(currentmap.moveTo(x_temp, y_temp))
		{
			if(myKey.isOnTop(myOgre))
			{
				myOgre.setElm('$');
			}else  myOgre.setElm('O');
			
			myOgre.setPosition(x_temp, y_temp);
		}else myOgre.setTempPosition(myOgre.getX(), myOgre.getY());
		
		updateOgreClub(myOgre);
		
		
	}
	
	public void updateOgreClub(Ogre myOgre)
	{
		int[] club;
		do {
			club = myOgre.ChangeClub();
			if(currentmap.moveTo(club[0], club[1]))
			{
				if(myKey.getX() == club[0] && myKey.getY() == club[1])
				{
					myOgre.setClubElm('$');
					
				}else myOgre.setClubElm('*');
				myOgre.setClub(club[0], club[1]);
				break;
			}
		}while(true);
	}

	
	public Boolean VerifyColisionOgre(Ogre myOgre)
	{
			if((myHero.getX() == myOgre.getX() && Math.abs(myHero.getY() - myOgre.getY()) <= 1) || (myHero.getY() == myOgre.getY() && Math.abs(myHero.getX() - myOgre.getX()) <= 1))
			{
				
				myOgre.setStopOgre(2);
				myOgre.setElm('8');
				myOgre.setClubElm(' ');
				
				return false;
			}
			else if(((myHero.getX() == myOgre.getAttackX() && Math.abs(myHero.getY() - myOgre.getAttackY()) <= 1) || (myHero.getY() == myOgre.getAttackY() && Math.abs(myHero.getX() - myOgre.getAttackX()) <= 1)) && myOgre.getStopOgre() == 0)
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
			
			
			maptosend[myKey.getX()][myKey.getY()] = 'k';
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
	

}
