/**
 * <p>A class that keeps track of the current state of a game of hangman.</p>
 * <p> The class is constructed with a secret word and some number of guesses.</p>
 * <p>Every time a letter is guessed, the state of the game is updated appropriately
 * integrating the guessed letter into the word, updating the number of guesses
 * remaining, etc.</p>
 * 
 * <p>This class can then be used by a user interface to administer a regular game of Hangman.</p>
 */
import java.util.*;
public class NormalHangMan extends HangmanGame
{
   
	/**
     * Constructor sets up the game to be played with a word and some number of
     * guesses.  The class should have private variables that keep track of:
     * <li>The original secret word
     * <li>The number of guesses remaining
     * <li>The number of letters that still need to be guessed
     * <li>The current state of word to be guessed (e.g. "L A B _ R A _ _ R Y")
     * @param currSecretWord the word that the player is trying to guess
     * @param numGuesses the number of guesses allowed
     */
    public NormalHangMan(String currSecretWord, int numGuesses, ArrayList<String> LetterHistory){
    	super();
        secretWord = currSecretWord;
        GuessRemainingNum = numGuesses;
        LetterLeftNum = secretWord.length(); 
        GuessResult=true;
        for(int i = 0; i < secretWord.length(); i++)
        {
            CurrentState += "_ ";
            searchWord(i);
            
        }
//        for (int i = 0; i < LetterHistory.length(); i++) {
//        	LetterGuessHistory.add("" + LetterHistory.charAt(i));
//        }
        LetterGuessHistory.addAll(LetterHistory);
    } 
    public void searchWord(int i)
    {
    	for(int j = i; j > 0; j--)
        {
            if(secretWord.charAt(i) == secretWord.charAt(j-1))
            {
                LetterLeftNum--;//If the letter appears many times in the secret word, it will be counted just once.
                break;
            }
        }
    }

public boolean isWin()
    {
        if(GuessRemainingNum == 0)
            return false;//if the user have no chance to guess again, it means the user loses.
        else
            return true;
    }
    public String displayGameState()
    {
        return CurrentState;
    }
    
    public boolean makeGuess(char guessedChar)
    {
    	if (Character.isLetter(guessedChar) == false) 
    		return GuessResult=false;
    	GuessResult=true;
        //boolean tempB = true;
        //LetterGuess = ch;
        for(int i = 0; i < secretWord.length(); i++)
        {
            if(secretWord.charAt(i) == guessedChar)//if the user guess right, adjust the current state.
            {
            	updateState(guessedChar);
                break;
            }
            else
            {
            	GuessResult=false;
            }
        }
        if(!RepeatInput(guessedChar))
        {
            updateLetterStats(guessedChar);
            return GuessResult;
        }
        else return GuessResult=false;
    }
    
    public void updateLetterStats(char guessedChar)
    {
    	LetterGuessHistory.add(guessedChar + "");

        if(GuessResult)
        {
            LetterLeftNum--;
        }
        else
        {
            GuessRemainingNum--;
        }
    }
    
    public void updateState(char guessedChar)
    {
    	 String newState = "";
         for(int j = 0; j < secretWord.length(); j++)
         {
             if(secretWord.charAt(j) == guessedChar)
             {
                 newState = newState + guessedChar + " ";
             }
             else
             {
                 newState = newState + CurrentState.charAt(2*j) + CurrentState.charAt(2*j+1);              
             }
         }
         CurrentState = newState;
         GuessResult=true;
    }
   
}
    
       