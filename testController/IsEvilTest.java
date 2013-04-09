import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Test;

//game.makeGuess(nextLetter) == true && IsEvil == true
//game.makeGuess(nextLetter) == true && IsEvil == false
//game.makeGuess(nextLetter) == false
//game.gameOver() == true && game.isWin() == true
//game.gameOver() == true && game.isWin() == false
//game.gameOver() == false

public class IsEvilTest {

	@Test
	public void guessTrue_isEvilTrue() {
		GUI_PlayGame gui = new GUI_PlayGame(4, 4);
		gui.show();
		gui.game.controller('A', gui.result, gui.label2, gui.label3, gui.frame, gui.game);
	}

}
