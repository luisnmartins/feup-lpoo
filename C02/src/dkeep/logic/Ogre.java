
package dkeep.logic;
import java.util.Random;

public class Ogre extends Character implements java.io.Serializable{


		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private char attackElement;
		private int attackX;
		private int attackY;
		private int stopmove;
		
		/**
		 * Constructor of an Ogre, an Ogre is a Character so the  it uses the Character constructor and and set the Club in the Ogre position
		 * @param x Ogre/Club start matrix line
		 * @param y Ogre/Club start matrix column
		 * @param Elm char that represents Ogre in the matrix
		 * @param attackElm char that represents Ogre's Club in the matrix
		 */
		public Ogre(int x, int y, char Elm, char attackElm)
		{
			super(x, y, Elm);
			attackElement = attackElm;
			attackX = x;
			attackY = y;
			
		}
		
		/**
		 * Generate a Random move to the Ogre
		 * @return Returns the char that represents the generated move considering the WASD keys ( W - up, A - left, S - Down, D - right) 
		 */
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
		
		
		/**
		 * Randomly generates the new Club's position considering as start point the actual Ogre's position
		 * @return Returns an int array with the new Club's matrix line at the 1st position (int[0]) and the new Club's matrix column at the 2nd position(int[1))
		 */
		public int[] ChangeClub()
		{
			
			Random rand = new Random();

			int r = rand.nextInt(4)+1;
			
			int[] position = {getX(), getY()};

			
			if(r%2 == 0)
			{
				if(r == 2) position[0] -= 1;
				else position[0] += 1;
			}
			else
			{
				if(r == 1) position[1] += 1;
				else position[1] -= 1;
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
							return false;
					}
					else
						return true;
				}
				
				
			}
			if((c.getX() == this.attackX && Math.abs(c.getY() - this.attackY) <= 1) || (c.getY() == this.attackY && Math.abs(c.getX() - this.attackX) <= 1))
			{
				return true;
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
				}
				else
				{
					this.stopmove--;
					return true;
				}
			}
			return false;
		}
		
		
		
		public boolean update(Map currentmap, char guardmove)
		{
			int tries = 0;
			if(this.getMoveCharacter())
			{
			
			if(!updateSleep())
			{
				
			
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
				tries++;
				
			}while(tries<=50);
			
			this.setPosition(this.getXTemp(), this.getYTemp());
		
			
			
			}
			}
			updateClub(currentmap);
			return true;
		}
		
		
		public void updateClub(Map currentmap)
		{
			System.out.println("Update Ogre");
			int[] club;
			int tries =0;
			
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
				
				tries++;
				club[0] = getX();
				club[1] = getY();
			}while(tries <= 50);
		
			this.setClub(club[0], club[1]);
			
		}
		
		public void addElementsMatrix(char[][] map)
		{
			map[this.getAttackX()][this.getAttackY()] = this.getClubElm();
			super.addElementsMatrix(map);
			
		}
		
	
}
