
package dkeep.logic;
import java.util.Random;

public class Ogre extends Character implements java.io.Serializable{


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
			
			
		/**
		 * Sets the x and y club position with the given parameters
		 * @param x  the new x position of the club
		 * @param y  the new y position of the club
		 */
		public void setClub(int x, int y)
		{
			attackX = x;
			attackY = y;
		}
		
		/**
		 * Sets a new symbol to represent the club
		 * @param element the new char that represents a club in the map
		 */
		public void setClubElm(char element)
		{
			attackElement = element;
		}
		
		/**
		 * Sets the counter time of when the ogre gets stuned by the hero to its given parameter
		 * @param times the new value of stopmove of ogre(ogre is going to be times time turns without moving if its stunned)
		 */
		public void setStopOgre(int times)
		{
			stopmove = times;
		}
		
		/**
		 * Decrements by one de counter stopmove that defines that the ogre is stunned
		 */
		public void decStopOgre()
		{
			stopmove--;
		}
		/**
		 * Gets the current value of the counter that defines the number of turns that the ogre is left to be stunned
		 * @return the  stopmove variable
		 */
		public int getStopOgre()
		{
			return stopmove;
		}
		
		/**
		 * Gets the current symbol that represents the club in the game map
		 * @return the attackElement variable of the ogre
		 */
		public char getClubElm()
		{
			return attackElement;
		}
		
		
		/**
		 * Gets the current x position of the ogre club attack
		 * @return the attackX variable 
		 */
		public int getAttackX()
		{
			return attackX;
		}
		
		/**
		 * Gets the current y position of the ogre club attack
		 * @return the attackY variable
		 */
		public int getAttackY()
		{
			return attackY;
		}
		
		/**
		 * Compares if an ogre and the club attack is next to another Character and updates the ogre accordingly
		 * Checks if the Ogre is not stunned and then compares if the Character c is next to it , if so the the ogre is set to stunned accordingly 
		 * Also checks if the character is next to the ogre attack club
		 * @param c character to compare to 
		 * @return true the character c is next to the ogre club and false if the character is next to the ogre itself(ogre is stunned) or if the character is not near the ogre
		 */
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
		
		/**
		 * Updates the sleeping(stun) status of the ogre by updating the sleeping count timer, the ogre and club elements
		 * @return true if the ogre is still sleeping, false if the ogre is awake
		 */
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
		
		
		/**
		 * Updates the ogre and club positions by randomly simulating new movements until the new positions are valid (a max of 50 tries in case something goes wrong)
		 * Only updates the ogre ( or tries to) if the ogre is not stunned
		 * @param currentmap map to check if the new positions of the ogre are valid
		 * @param guardmove char to represent the move set (irrelevent to the ogre)
		 * @return true if the ogre has been updated successfully
		 */
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
		
		/**
		 * Updates the ogres club positions by randomly getting new adjacent positions to the ogre (after he moved) until the position is valid ( 50 tries atmost if something goes wrong)
		 * also updates the club symbol accordingly (in case it goes over a key, for example)
		 * @param currentmap map to check if the new club positions of the club are valid positions
		 */
		public void updateClub(Map currentmap)
		{
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
		
		/**
		 * Adds the ogre club position into a map and the ogre itself
		 */
		public void addElementsMatrix(char[][] map)
		{
			map[this.getAttackX()][this.getAttackY()] = this.getClubElm();
			super.addElementsMatrix(map);
			
		}
		
	
}
