package dkeep.gui;

import dkeep.logic.Level;
import dkeep.logic.Level.state;

public class GraphicsVariables {
	
	private Level currentLevel;
	private int Ogrenmb=2, GuardTypenmb=1;
	private int horMapSize,verMapSize;
	private char selectedElement;
	
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
	
	public void editMap(int x,int y, char element)
	{
		currentLevel.getCurrentMap().setMapPosition(x, y, element);
	}
	
	public void setMapSizes(int hor,int ver)
	{
		setHorMapSize(hor);
		setVerMapSize(ver);
	}
	public int getHorMapSize() {
		return horMapSize;
	}
	public void setHorMapSize(int horMapSize) {
		this.horMapSize = horMapSize;
	}
	public int getVerMapSize() {
		return verMapSize;
	}
	public void setVerMapSize(int verMapSize) {
		this.verMapSize = verMapSize;
	}
	
	public void setSelectedElement(char element)
	{
		this.selectedElement = element;
	}
	
	public char getSelectedElement()
	{
		return this.selectedElement;
	}
	
	

}
