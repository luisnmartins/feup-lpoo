package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;

public class Map implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*private static char[][] levelOneMap= {  { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
											{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
											{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
											{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
											{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
											{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
											{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };*/
	
	/*private static char[][] levelTwoMap = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };*/

	
	private char[][] map;
	private ArrayList<Door> doors = new ArrayList<Door>();
	private Key myKey;
	
	public Map(char[][] map)
	{
		
		this.map = new char[map.length][];
		
		for(int i=0; i< map.length; i++)
		{
			this.map[i] = Arrays.copyOf(map[i], map[i].length);
		}
		
	}
	

	public  Boolean moveTo(int x, int y) {
		if(map[x][y] == 'X' || map[x][y] == 'I')
			return false;
		else
			return true;
	}
	

	public void ClearPosition(int x, int y)
	{
		map[x][y]  = ' ';
	}
	
	public void setDoor(int xPos, int yPos)
	{
		Door myDoor = new Door(xPos, yPos);
		doors.add(myDoor);
		System.out.println("Doors size: " + this.doors.size());
	}
	
	
	public void setKey(int xPos, int yPos)
	{
		
		myKey = new Key(xPos, yPos, 'k');
	}
	
	public Key getKey()
	{
		return myKey;
	}
	
	public void openDoors()
	{
		for(int i=0; i<doors.size(); i++)
		{	
			doors.get(i).OpenDoor();
		}
	}
	
	public boolean DoorsAreOpened()
	{
		return doors.get(0).IsOpened();
	}
	
	public void setSaticMap(char[][] newmap)
	{};
	
	//verify if character c is near a door and can move 
	public boolean verifyMoveDoors(Character c)
	{
		int i;
		
		if((i=IsOveraDoor(c.getXTemp(), c.getYTemp())) != -1)
		{
			
			System.out.println("i value: " + i);
			//verify if the door is open
				if(!doors.get(i).IsOpened())
				{
					
					if(myKey.getFound())
					{
						doors.get(i).OpenDoor();
										
					}
					return false;
				}		
				else
					return true;
		
		}
		else
			return true;
		
	}
	
	
	public int IsOveraDoor(int x, int y)
	{
		//run all doors
		for(int i=0; i<doors.size(); i++)
		{
			//verify if character is over the door
			if(doors.get(i).doorAchieved(x, y))
			{
				
				return i;
			
			}
		}
		return -1;
	}
	
	
	public char[][] getMap()
	{
		return this.map;
	};

	
	public void setMapPosition(int x,int y, char element)
	{
		map[x][y] = element;
	}
	
	
	public void addElementsMatrix(char[][] finalmap)
	{
		finalmap[myKey.getX()][myKey.getY()] = myKey.getSymbol();
		for(int i=0; i<doors.size(); i++)
		{
			finalmap[doors.get(i).getX()][doors.get(i).getY()] = doors.get(i).getSymbol();
		}
	}
	
	
	
}