package dkeep.logic;



public class GameState {

	public enum state{ RUNNING, WIN, LOSE, END}
	
	Level currentLevel;
	
	//flag to verify GameState
	private state gameRunning;

	
	
	
	public GameState(Level currentlevel)
	{
		
		//set run
		gameRunning = state.RUNNING;
		
		this.currentLevel = currentlevel;
		
	}
	
	
	
	public void UpdateGame(char move)
	{
		
		int verifyUpdate = currentLevel.updateGame(move);
		
		if(verifyUpdate == 1)
		{	
			gameRunning = state.LOSE;
			System.out.println("You Lose!! Try Again");
		}
		else if(verifyUpdate == 2)
		{
			if(currentLevel.nextLevel() == null)
			{
				gameRunning = state.END;
				System.out.println("Congratzz!! You're a Hero!!");
			}
			else
			{
				currentLevel = currentLevel.nextLevel();
			}
					
		}
		
	
		
	}
	
	public int[] getHeroPosition()
	{
		return null;
	}


	
	
	
	
	
	
	//TODO remove update matrix
	/*public void UpdateHero(char move)
	{
		
		
		//Clean and update hero temporary position
		currentmap.ClearPosition(myHero.getX(), myHero.getY());
		myHero.changePosition(move,false);
		
		
		if(currentmap.moveTo(myHero.getXTemp(), myHero.getYTemp()) == false)
		{
			
			if(currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'k')
			{
				myHero.setX(myHero.getXTemp());
				myHero.setY(myHero.getYTemp());
				myHero.setKey(true);
				
			
				if (level == 1)
				{
					currentmap.setMapPosition(5, 0, 'S');
					currentmap.setMapPosition(6, 0, 'S');
					
					
					int i = ThreadLocalRandom.current().nextInt(1, 6);
					myOgres = new Ogre[i];
					for(int j=0; j < i ; j++)
					{
						myOgres[j] = new Ogre(1,4,'O','*');
					}
					
				}
				else
					myHero.setElm('K');
				
			}
			else if(level == 2 && myHero.getKey() == true && currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'I')
			{
				gameRunning = 1;
				myHero.setX(myHero.getXTemp());
				myHero.setY(myHero.getYTemp());
				myHero.setElm('S');
			}
			
			else if (currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'X' || currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'I' || currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'g')
			{
				myHero.setXTemp(myHero.getX());
				myHero.setYTemp(myHero.getY());
			}
			
			
			else if (currentmap.getElement(myHero.getXTemp(), myHero.getYTemp()) == 'S')
			{
				level = 2;
				currentmap = new Level2Map();
				myHero.setKey(false);
				myHero.setElm('A');
				myHero.setX(7);
				myHero.setY(1);
				myHero.setXTemp(7);
				myHero.setYTemp(1);
				
			}
			
		}
		else
		{
			myHero.setX(myHero.getXTemp());
			myHero.setY(myHero.getYTemp());
		}
		currentmap.setMapPosition(myHero.getX(), myHero.getY(),myHero.getElement());
		
	}
	
	//TODO remove update matrix
	public void UpdateGuard()
	{
		
		int randomInd = ThreadLocalRandom.current().nextInt(1,101);
		if(randomInd >= 70)
		{
			myGuard.setCharateristic();
		}
		
		if(myGuard.getCharateristic() == true && myGuard.getType() == 1)
		{
			myGuard.setElm('g');
			currentmap.setMapPosition(myGuard.getX(),myGuard.getY(), 'g');
		}
		
		else if(myGuard.getCharateristic() == true && myGuard.getType() == 2)
		{
			
			myGuard.changePosition(myGuard.getMoveGuard(), true);
			currentmap.ClearPosition(myGuard.getX(), myGuard.getY());
			myGuard.setX(myGuard.getXTemp());
			myGuard.setY(myGuard.getYTemp());
			currentmap.setMapPosition(myGuard.getX(), myGuard.getY(),myGuard.getElement());
		}
		else
		{
			myGuard.setElm('G');
			myGuard.changePosition(myGuard.getMoveGuard(),false);
			currentmap.ClearPosition(myGuard.getX(), myGuard.getY());
			myGuard.setX(myGuard.getXTemp());
			myGuard.setY(myGuard.getYTemp());
			currentmap.setMapPosition(myGuard.getX(), myGuard.getY(),myGuard.getElement());
		}
	}
	
	
	//TODO remove update matrix
	public void UpdateOgre(Ogre myOgre)
	{
		
		
			currentmap.ClearPosition(myOgre.getAttackX(), myOgre.getAttackY());
		if(myOgre.getElement() == '8')
		{
			if(myOgre.getStopOgre() == 0)
			{				
				myOgre.setElm('O');
			}
			else
			{
				myOgre.decStopOgre();
				currentmap.setMapPosition(myOgre.getX(), myOgre.getY(),myOgre.getElement());
				return;
			
			}
		}
				currentmap.ClearPosition(myOgre.getX(), myOgre.getY());
		
		
		if(myOgre.getElement() == '$' && myHero.getKey() == false)
		{
			//currentmap.setMapPosition(myOgre.getX(),myOgre.getY(), 'k');
			myOgre.setElm('O');
			
		}
		
		if(myOgre.getClubElm() == '$' && myHero.getKey() == false)
		{	
			myOgre.setClubElm('*');
			
			//currentmap.setMapPosition(myOgre.getAttackX(),myOgre.getAttackY(), 'k');
		}
		
		
		do
		{
			myOgre.changePosition(myOgre.GenerateOgre(),false);
			
			if(currentmap.moveTo(myOgre.getXTemp(), myOgre.getYTemp()) == false)
			{
				if(currentmap.getElement(myOgre.getXTemp(), myOgre.getYTemp()) == 'k')
				{
					myOgre.setElm('$');
					break;
				}
				if (currentmap.getElement(myOgre.getXTemp(), myOgre.getYTemp()) == 'X' || currentmap.getElement(myOgre.getXTemp(), myOgre.getYTemp()) == 'I')
				{
					myOgre.setXTemp(myOgre.getX());
					myOgre.setYTemp(myOgre.getY());
				}
				
			}
			else
			{

				break;
				
			}
				
		}while(true);
		myOgre.setX(myOgre.getXTemp());
		myOgre.setY(myOgre.getYTemp());
		
		currentmap.setMapPosition(myOgre.getX(), myOgre.getY(),myOgre.getElement());
		
		
		
		int[] club;
		do
		{
			club = myOgre.ChangeClub();
			
			if(currentmap.moveTo(club[0], club[1]) == false)
			{
				if(currentmap.getElement(club[0], club[1]) == 'k')
				{
					myOgre.setClubElm('$');
					break;
				}
			
			}
			else
			{
	

			
				break;
			}
		}while(true);
		myOgre.setClub(club[0], club[1]);
		currentmap.setMapPosition(club[0], club[1], myOgre.getClubElm());
			
		
		
	}
	

	*/
		

	
	
	//TODO
	public void printMap() {
		
		
		char[][] currentmap = currentLevel.getMap();
		

		for (int i = 0; i < currentmap.length; i++) {
			
			for (int j = 0; j < currentmap[i].length; j++) {
				System.out.print(currentmap[i][j]);
				System.out.print(" ");

			}
			System.out.print("\n");
		}
		System.out.print('\n');

	}
	
	
	public state getgameRunning()
	{
		return this.gameRunning;
	}
	

}
