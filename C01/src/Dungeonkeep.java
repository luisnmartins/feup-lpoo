import java.util.Scanner;

public class Dungeonkeep {
		
	 private char[][] map= { {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
				{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
				{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
				{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},
				{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
				{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'} };
	 
	 private char[] guard = {'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a','a', 's', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'w','w','w','w','w'};
	 
	 private char[][] map2= { {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			 			{'I', ' ', ' ', ' ', 'O', ' ',' ','K','X'},
			 			{'X', ' ', ' ', ' ',' ', ' ', ' ',' ','X'},
			 			{'X', ' ', ' ', ' ',' ', ' ', ' ',' ','X'},
			 			{'X', ' ', ' ', ' ',' ', ' ', ' ',' ','X'},
			 			{'X', ' ', ' ', ' ',' ', ' ', ' ',' ','X'},
			 			{'X', ' ', ' ', ' ',' ', ' ', ' ',' ','X'},
			 			{'X', 'H', ' ', ' ', ' ',' ', ' ', ' ', 'X'},
			 			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'} };
		
	
		public void move(int xh, int yh)
		{
			Boolean end = false;
			Boolean levelup = false;
			char move;
			Position guard_pos = new Position(1, 8);
			Position hero_pos = new Position(xh, yh);
			Position ogre_pos = new Position(1, 4);
			int it_guard = 0;
			
			while(true)
			{
				Scanner s = new Scanner(System.in);
				move = s.next().charAt(0);
				
				
				map[hero_pos.x][hero_pos.y] = ' ';
				
				hero_pos.change_pos(move);
				
				map[guard_pos.x][guard_pos.y] = ' ';
				guard_pos.change_pos(guard[it_guard]);
				
				if(levelup == true)
				{
					map[8][7] = 'K';
					levelup = false;
				}
				
				
				if(map[hero_pos.x][hero_pos.y] == 'I' || map[hero_pos.x][hero_pos.y] == 'X')
				{
					hero_pos.x = xh;
					hero_pos.y = yh;
				}
				else if((hero_pos.x == guard_pos.x && Math.abs(hero_pos.y-guard_pos.y)<=1) || (hero_pos.y == guard_pos.y && Math.abs(hero_pos.x-guard_pos.x)<=1))
				{
					end = true;
					xh = hero_pos.x;
					yh = hero_pos.y;
					map[xh][yh] = 'H';
					map[guard_pos.x][guard_pos.y] = 'G';
					break;
				}
				else if(map[hero_pos.x][hero_pos.y] == 'K')
				{
					map[5][0] = 'S';
					map[6][0] = 'S';
					xh = hero_pos.x;
					yh = hero_pos.y;
					levelup = true;
					
				}
				else if(map[hero_pos.x][hero_pos.y] == ' ')
				{
					xh = hero_pos.x;
					yh = hero_pos.y;
				}
				else if(map[hero_pos.x][hero_pos.y] == 'S')
				{
					map = map2;
					//map[hero_pos.x][hero_pos.y] = ' ';
					hero_pos.x = 7;
					hero_pos.y = 1;
					
				}
				
				
				map[xh][yh] = 'H';
				
				
				map[guard_pos.x][guard_pos.y] = 'G';
				it_guard++;
				if(it_guard == guard.length-1)
					it_guard = 0 ;
				
				printMap();
			}
			
			printMap();
			if(end == true)
			{
				System.out.println("LOSER!!");
			}

		}
		
		public void printMap()
		{
			
			for(int i=0; i<map.length; i++)
			{
				for(int j=0; j<map[i].length; j++)
				{
					System.out.print(map[i][j]);
					System.out.print(" ");
					
				}
				System.out.print("\n");
			}
			
			
		}
		
		
		public void startGame()
		{
			printMap();
			move(1,1);
		}
		
		public static void main(String[] args)
		{
			
			Dungeonkeep dk = new Dungeonkeep();
			dk.startGame();
			
		}
		
		public class Position
		{
			private int x;
			private int y;
					
			Position(int x_pos, int y_pos)
			{
				x = x_pos;
				y = y_pos;
			}
			
			public void change_pos(char key)
			{
				if(key == 'w'){
					
					if(x != 0)
						x -= 1;
				}
				
				else if(key == 'a'){
					
				if(y != 0)
					y -= 1;
				}
				
				else if(key == 's'){
					
					x += 1;
				}
				
				else if(key == 'd'){
					
					y += 1;
				}
				
			}
			
			
			
		}
		
		

}

