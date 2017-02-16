import java.util.Scanner;

public class Dungeonkeep {
		
		
		public void move(int xh, int yh, char[][] map)
		{
			Boolean end = false;
			char move;
			int new_pos_x = xh, new_pos_y = yh;
			
			while(true)
			{
				Scanner s = new Scanner(System.in);
				move = s.next().charAt(0);
				map[xh][yh] = ' ';
				if(move == 'w'){
					
					if(new_pos_x != 0)
						new_pos_x -= 1;
				}
				
				else if(move == 'a'){
					
				if(new_pos_y != 0)
					new_pos_y -= 1;
				}
				
				else if(move == 's'){
					
					new_pos_x += 1;
				}
				
				else if(move == 'd'){
					
					new_pos_y += 1;
				}
				if(map[new_pos_x][new_pos_y] == 'I' || map[new_pos_x][new_pos_y] == 'X')
				{
					new_pos_x = xh;
					new_pos_y = yh;
				}
				else if(map[new_pos_x-1][new_pos_y] == 'G' || map[new_pos_x][new_pos_y+1] == 'G')
				{
					end = true;
					xh = new_pos_x;
					yh = new_pos_y;
					map[xh][yh] = 'H';
					break;
				}
				else if(map[new_pos_x][new_pos_y] == 'K')
				{
					map[5][0] = 'S';
					map[6][0] = 'S';
					xh = new_pos_x;
					yh = new_pos_y;
					map[xh][yh] = 'H';
					break;
				}
				else if(map[new_pos_x][new_pos_y] == ' ')
				{
					xh = new_pos_x;
					yh = new_pos_y;
				}
				
				map[xh][yh] = 'H';
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
			
			for(int i=0; i<map.length; i++)
			{
				for(int j=0; j<map[i].length; j++)
				{
					System.out.print(map[i][j]);
					System.out.print(" ");
					
				}
				System.out.print("\n");
			}
			if(end == true)
			{
				System.out.println("LOSER!!");
			}
			else
			{
				System.out.println("YOU WIN!! CONTINUE...");
			}
		}
		
		public void map()
		{
			char[][] map= {{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}, {'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'}, {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'}, {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'}, {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'}, {'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'}, {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'}, {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'}, {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}};
			for(int i=0; i<map.length; i++)
			{
				for(int j=0; j<map[i].length; j++)
				{
					System.out.print(map[i][j]);
					System.out.print(" ");
					
				}
				System.out.print("\n");
			}
			move(1, 1, map);
		}
		
		public static void main(String[] args)
		{
			
			Dungeonkeep dk = new Dungeonkeep();
			dk.map();
			
		}
		
		

}

