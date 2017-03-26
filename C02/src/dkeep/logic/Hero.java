
package dkeep.logic;

import java.util.ArrayList;

public class Hero extends Character implements java.io.Serializable{

	

	private static final long serialVersionUID = 1L;


	/**
	 * Creates a Character of type Hero with the given positions as parameters,and a char to represent it in the map 
	  * @param xP Hero matrix position - Line
	 * @param yP Hero matrix position - Column
	 * @param Elm  Char that represents the Hero in the matrix
	 */
	public Hero(int xP, int yP, char Elm)
	{
		super(xP, yP, Elm);
		
	}

	
	/**
	 * Updates the Hero status and movement in the game by checking colisions with the enemies of the level, with the key and the door
	 * @param currentmap the map where the hero is being updated
	 * @param move WASD character to represent the movement the hero is trying to make
	 * @param enemies an arraylist of Characters, where all the enemies of the Level that the hero is playing are stored(theres is only more than one enemie in an OgreLevel)
	 * @return true if the hero could move through the map successfully, false otherwise
	 */
	public boolean update(Map currentmap, char move, ArrayList<Character> enemies)
	{
		
		super.update(currentmap, move);
		int x_temp = this.getXTemp();
		int y_temp = this.getYTemp();
		Key tempKey = currentmap.getKey();
		boolean canmove = false;
		if(currentmap.moveTo(x_temp, y_temp)){
			if(tempKey.isOnTop(x_temp, y_temp))
				if(tempKey.getIsaKey()){
					
					tempKey.setFound();
					this.setElm('K');
				}else
					currentmap.openDoors();
				
			if(currentmap.verifyMoveDoors(this)){
				canmove=true;}
			
			
			for(Character enemie: enemies){
				if(enemie.getIsParalyzed()){
					
					if(enemie.getX() == x_temp && enemie.getY() == y_temp){	
						canmove=false;
						break;}	
					
				}
				}				
			
		}
		if(canmove == false)	{
			this.setTempPosition(this.getX(), this.getY());
			return false;
		}else{	
			this.setPosition(this.getXTemp(), this.getYTemp());
			return true;
		}}
	
	
}

