import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class HangmanGame
{

    protected String OriginSecretWord = ""; //To store the secret word
	protected int GuessRemainingNum;// to store the number of guess for the user
	protected int LetterLeftNum; //number of letters remaining 26 for evilHangman
	protected String CurrentState = ""; // store the current guessing situation
	protected ArrayList<String> LetterGuessHistory;// store the letters user has tried
	//protected char LetterGuess;// the letter the user guess right now
	protected boolean GuessResult;
	public HangmanGame() {
		LetterGuessHistory = new ArrayList<String>();
	}
	/**
     * @return the original secret word.
     */
    public String getSecretWord() {
		return OriginSecretWord;
	}
    
    /**
     * Simulates the player guessing a letter in the word and updates the state of game
     * accordingly -- number of guesses remaining, number of letters 
     * if the guessed letter is not in the word, and hasn't been guessed yet, numGuesses is decremented
     * if the guessed letter is in the word, and hasn't been guessed yet, then update the current state of
     * the guessed word to reveal the position(s) of the letter(s) that was just guessed, and update numLettersRemaining.
     * There should be no guess penalty for guessing a letter that has already been 
     * guessed, just return false.  
     * @param ch the char that is the next letter to be guessed on the word
     * @return true if the game isn't over and the guessed letter was in the word, false otherwise
     */
    public boolean makeGuess(char ch) {
		return false;
	}
    /*
     * This handles the logic of sending info to the Game object.
     */
    public HangmanGame controller(char InputLetter, JLabel result, JLabel label2, JLabel label3, JFrame frame, HangmanGame game)
    {
        //handle the user choice, and pass the data to the model
        char nextLetter = Character.toUpperCase(InputLetter);

        if(makeGuess(nextLetter))
        {
            if(game instanceof EvilHangMan)//judge whether the hangman is evil
            {
                //if in the evil statement, and the user guess right, 
            	// it means it is the time to turn the evil to the regular hangmam
                result.setText("Yes!");
                System.out.println("true true");
                String RealSecretString = getSecretWord();
                //System.out.println(getSecretWord());
                int GuessRemaining = numGuessesRemaining();
                String LetterHistory = lettersGuessed();
                //LetterHistory+=nextLetter;
                game=new NormalHangMan(RealSecretString, GuessRemaining,LetterHistory);//turn the evil to regular hangman
                //IsEvil = false;
                game.makeGuess(nextLetter);//re-value the user guess when turn to the regular hangman for the first time
                
            }
            else
            {
                result.setText("Yes!");
                System.out.println("Yes nothing else");
            }
        }
        else
        {
            result.setText("Nope!");
        }

        label2.setText("Secret Word: "+ game.displayGameState());
        label3.setText(String.valueOf("Guesses Remaining: "+game.numGuessesRemaining()));
        if(gameOver())
        {
            if(isWin())
            {
                new GUI_Winner(displayGameState(),frame);
            }
            else
            {
                new GUI_Loser(getSecretWord(),frame);
            }
        }
        return game;
    }
    
    /**
     * @return true if there are no more letters to be guessed and there is still at least one guess remaining
     */
    public boolean isWin() {
       return false;
	} 
    
    /** 
     * @return true if either num guesses remaining is 0 or num letters remaining to be guessed is 0.
     */
    public boolean gameOver() {
        if(GuessRemainingNum == 0 || LetterLeftNum == 0)
            return true;
        else
            return false;
	} 
    public boolean getGuessResult()
    {
    	return GuessResult;
    }
    /**
     * @return the number of guesses the player has left
     */
    public int numGuessesRemaining() {
		return GuessRemainingNum;
	}
    
    /**
     * The number of letters remaining to be guessed in the secret word - i.e.
     * the number of blank spaces the player sees, which may be different from
     * the actually number of letters it will take to fill those blanks.
     * @return the number of letters in the secret word that still have to be guessed
     */
    public int numLettersRemaining() {
		return LetterLeftNum;
	}
    
    /**
     * Gives a display-ready String-ified version of the current state of the secret word, showing letters
     * that have been guessed and blank spaces for letters that still need to be guessed.
     * For example if the secrect word is "LABORATORY" and the player has guessed L, A, B, R, and Y
     * the method might return something like "L A B _ R A _ _ R Y"
     * @return a String of the current state of the secret word.
     */
    public String displayGameState() {
    	System.out.println("displayGameState called");
		return CurrentState;
	}
    
    /**
     * A String representing the letters guessed so far in the order they were guessed.
     * Duplicates should not be added.
     * @return a String showing which letters have already been guessed.
     */
    public String lettersGuessed() {
    	String letters = "";
    	for (int i = 0; i < LetterGuessHistory.size(); i ++) {
    		letters += LetterGuessHistory.get(i);
    	}
    	return letters;
	}

	public boolean RepeatInput(char c) {
		String character = c + "";
//		for (int i = 0; i < LetterGuessHistory.size(); i++) {
//			if (LetterGuessHistory.get(i).equals(character)) return true;
//		}
//		return false;
		return LetterGuessHistory.contains(character);
	}

}
