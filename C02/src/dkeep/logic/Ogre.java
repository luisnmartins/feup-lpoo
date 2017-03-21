package dkeep.logic;
import java.util.Random;

public class Ogre extends Character {

		private char attackElement;
		private int attackX;
		private int attackY;
		private int stopmove;
		
		
		public Ogre(int x, int y, char Elm, char attackElm)
		{
			super(x, y, Elm);
			attackElement = attackElm;
			attackX = x;
			attackY = y;
			
		}
		
		public char GenerateOgrePosition()
		{
			Random rand = new Random();

			int r = rand.nextInt(4);
			
			if (r == 0) 
				return 'd';
			
			else if (r == 1)
				return 'w';
			
			else if (r == 2) 
				
				return 'a';
			
			else 
				return 's';
			
			
		}
		
		public int[] ChangeClub()
		{
			
			Random rand = new Random();

			int r = rand.nextInt(4)+1;
			
			int[] position = {getX(), getY()};

			
			if(r%2 == 0)
			{
				if(r == 2)
					position[0] -= 1;
				else
					position[0] += 1;
			}
			else
			{
				if(r == 1)
					position[1] += 1;
				else
					position[1] -= 1;
			}
			
			return position;

		}
			
			
		
		public void setClub(int x, int y)
		{
			attackX = x;
			attackY = y;
		}
		
		public void setClubElm(char element)
		{
			attackElement = element;
		}
		
		public void setStopOgre(int times)
		{
			stopmove = times;
		}
		
		
		public void decStopOgre()
		{
			stopmove--;
		}
		
		public int getStopOgre()
		{
			return stopmove;
		}
		
		
		public char getClubElm()
		{
			return attackElement;
		}
		
		public int getAttackX()
		{
			return attackX;
		}
		public int getAttackY()
		{
			return attackY;
		}
		
		
		public boolean verifyColision(Character c)
		{
			if(this.getElement() == 'O')
			{
				
				if(super.verifyColision(c))
				{	
					if(this.getMoveCharacter())
					{
						
						stopmove = 2;
						this.setElm('8');
						this.setIsParalyzed(true);
						this.attackElement = ' ';
						this.attackX = this.getX();
						this.attackY = this.getY();
							return false;
					}
					else
						return true;
				}
				else if((c.getX() == this.attackX && Math.abs(c.getY() - this.attackY) <= 1) || (c.getY() == this.attackY && Math.abs(c.getX() - this.attackX) <= 1))
				{
					return true;
				}
				
				else
					return false;
				
			}
			else
				return false;
			
			
		}
		
		public boolean updateSleep()
		{

			if(this.getElement() == '8')
			{
				if(this.stopmove == 0)
				{				
					this.setElm('O');
					this.setIsParalyzed(false);
					this.attackElement = '*';
					return false;
				}
				else
				{
					this.stopmove--;
					return true;
				}
			}
			else
				return false;
		}
		
		
		
		public boolean update(Map currentmap, char guardmove)
		{
			if(this.getMoveCharacter())
			{
			
			if(updateSleep())
				return true;
			
			do
			{
				super.update(currentmap, this.GenerateOgrePosition());
				int x_temp = this.getXTemp();
				int y_temp = this.getYTemp();
				if(currentmap.moveTo(x_temp, y_temp))
				{
					if(currentmap.getKey().isOnTop(x_temp, y_temp) && !currentmap.getKey().getFound())
					{
						this.setElm('$');
						break;
						
					}else 
						this.setElm('O');
					
					if(currentmap.IsOveraDoor(x_temp, y_temp) == -1) // is not a door
						break;
						
					
				}
				this.setTempPosition(this.getX(), this.getY());
				
			}while(true);
			
			this.setPosition(this.getXTemp(), this.getYTemp());
		
			updateClub(currentmap);
			
			}
			return true;
		}
		
		
		public void updateClub(Map currentmap)
		{
			int[] club;
			
			do{
				club = this.ChangeClub();
				
				if(currentmap.moveTo(club[0], club[1]))
				{
					
					if(currentmap.getKey().isOnTop(club[0], club[1]) && !currentmap.getKey().getFound())
					{
						
						this.setClubElm('$');
						break;
					
					}else 
						this.setClubElm('*');
					
					if(currentmap.IsOveraDoor(club[0], club[1]) == -1) // is not a door
						break;
				}
			}while(true);
		
			this.setClub(club[0], club[1]);
			
		}
		
		public void addElementsMatrix(char[][] map)
		{
			map[this.getAttackX()][this.getAttackY()] = this.getClubElm();
			super.addElementsMatrix(map);
			
		}
		
	
}
