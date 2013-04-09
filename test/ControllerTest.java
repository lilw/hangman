import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class ControllerTest {
	private GUI_PlayGame gui;
	@Before
	public void setUp() throws Exception 
	{
		//hm = new EvilHangMan(6, 8);
		gui = new GUI_PlayGame(10, 4);
		gui.show();
		//gui.game=gui.game.controller('A', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
	}
	
	@Test
	public void testGuessTEvilT() 
	{
			
			gui.game=gui.game.controller('U', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
			System.out.println("Secret word: "+gui.game.getSecretWord()+" ch: "+'U');
			assertTrue(gui.isEvil());
			gui.game=gui.game.controller('A', gui.result, gui.label2, gui.label3, gui.frame, gui.game);//secret word is zoological
			System.out.println("Secret word: "+gui.game.getSecretWord()+" ch: "+'A');
			assertTrue(gui.game.getGuessResult());
			//if(ch)
		
		
	}
	@Test
	public void testGuessTEvilF() 
	{   //prep for test case
		gui.game=gui.game.controller('U', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		System.out.println("Secret word: "+gui.game.getSecretWord()+" ch: "+'U');
		gui.game=gui.game.controller('A', gui.result, gui.label2, gui.label3, gui.frame, gui.game);//secret word is zoological
		
		//actual test case
		assertFalse(gui.isEvil());
		gui.game=gui.game.controller('O', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		assertTrue(gui.game.getGuessResult());
		
	}
	@Test
	public void testGuessF() 
	{
		//word is AARDWOLVES which does not contain U
		gui.game=gui.game.controller('U', gui.result,gui.label2, gui.label3, gui.frame, gui.game);
		assertFalse(gui.game.getGuessResult());
	}
	@Test
	public void testOverTWinT() 
	{
		//prep for test
		//secret word is Zoological
		gui.game=gui.game.controller('U', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		gui.game=gui.game.controller('A', gui.result, gui.label2, gui.label3, gui.frame, gui.game);//secret word starts here
		gui.game=gui.game.controller('O', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		gui.game=gui.game.controller('L', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		gui.game=gui.game.controller('G', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		gui.game=gui.game.controller('I', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		gui.game=gui.game.controller('C', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		
		//actual test case
		gui.game=gui.game.controller('Z', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		assertTrue(gui.game.gameOver());
		assertTrue(gui.game.isWin());
		
		
	}
	@Test
	public void testOverTWinF() 
	{
		//prep for test
		//secret word is Zoological
		gui.game=gui.game.controller('U', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		gui.game=gui.game.controller('A', gui.result, gui.label2, gui.label3, gui.frame, gui.game);//secret word starts here
		
		gui.game=gui.game.controller('B', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		gui.game=gui.game.controller('D', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		gui.game=gui.game.controller('E', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		assertTrue(gui.game.gameOver());
		assertFalse(gui.game.isWin());
	}
	@Test
	public void testOverF() 
	{
		gui.game=gui.game.controller('U', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
		assertFalse(gui.game.gameOver());
	}
}
