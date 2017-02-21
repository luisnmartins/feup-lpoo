import java.util.Random;
import java.util.Scanner;

public class Dungeonkeep {

	private char[][] map = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
			{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' }, { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	private char[] guard = { 'a', 's', 's', 's', 's', 'a', 'a', 'a', 'a', 'a', 'a', 's', 'd', 'd', 'd', 'd', 'd', 'd',
			'd', 'w', 'w', 'w', 'w', 'w', 'w' };

	private char[][] map2 = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'I', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	public void move(int xh, int yh) {
		Boolean end = false; // gameover
		Boolean gotKey = false; // key from both levels
		int level = 1; // level
		char move;
		Position guard_pos = new Position(1, 8, 'G');
		Position hero_pos = new Position(xh, yh, 'H');
		Position ogre_pos = new Position(1, 4, 'O');
		Position club_pos = new Position(1, 4, ' ');
		int it_guard = 0; // iterator for guard array

		while (true) {
			Scanner s = new Scanner(System.in);
			move = s.next().charAt(0);
			
			
			if(hero_pos.x == 8 && hero_pos.y == 7)
				map[hero_pos.x][hero_pos.y] = 'k';
			else
				map[hero_pos.x][hero_pos.y] = ' ';
			hero_pos.change_pos(move);

			if (level == 1) {

				// update guard
				map[guard_pos.x][guard_pos.y] = ' ';
				guard_pos.change_pos(guard[it_guard]);

				

				
				if (map[hero_pos.x][hero_pos.y] == 'I' || map[hero_pos.x][hero_pos.y] == 'X') {
					hero_pos.x = xh;
					hero_pos.y = yh;
				}

				else if ((hero_pos.x == guard_pos.x && Math.abs(hero_pos.y - guard_pos.y) <= 1)
						|| (hero_pos.y == guard_pos.y && Math.abs(hero_pos.x - guard_pos.x) <= 1)) {
					end = true;

					updateMap(ogre_pos, hero_pos, guard_pos, club_pos, level);

					break;
				} else if (map[hero_pos.x][hero_pos.y] == ' ') {
					xh = hero_pos.x;
					yh = hero_pos.y;
				} else if (map[hero_pos.x][hero_pos.y] == 'S') {
					map = map2;
					level = 2;
					hero_pos.x = xh = 7;
					hero_pos.y = yh = 1;

				}

				updateMap(ogre_pos, hero_pos, guard_pos, club_pos, level);

				it_guard++;
				if (it_guard == guard.length - 1)
					it_guard = 0;
				

			}
			else
			{
				// update ogre and club
				updateOgre(ogre_pos);
				updateClub(ogre_pos, club_pos);

				// doing nothing
				if (map[hero_pos.x][hero_pos.y] == 'I' || map[hero_pos.x][hero_pos.y] == 'X') {
					if(hero_pos.key == 'S'){
						xh = hero_pos.x;
						yh = hero_pos.y;
					}
					else{
						hero_pos.x = xh;
						hero_pos.y = yh;
					}
				}
				//gameover
				else if ((hero_pos.x == ogre_pos.x && Math.abs(hero_pos.y - ogre_pos.y) <= 1) || (hero_pos.y == ogre_pos.y && Math.abs(hero_pos.x - ogre_pos.x) <= 1) 
						|| (hero_pos.x == club_pos.x && Math.abs(hero_pos.y - club_pos.y) <= 1) || (hero_pos.y == club_pos.y && Math.abs(hero_pos.x - club_pos.x) <= 1)) {
					
					end = true;
					updateMap(ogre_pos, hero_pos, guard_pos, club_pos, level);
					break;
					
				} else if (map[hero_pos.x][hero_pos.y] == ' ') {
					xh = hero_pos.x;
					yh = hero_pos.y;
				} if (hero_pos.key == 'S') {
					System.out.println("YOU WIN!!");
					map[1][0] = 'S';
					break;
					
				}
				

				updateMap(ogre_pos, hero_pos, guard_pos, club_pos, level);
			}
			printMap();

		}

		printMap();
		if (end == true) {
			System.out.println("LOSER!!");
		}

	}

