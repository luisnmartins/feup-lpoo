import java.util.Scanner;

public class dungeonkeep {
		
		
		/*public static void move(int xh, int yh, char[][] map)
		{
			Boolean end = false;
			char move;
			int new_pos_x = xh, new_pos_y = yh;
			
			while(end == false)
			{
				Scanner s = new Scanner(System.in);
				move = s.next().charAt(0);
				if(move == 'w'){
					
					new_pos_x -= 1;
				}
				
				else if(move == 'a'){
					
					new_pos_y -= 1;
				}
				
				else if(move == 's'){
					
					new_pos_y += 1;
				}
				
				else if(move == 'd'){
					
					new_pos_x += 1;
				}
				
				/*if(map[new_pos_x][new_pos_y] == 'X' || map[new_pos_x][new_pos_y] == 'I')
				{
			}
		}*/
		
		
		
		public static void main(String[] args)
		{
			char[][] map= {{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}, {'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'}, {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'}, {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'}, {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'}, {'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'},{'I',' ',' ',' ',' ',' ',' ',' ',' ', 'X'}, {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'}, {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'}, {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}};
			for(int i=0; i<map.length; i++)
			{
				for(int j=0; j<map[i].length; j++)
				{
					System.out.print(map[i][j]);
					
				}
				System.out.print("\n");
			}
			
			//move(1, 1, char[][]);
		}
		
		

}

