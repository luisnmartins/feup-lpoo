package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.cli.DungeonKeep;
import dkeep.cli.DungeonKeep.state;
import dkeep.logic.OgreLevel;

import dkeep.logic.Level;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

public class TestOgreRandomBehaviour {

	
	char[][] map = {{'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', 'O', 'X'},
			{'I', ' ', ' ', ' ', 'X'},
			{'X', 'k', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};
	
	
	@Test(timeout = 1000)
	public void testSomeRandomBehaviour(){
		
		
		Map testmap = new Map(map);
		OgreLevel currentLevel = new OgreLevel(testmap);
		
		Ogre myOgre = currentLevel.getFirstOgre();
		
		
		boolean as = false, ad = false, aw = false, aa = false;
		boolean ss= false, sd = false, sa = false, sw = false;
		boolean dd = false, ds = false, da = false, dw= false;
		boolean ww = false, ws = false, wa = false, wd = false;
		
		while(!as || !ad || !aw || !aa || !ss || !sd || !sa || !sw || !dd || !ds || !da || !dw || !ww || !ws || !wa || !wd)
		{
			int xOld = myOgre.getX();
			int yOld = myOgre.getY();
			
			currentLevel.updateOgre(myOgre);
			
			int x= myOgre.getX();
			int y = myOgre.getY();
			int xAttack = myOgre.getAttackX();
			int yAttack = myOgre.getAttackY();
			
			
			if(x-xOld == 1 && y- yOld == 0 && xAttack - x == 1 && yAttack == y)
			{
				ss = true;
			}else if(x-xOld == 1 && y-yOld == 0 && xAttack -x == 0 && yAttack-y ==1)
			{
				sd = true;
			}else if(x-xOld == 1 && y-yOld == 0 && xAttack -x == 0 && yAttack-y ==-1)
			{
				sa = true;
			}else if(x-xOld == 1 && y-yOld == 0 && xAttack -x == -1 && yAttack-y ==0)
			{
				sw = true;
			}else if(x-xOld == -1 && y-yOld == 0 && xAttack -x == 0 && yAttack-y ==1)
			{
				wd = true;
			}else if(x-xOld == -1 && y-yOld == 0 && xAttack -x == -1 && yAttack-y ==0)
			{
				ww = true;
			}else if(x-xOld == -1 && y-yOld == 0 && xAttack -x == 0 && yAttack-y ==-1)
			{
				wa = true;
			}else if(x-xOld == -1 && y-yOld == 0 && xAttack -x == 1 && yAttack-y ==0)
			{
				ws= true;
			}else if(x-xOld == 0 && y-yOld == 1 && xAttack -x == 0 && yAttack-y ==1)
			{
				dd= true;
			}else if(x-xOld == 0 && y-yOld == 1 && xAttack -x == 0 && yAttack-y ==-1)
			{
				da= true;
			}else if(x-xOld == 0 && y-yOld == 1 && xAttack -x == 1 && yAttack-y ==0)
			{
				ds = true;
			}else if(x-xOld == 0 && y-yOld == 1 && xAttack -x == -1 && yAttack-y ==0)
			{
				dw = true;
			}else if(x-xOld == 0 && y-yOld == -1 && xAttack -x == 0 && yAttack-y ==1)
			{
				ad = true;
			}else if(x-xOld == 0 && y-yOld == -1 && xAttack -x == 0 && yAttack-y ==-1)
			{
				aa = true;
			}else if(x-xOld == 0 && y-yOld == -1 && xAttack -x == 1 && yAttack-y ==0)
			{
				as = true;
			}else if(x-xOld == 0 && y-yOld == -1 && xAttack -x == -1 && yAttack-y ==0)
			{
				aw = true;
			}else 
				fail("ERROR !\n");
		}
		
	}
	
	
}
