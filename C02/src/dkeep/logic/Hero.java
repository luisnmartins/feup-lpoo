
package dkeep.logic;

import java.util.ArrayList;

public class Hero extends Character implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Creates a Character of type Hero with the given positions as parameters,and a char to represent it in the map 
	  * @param xPos Hero matrix position - Line
	 * @param yPos Hero matrix position - Column
	 * @param Elm  Char that represents the Hero in the matrix
	 */
	public Hero(int xP, int yP, char Elm)
	{
		super(xP, yP, Elm);
		
	}

	
	/**
	 * Updates the Hero in the game and checks 
	 * @param currentmap
	 * @param move
	 * @param enemies
	 * @return
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
				System.out.println("verifydoors");
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

