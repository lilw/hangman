import java.util.*;
import java.io.*;


public class EvilHangMan extends HangmanGame {
	//private String secretWord = "";// To store the secret word
	//private int guess;// to store the number of guess for the user
	//private String state = "";// store the current guessing situation
	//private String letterGuessHistory = "";// store the letters user has tried
	//private char l;// the letter the user guess right now
	
	//private String[] Wordlist = new String[235000];// to store the dictionary
	private ArrayList<String> Wordlist = new ArrayList<String>();

	private int secretStringLength;// the length of the secret string
	//private boolean GuessResult = false;

	public EvilHangMan(int StringLength, int numGuesses) {
		super();
		GuessRemainingNum = numGuesses;
		secretStringLength = StringLength;
		LetterLeftNum = 26;
		GuessResult=false;
		
		readDictionary();
		for (int i = 0; i < StringLength; i++) {
			CurrentState += "_ ";
		}
		
	}
	public void readDictionary()
	{
		Scanner Scanner = null;
		try {
			Scanner = new Scanner(new File("dictionary.txt"));// read the dictionary
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//int i = 0;
		while (Scanner.hasNext()) {
			String currWord = Scanner.nextLine().toUpperCase();
			if (currWord.length() == secretStringLength) {
				Wordlist.add(currWord);

			}
		}
		Scanner.close();

	}
	
	public boolean makeGuess(char guessedChar) {
		GuessResult = false;
		//LetterGuess = ch;
		if (Character.isLetter(guessedChar) && !RepeatInput(guessedChar)) {
			// adjust the Wordlist in order to avoid the word with the letter
			// user guessed
			ArrayList<String> remove = new ArrayList<String>();
			for (int i = Wordlist.size() -1; i >= 0; i--) {
				for (int j = 0; j < secretStringLength; j++) {
					if (Wordlist.get(i).charAt(j) == guessedChar) {
						if (!remove.contains(Wordlist.get(i))) 
							remove.add(Wordlist.get(i));
					}
				}
			}

			Wordlist.removeAll(remove);
			
			//System.out.println(Wordlist.size() - remove.size());
			updateWordlistAndSecretWord(remove);
			if (!GuessResult)
				LetterGuessHistory.add("" + guessedChar);

		} 
		else 
			return GuessResult=false;
		
		return GuessResult;
	}
    public void updateWordlistAndSecretWord(ArrayList<String> remove)
    {
    	if (Wordlist.size() - remove.size() <= 0) {
			if(remove.size()>0)
				secretWord = remove.get(0);
			GuessResult = true;
		} else {
			secretWord = Wordlist.get(0);
			GuessRemainingNum--;
			GuessResult = false;
		}
		
    }
}