	public void updateOgre(Position ogre) {
		while (true) {

			Position ogreTemp = new Position(ogre.x, ogre.y, ogre.key);
			Random rand = new Random();

			int r = rand.nextInt(4);

			if (r == 0) {
				ogreTemp.key = 'd';
			} else if (r == 1) {
				ogreTemp.key = 'w';
			} else if (r == 2) {
				ogreTemp.key = 'a';
			} else if (r == 3) {
				ogreTemp.key = 's';
			}

			ogreTemp.change_pos(ogreTemp.key);

			if (map[ogreTemp.x][ogreTemp.y] != 'I' && map[ogreTemp.x][ogreTemp.y] != 'X' &&  map[ogreTemp.x][ogreTemp.y] != 'S') {
				map[ogre.x][ogre.y] = ' ';
				ogre.x = ogreTemp.x;
				ogre.y = ogreTemp.y;
				break;
			}

		}

	}
	
	public void updateClub(Position ogre, Position club)
	{
		
		while (true) {
			
			Position clubTemp = new Position(ogre.x, ogre.y, '*');
			Random rand = new Random();
			
			int r = rand.nextInt(4);
			map[club.x][club.y] = ' ';
			
			if (r == 0) {
				clubTemp.key = 'd';
			} else if (r == 1) {
				clubTemp.key = 'w';
			} else if (r == 2) {
				clubTemp.key = 'a';
			} else if (r == 3) {
				clubTemp.key = 's';
			}
			clubTemp.change_pos(clubTemp.key);
			
			if (map[clubTemp.x][clubTemp.y] != 'I' && map[clubTemp.x][clubTemp.y] != 'X' &&  map[clubTemp.x][clubTemp.y] != 'S') {
				
				club.x = clubTemp.x;
				club.y = clubTemp.y;
				
				break;
				
			}
			
		}
	}

	public void updateMap(Position ogre, Position hero, Position guard, Position club, int level)
		{
			if(level == 1){
				
				map[hero.x][hero.y] = hero.key;
				map[guard.x][guard.y] = guard.key;
				
				if(hero.x == 8 && hero.y == 7)
				{
					map[5][0] = 'S';
					map[6][0] = 'S';
				}

				
				
			}
			else
			{
				
				// update element key position
				if (map[hero.x][hero.y] == 'k') {
					hero.key = 'K';
					//map[1][0] = 'S';
					
				}

				if(map[ogre.x][ogre.y] == 'k' || map[ogre.x][ogre.y] == '$')
					ogre.key = '$';
				else
				{
					ogre.key = 'O';
					
					if(hero.key == 'H')
						map[1][7] = 'k';
				}
				
				if(map[club.x][club.y] == 'k' || map[club.x][club.y] == '$')
					club.key = '$';
				else
				{
					club.key = '*';
					
					if(hero.key == 'H')
						map[1][7] = 'k';
				}
				
				map[club.x][club.y] = club.key;
				map[ogre.x][ogre.y] = ogre.key;
				map[hero.x][hero.y] = hero.key;
				
				if(hero.key == 'K' && map[hero.x][hero.y-1] == 'I'){
					
					hero.key = 'S';
					
				}
			}
			
			
		}

	public void printMap() {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
				System.out.print(" ");

			}
			System.out.print("\n");
		}

	}

	public void startGame() {
		printMap();
		move(1, 1);
	}

	public static void main(String[] args) {

		Dungeonkeep dk = new Dungeonkeep();
		dk.startGame();

	}

	public class Position {
		private int x;
		private int y;
		private char key;

		Position(int x_pos, int y_pos, char key_pos) {
			x = x_pos;
			y = y_pos;
			key = key_pos;

		}

		public void change_key(char key_pos) {
			key = key_pos;
		}

		public void change_pos(char key) {
			if (key == 'w') {

				if (x != 0)
					x -= 1;
			}

			else if (key == 'a') {

				if (y != 0)
					y -= 1;
			}

			else if (key == 's') {

				x += 1;
			}

			else if (key == 'd') {

				y += 1;
			}

		}

	}

}
