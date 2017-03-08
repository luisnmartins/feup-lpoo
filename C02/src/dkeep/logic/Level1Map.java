package dkeep.logic;

import java.util.ArrayList;
import java.util.List;

public class Level1Map extends Map {

	private static char[][] map = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	private List<List<Integer>> doors = new ArrayList<List<Integer>>();
	
	
	public Level1Map()
	{
		super(map);
	}
	
	
	public  char getElement(int x, int y){
		return map[x][y];
	}
	
	public  Boolean moveTo(int x, int y){
		if(map[x][y] == 'X' || map[x][y] == 'I')
			return false;
		else
			return true;
	
	};
	

	public  void ClearPosition(int x, int y){
		
		map[x][y] = ' ';
	};
	
	
	public void setDoor(int x, int y)
	{
		List<Integer> door = new ArrayList<Integer>();
		doors.add(door);
	}
	
	
	//TODO set openDoors
	public  void openDoors(){
		
		/*for(int i=0; i<doors.size(); i++)
		{
			for(int j=0; j<doors.get(i).size(); j++)
				map[doors.get(i).get(j).intValue(), doors.get(i).get(j+1).intValue()] = 'S';

		}*/
		
	};
	
	
	public  char[][] getMap(){return map;};

	
}
