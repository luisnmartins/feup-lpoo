package dkeep.logic;

public interface Map {


	
	public char getElement(int x, int y);
	
	public Boolean moveTo(int x, int y);
	

	public void ClearPosition(int x, int y);
	
	public void openDoors();
	
	
	public char[][] getMap();

	
	public void setMapPosition(int x,int y, char element);

	
	
}