package dkeep.logic;

import java.util.ArrayList;
import java.util.List;

public class Level1Map extends Map {

	private static char[][] mapStatic= { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	
	
	
	public Level1Map()
	{
		super(mapStatic);
	}
	
	
	public  char getElement(int x, int y){
		return this.map[x][y];
	}
	
	public  Boolean moveTo(int x, int y){
		if(map[x][y] == 'X' || map[x][y] == 'I')
			return false;
		else
			return true;
	
	};
	
	
	
	
	public  char[][] getMap(){return map;};

	
}
