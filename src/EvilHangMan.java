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
	private int numWords = 0;// count the number of possible secret words.
	private int secretStringLength;// the length of the secret string
	private boolean GuessResult = false;

	public EvilHangMan(int StringLength, int numGuesses) {
		super();
		GuessRemainingNum = numGuesses;
		secretStringLength = StringLength;
		LetterLeftNum = 26;
		Scanner Scanner = null;
		try {
			Scanner = new Scanner(new File("dictionary.txt"));// read the dictionary
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		int i = 0;
		while (Scanner.hasNext()) {
			String temp = Scanner.nextLine().toUpperCase();
			if (temp.length() == StringLength) {
				Wordlist.add(temp);
				i++;
				numWords++;
			}
		}

		for (i = 0; i < StringLength; i++) {
			CurrentState += "_ ";
		}
		Scanner.close();
	}
/*
	public String getSecretWord() {
		return secretWord;
	}

	public int numGuessesRemaining() {
		return guess;
	}

	public int numLettersRemaining() {
		return 26; // because they never get one right!
	}

	public boolean isWin() {
		return false;
	}

	public boolean gameOver() {
		if (GuessRemainingNum == 0)
			return true;
		else
			return false;
	}

	public String lettersGuessed() {
		return LetterGuessHistory;
	}

	public String displayGameState() {
		return CurrentState;
	}
*/
/*
	public boolean makeGuess(char ch) {
		GuessResult = false;
		LetterGuess = ch;
		if (Character.isLetter(ch) && !RepeatInput(ch)) {
			// adjust the Wordlist in order to avoid the word with the letter
			// user guessed
			int tempWordNum = 0;
			for (int i = 0; i < numWords; i++) {
				for (int j = 0; j < secretStringLength; j++) {
					if (Wordlist[i].charAt(j) == ch) {
						break;
					} else {
						if (j == secretStringLength - 1) {
							if (Wordlist[i].charAt(j) != ch) {
								tempWordNum++;
							}
						}
					}
				}
			}
			// we choose the words that don't contain the letter the user
			// guessed, and they will be the new possible secret words.
			String[] temp = new String[tempWordNum];
			int tempIndex = 0;
			for (int i = 0; i < numWords; i++) {
				for (int j = 0; j < secretStringLength; j++) {
					if (Wordlist[i].charAt(j) == ch) {
						break;
					} else {
						if (j == secretStringLength - 1) {
							if (Wordlist[i].charAt(j) != ch) {
								temp[tempIndex] = Wordlist[i];
								tempIndex++;
							}
						}
					}
				}
			}
			if (tempWordNum == 0) {

				OriginSecretWord = Wordlist[0];
				GuessResult = true;
			} else {
				OriginSecretWord = temp[0];
				numWords = tempWordNum;
				Wordlist = temp;
				GuessRemainingNum--;
				GuessResult = false;
			}
			if (!GuessResult) {
				LetterGuessHistory.add("" + LetterGuess);
			}

		} else return false;
		
		return GuessResult;
	}
	*/
	public boolean makeGuess(char ch) {
		GuessResult = false;
		LetterGuess = ch;
		if (Character.isLetter(ch) && !RepeatInput(ch)) {
			// adjust the Wordlist in order to avoid the word with the letter
			// user guessed
			int tempWordNum = 0;
			//add, bad, sad
			//guess a
			ArrayList<String> remove = new ArrayList<String>();
			for (int i = Wordlist.size() -1; i >= 0; i--) {
				for (int j = 0; j < secretStringLength; j++) {
					if (Wordlist.get(i).charAt(j) == ch) {
						if (!remove.contains(Wordlist.get(i))) remove.add(Wordlist.get(i));
						break;
					}/* else {
						if (j == secretStringLength - 1) {
							if (Wordlist.get(i).charAt(j) != ch) {
								tempWordNum++;
							}
						}
					}*/
				}
			}

			//Wordlist.remove(1);
			Wordlist.removeAll(remove);
			
			
			// we choose the words that don't contain the letter the user
			// guessed, and they will be the new possible secret words.
//			ArrayList<String> temp = new ArrayList<String>();
//			int tempIndex = 0;
//			for (int i = 0; i < numWords; i++) {
//				for (int j = 0; j < secretStringLength; j++) {
//					if (Wordlist.get(i).charAt(j) == ch) {
//						break;
//					} else {
//						if (j == secretStringLength - 1) {
//							if (Wordlist.get(i).charAt(j) != ch) {
//								temp.add(tempIndex, Wordlist.get(i));
//								tempIndex++;
//							}
//						}
//					}
//				}
//			}
			System.out.println(Wordlist.size() - remove.size());
			if (Wordlist.size() - remove.size() <= 0) {

			//	OriginSecretWord = Wordlist.get(0);
				GuessResult = true;
			} else {
				OriginSecretWord = Wordlist.get(0);
				//numWords = tempWordNum;
				GuessRemainingNum--;
				GuessResult = false;
			}
			if (!GuessResult) {
				LetterGuessHistory.add("" + LetterGuess);
			}

		} else return false;
		
		return GuessResult;
	}
    
}