package dkeep.logic;

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
		
		
	
}
