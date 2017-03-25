package dkeep.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import dkeep.logic.Level;

public class GraphicsVariables {
	
	private Level currentLevel;
	private int Ogrenmb=2, GuardTypenmb=1;
	private int horMapSize,verMapSize;
	private char selectedElement;
	private String GameStatusMessage;
	private HashMap<Character, BufferedImage> imagesMap = new HashMap<Character, BufferedImage>();

	private BufferedImage mario_d;
	private BufferedImage mario_a;
	private BufferedImage mario_w;
	private BufferedImage mario_s;
	private BufferedImage mario_key_d;
	private BufferedImage mario_key_s;
	private BufferedImage guard;
	private BufferedImage guard_sleep;
	private BufferedImage wall;
	private BufferedImage key;
	private BufferedImage closedDoor;
	private BufferedImage openedDoor;
	private BufferedImage bowser;
	private BufferedImage bowser_fire;
	private BufferedImage bowser_stunned;
	private BufferedImage floor;
	private BufferedImage coin;
	private BufferedImage menu_screen;
	
	
	public GraphicsVariables()
	{
		try {
			loadImages();
			setHashMap();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
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
	
	
	public void loadImages() throws IOException
	{
			
			this.guard = ImageIO.read(new File("images/goomba_guard.png"));
			this.mario_d= ImageIO.read(new File("images/mario_d.png"));
			this.mario_a= ImageIO.read(new File("images/mario_a.png"));
			this.mario_s= ImageIO.read(new File("images/mario_s.png"));
			this.mario_w= ImageIO.read(new File("images/mario_w.png"));
			this.mario_key_d= ImageIO.read(new File("images/mario_key_d.png"));
			this.mario_key_s= ImageIO.read(new File("images/mario_key_s.png"));
			this.key = ImageIO.read(new File("images/key.png"));
			this.closedDoor = ImageIO.read(new File("images/closedDoor.png"));
			this.openedDoor = ImageIO.read(new File("images/openedDoor.png"));
			this.bowser = ImageIO.read(new File("images/bowser_s.png"));
			this.wall = ImageIO.read(new File("images/wall.png"));
			this.guard_sleep = ImageIO.read(new File("images/goomba_sleeping.png"));
			this.bowser_fire = ImageIO.read(new File("images/bowser_fire.png"));
			this.bowser_stunned = ImageIO.read(new File("images/bowser_stunned.png"));
			this.floor =  ImageIO.read(new File("images/floor.png"));
			this.coin = ImageIO.read(new File("images/coin.png"));
			this.menu_screen = ImageIO.read(new File("images/superMarioBackground.png"));
			
	}
	
	public void setHashMap()
	{
		imagesMap.put('G', guard);
		imagesMap.put('H', mario_d);
		imagesMap.put('k', key);
		imagesMap.put('I', closedDoor);
		imagesMap.put('S', openedDoor);
		imagesMap.put('K', mario_key_d);
		imagesMap.put('O', bowser);
		imagesMap.put('X', wall);
		imagesMap.put('g', guard_sleep);
		imagesMap.put('*', bowser_fire);
		imagesMap.put('8', bowser_stunned);
		imagesMap.put(' ', floor);
		imagesMap.put('$', coin);
	}
	
	public BufferedImage getImage(char Element)
	{
		return imagesMap.get(Element);
	}
	
	public void updateMario(char move)
	{
		boolean gotKey = currentLevel.getCurrentMap().getKey().getFound();
		switch(move)
		{
			case 'w':
				if(gotKey)
					imagesMap.put('K', mario_w);
				else
					imagesMap.put('H',mario_w);
				break;
			case 'a':
				if(gotKey)
					imagesMap.put('K', mario_a);
				else
					imagesMap.put('H',mario_a);
				break;
			case 's':
				if(gotKey)
				{
					imagesMap.put('K',mario_key_s);
				}else
					imagesMap.put('H',mario_s);
				break;
			case 'd':
				if(gotKey)
					imagesMap.put('K',mario_key_d);
				else
					imagesMap.put('H',mario_d);
				break;
				default:
					break;
		}
	}
	/*public BufferedImage getMario()
	{
		return this.mario;
	}
	public BufferedImage getFloor()
	{
		return this.floor;
	}
	public BufferedImage getWall()
	{
		return this.wall;
	}
	
	public BufferedImage getDoor()
	{
		return this.door;
	}
	
	public BufferedImage getGuard()
	{
		return this.guard;
	}
	
	public BufferedImage getBowser()
	{
		return this.bowser;
	}
	public BufferedImage getKey()
	{
		return this.key;
	}
	
	public BufferedImage getGuardSleep()
	{
		return this.guard_sleep;
	}
	
	public BufferedImage getBowserFire()
	{
		return this.bowser_fire;
	}
	
	public BufferedImage getBowserStunned()
	{
		return this.bowser_stunned;
	}
	public BufferedImage getCoin()
	{
		return this.coin;
	}*/
	
	public BufferedImage getMenuScreen()
	{
		return this.menu_screen;
	}
	
	
	public String getGameStatusMessage() {
		return GameStatusMessage;
	}
	public void setGameStatusMessage(String gameStatusMessage) {
		GameStatusMessage = gameStatusMessage;
	}

}
