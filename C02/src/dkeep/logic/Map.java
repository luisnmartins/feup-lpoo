
package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;

public class Map implements java.io.Serializable{
	


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char[][] map;
	private ArrayList<Door> doors = new ArrayList<Door>();
	private Key myKey;
	
	
	/**
	 * Constructor of a map that stores the given parameter to the char[][] map variable that represents the game map
	 * @param map matrix that represents the map itself
	 */
	public Map(char[][] map)
	{
		
		this.map = new char[map.length][];
		
		for(int i=0; i< map.length; i++)
		{
			this.map[i] = Arrays.copyOf(map[i], map[i].length);
		}
		
	}
	
	/**
	 * Function that sees if the given position on the matrix is free to move, that means the position is neither a wall nor a closed door
	 * @param x position in the map (matrix)
	 * @param y position in the map (matrix)
	 * @return true if the position is not a closed door or a wall, otherwise returns false
	 */
	public  Boolean moveTo(int x, int y) {
		if(map[x][y] == 'X' || map[x][y] == 'I')
			return false;
		else
			return true;
	}
	
	/**
	 * This function simply clears a position in the map matrix, so, the given position in the matrix is now defined by ' ' (not NUll)
	 * @param x position in the map
	 * @param y position in the map
	 */
	public void ClearPosition(int x, int y)
	{
		map[x][y]  = ' ';
	}
	
	
	/**
	 * Creates a new door at the given position,and adds it to the list of existing doors on the map
	 * (list doors);
	 * @param xPos in the map
	 * @param yPos in the map
	 */
	public void setDoor(int xPos, int yPos)
	{
		Door myDoor = new Door(xPos, yPos);
		doors.add(myDoor);
	}
	
	/**
	 * Creates a new key at the given position
	 * @param xPos in the map
	 * @param yPos in the map
	 */
	public void setKey(int xPos, int yPos)
	{
		
		myKey = new Key(xPos, yPos, 'k');
	}
	
	/**
	 * Gets the key of the map
	 * @return the myKey variable
	 */
	public Key getKey()
	{
		return myKey;
	}
	
	/**
	 * Opens all the doors at the variable doors
	 */
	public void openDoors()
	{
		for(int i=0; i<doors.size(); i++)
		{	
			doors.get(i).OpenDoor();
		}
	}
	
	/**
	 * Checks if the first door in the doors list is opened by using the object Door function isOpened() (check door documentation)
	 * @return true if the door is opened else returns false
	 */
	public boolean DoorsAreOpened()
	{
		return doors.get(0).IsOpened();
	}
	
	public void setSaticMap(char[][] newmap)
	{};
	
	/**
	 * Function that checks if a character is trying to go through a door and if he is able to 
	 * @param c character to know the position is trying to go to
	 * @return true if the door is opened and the character can go through the door or if the character is not trying to go through a door at all, otherwise returns false
	 */
	public boolean verifyMoveDoors(Character c)
	{
		int i;
		
		if((i=IsOveraDoor(c.getXTemp(), c.getYTemp())) != -1){
			
				if(!doors.get(i).IsOpened()){
					
					if(myKey.getFound()){
						
						doors.get(i).OpenDoor();
										
					}
					return false;
				}		
				else return true;
		
		}
		else return true;
		
	}
	
	/**
	 * Gets the index of the doors list of the door at the given position if there is any door at that pos
	 * @param x position in the map matrix
	 * @param y position in the map matrix
	 * @return the index of the door that exist at the position, if there are no doors at the given position then returns -1
	 * 
	 */
	public int IsOveraDoor(int x, int y)
	{
		for(int i=0; i<doors.size(); i++){
			
			if(doors.get(i).doorAchieved(x, y))
				return i;
		}
		return -1;
	}
	
	/**
	 * Gets the matrix that represents the map itself
	 * @return  the map variable
	 */
	public char[][] getMap()
	{
		return this.map;
	};

	/**
	 * Changes the given position in the matrix to the char given
	 * @param x position in the map matrix
	 * @param y position in the map matrix
	 * @param element going to be set in the position 
	 */
	public void setMapPosition(int x,int y, char element)
	{
		map[x][y] = element;
	}
	
	/**
	 * Adds to a map all the doors and the key saved at the key and doors variables    
	 * @param finalmap the map where the doors and key are going to be set
	 */
	public void addElementsMatrix(char[][] finalmap)
	{
		finalmap[myKey.getX()][myKey.getY()] = myKey.getSymbol();
		for(int i=0; i<doors.size(); i++){
			
			finalmap[doors.get(i).getX()][doors.get(i).getY()] = doors.get(i).getSymbol();
		}
	}



	
}

