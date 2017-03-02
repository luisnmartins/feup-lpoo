package dkeep.logic;

public interface Map {


	
	public char getElement(int x, int y);

	
	public Boolean ValidPosition(int x, int y);
	

	public void ClearPosition(int x, int y);
	
	
	public char[][] getMap();

	
	public void setMapPosition(int x,int y, char element);

	
	
}
