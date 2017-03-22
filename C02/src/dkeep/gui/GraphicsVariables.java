package dkeep.gui;

import dkeep.logic.Level;
import dkeep.logic.Level.state;

public class GraphicsVariables {
	
	private Level currentLevel;
	private int Ogrenmb=2, GuardTypenmb=1;
	private int horMapSize,verMapSize;
	
	
	public int getOgrenmb() {
		return Ogrenmb;
	}
	public void setOgrenmb(int ogrenmb) {
		Ogrenmb = ogrenmb;
	}
	
	
	
	public int getGuardTypenmb() {
		return GuardTypenmb;
	}
	public void setGuardTypenmb(int guardTypenmb) {
		GuardTypenmb = guardTypenmb;
	}
	
	
	public Level getLevel()
	{
		return currentLevel;
	}
	
	public void setLevel(Level currentLevel)
	{
		this.currentLevel = currentLevel;
	}
	
	public char[][] getMap()
	{
		return currentLevel.getMap();
	}
	
	public void setMapSizes(int hor,int ver)
	{
		horMapSize = hor;
		verMapSize = ver;
	}
	

}
