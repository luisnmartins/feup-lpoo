package dkeep.logic;
import java.util.Random;

public class Ogre extends Character {

		private char attackElement;
		private int attackX;
		private int attackY;
		
		public Ogre(int x, int y, char Elm, char attackElm)
		{
			super(x, y, Elm);
			attackElement = attackElm;
			attackX = x;
			attackY = y;
			
		}
		
		public char GenerateOgre()
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
					position[1] -= 1;
				else
					position[1] += 1;
			}
			else
			{
				if(r == 1)
					position[2] += 1;
				else
					position[2] -= 1;
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
		
	
}
