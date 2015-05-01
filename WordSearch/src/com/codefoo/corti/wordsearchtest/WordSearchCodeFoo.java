package com.codefoo.corti.wordsearchtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * IGN Code Foo 2015 Word Search application What this program does is find
 * every matching word in a word search
 * 
 * @author John corti
 *
 */
public class WordSearchCodeFoo {
	// global param's 
	
	private char[][] wordBlock;
	private ArrayList<String> wordKey; 
	private String fileName;  
	private static final int DEMO_WIDTH = 20;
	private static final int DEMO_HEIGHT = 20;

	private FileReader reader;
	private Scanner inFile;

	public static void main(String[] args) 
	{
		//we create a local version of WordSearchCodeFoo and run the constructor
		WordSearchCodeFoo wstt = new WordSearchCodeFoo();

	}

	/**
	 * The constructor 
	 * 
	 * we call to methods in the constructor that will fill
	 * the two arrays and find all of the possible matches
	 */
	public WordSearchCodeFoo() {
		fileName = "word-search.txt";
		wordKey = new ArrayList<String>();
		wordBlock = new char[DEMO_HEIGHT][DEMO_WIDTH];
		fillwordBlockAndWordBank();
		solve();
	}

	/**
	 * This is where we fill the word bank and the letter bank
	 */
	private void fillwordBlockAndWordBank() {
		int col = 0;
		int row = 0;
		String readLine = "";

		File file = new File(System.getProperty("user.dir") + "/" + fileName);
		
		//this is the start of the reader
		try {
			reader = new FileReader(file);
			inFile = new Scanner(reader);
			
			while (inFile.hasNext()) {
				readLine = inFile.next();
				//here i check if it is uppercase and it only has 1 char
				if (readLine.length() == 1
						&& Character.isUpperCase(readLine.charAt(0))) {
					wordBlock[row][col] = readLine.charAt(0);
					col++;
				}
				
				if (col == 20) {
					col = 0;
					row++;
				}
				//here i check if it is more than 1 char and all of the chars are capital
				if (readLine.length() >= 2 && isUpper(readLine)) {
					wordKey.add(readLine);
				}
			}
			reader.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			System.err.println();
		}

		//these will print out the leter block and word bank side by side 
		for (int index = 0; index < wordKey.size(); index++) {
			for (int indexToo = 0; indexToo < 20; indexToo++) {
				if (index < 20) {
					System.out.print(wordBlock[index][indexToo] + " ");
				}
			}
			
			//this sorts it by alpha
			Collections.sort(wordKey);
			if (index <= wordKey.size() - index) {
				System.out.print("              " + wordKey.get(index));

				if (index < wordKey.size() && index < 12) {
					System.out.print("              "
							+ wordKey.get(wordKey.size() - index - 1));
				}

			}
			System.out.println();
		}
	}
	/**
	 * This checks if a str is all capitals
	 * 
	 * @param str 
	 * @return true if the str is capital false if it is not
	 */
	public boolean isUpper(String str) {
		for (int index = 0; index < str.length(); index++) {
			char c = str.charAt(index);
			if (c >= 97 && c <= 122) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * this solves thw word search
	 */

	private void solve() {
		
		boolean working = true;

		
		String[] directions = new String[] { "South", "East", "North", "West",
				"South-West", "South-East", "North-East", "North-West" };

		for (int row = 0; row < DEMO_HEIGHT; row++) {

			for (int col = 0; col < DEMO_WIDTH; col++) {

				for (String word : wordKey) {

					int charIndex = 0;
					int testRow = row;
					int testCol = col;

					int startR = 0;
					int startC = 0;
					int endR = 0;
					int endC = 0;

					String testWord = "";

					for (int d = 0; d < directions.length;) {
						if (wordBlock[testRow][testCol] == word
								.charAt(charIndex)) {
							testWord += wordBlock[testRow][testCol];
		
							 
							//I used a switch statement since i think it would be more efficient for searching through
							 
							switch (d) {
							case 0:
								while (working) {
									if (wordBlock[testRow][testCol] == word
											.charAt(charIndex)) {
										if (testWord.equals(word)) {
											startR = testRow - word.length()
													+ 1;
											startC = testCol;
											endR = testRow;
											endC = testCol;
											System.out.println(word
													+ " was found starting at "
													+ startR + "," + startC
													+ " going " + directions[d]
													+ " amd ending at " + endR
													+ "," + endC);
											working = false;
											break;
										}
										if (testRow < DEMO_HEIGHT - 1) {
											testRow++;
										}
										charIndex++;
										testWord += wordBlock[testRow][testCol];

									} else
										working = false;
								}
							case 1:
								while (working) {
									if (wordBlock[testRow][testCol] == word
											.charAt(charIndex)) {
										if (testWord.equals(word)) {
											startR = testRow;
											startC = testCol - word.length()
													+ 1;
											endR = testRow;
											endC = testCol;
											System.out.println(word
													+ " was found starting at "
													+ startR + "," + startC
													+ " going " + directions[d]
													+ " amd ending at " + endR
													+ "," + endC);

											working = false;
											break;
										}
										if (testCol < DEMO_WIDTH - 1) {
											++testCol;
										}
										charIndex++;
										testWord += wordBlock[testRow][testCol];
									} else {
										working = false;
									}
								}

							case 2:
								while (working) {
									if (wordBlock[testRow][testCol] == word
											.charAt(charIndex)) {
										if (testWord.equals(word)) {
											startR = testRow + word.length()
													- 1;
											startC = testCol;
											endR = testRow;
											endC = testCol;

											System.out.println(word
													+ " was found starting at "
													+ startR + "," + startC
													+ " going " + directions[d]
													+ " amd ending at " + endR
													+ "," + endC);
											working = false;
											break;
										}
										if (testRow > 0) {
											testRow--;
										}

										charIndex++;
										testWord += wordBlock[testRow][testCol];
									} else
										working = false;
								}

							case 3:
								while (working) {
									if (wordBlock[testRow][testCol] == word
											.charAt(charIndex)) {
										if (testWord.equals(word)) {
											startR = testRow;
											startC = testCol + word.length()
													- 1;
											endR = testRow;
											endC = testCol;
											System.out.println(word
													+ " was found starting at "
													+ startR + "," + startC
													+ " going " + directions[d]
													+ " amd ending at " + endR
													+ "," + endC);
											working = false;
											break;
										}
										if (testCol > 0) {
											testCol--;
										}
										charIndex++;
										testWord += wordBlock[testRow][testCol];
									} else {
										working = false;
									}

								}

							case 4:
								while (working) {
									if (wordBlock[testRow][testCol] == word
											.charAt(charIndex)) {
										if (testWord.equals(word)) {
											startR = testRow - word.length()
													+ 1;
											startC = testCol + word.length()
													- 1;
											endR = testRow;
											endC = testCol;
											System.out.println(word
													+ " was found starting at "
													+ startR + "," + startC
													+ " going " + directions[d]
													+ " amd ending at " + endR
													+ "," + endC);
											working = false;
											break;
										}
										if (testCol > 0
												&& testRow < DEMO_HEIGHT - 1) {
											--testCol;
											++testRow;
										}
										charIndex++;
										testWord += wordBlock[testRow][testCol];
									} else
										working = false;
								}

							case 5:
								while (working) {
									if (wordBlock[testRow][testCol] == word
											.charAt(charIndex)) {
										if (testWord.equals(word)) {
											startR = testRow - word.length()
													+ 1;
											startC = testCol - word.length()
													+ 1;
											endR = testRow;
											endC = testCol;
											System.out.println(word
													+ " was found starting at "
													+ startR + "," + startC
													+ " going " + directions[d]
													+ " amd ending at " + endR
													+ "," + endC);
											working = false;
											break;
										}
										if (testCol < DEMO_WIDTH - 1
												&& testRow < DEMO_HEIGHT - 1) {
											++testCol;
											++testRow;
										}

										charIndex++;
										testWord += wordBlock[testRow][testCol];
									} else
										working = false;
								}
							case 6:
								while (working) {
									if (wordBlock[testRow][testCol] == word
											.charAt(charIndex)) {
										if (testWord.equals(word)) {
											startR = testRow + word.length()
													- 1;
											startC = testCol - word.length()
													+ 1;
											endR = testRow;
											endC = testCol;
											System.out.println(word
													+ " was found starting at "
													+ startR + "," + startC
													+ " going " + directions[d]
													+ " amd ending at " + endR
													+ "," + endC);
											working = false;
											break;
										}
										if (testCol < DEMO_WIDTH - 1
												&& testRow > 0) {
											++testCol;
											--testRow;
										}

										charIndex++;
										testWord += wordBlock[testRow][testCol];
									} else
										working = false;
								}
							case 7:
								while (working) {
									if (wordBlock[testRow][testCol] == word
											.charAt(charIndex)) {
										if (testWord.equals(word)) {
											startR = testRow + word.length()
													- 1;
											startC = testCol + word.length()
													- 1;
											endR = testRow;
											endC = testCol;
											System.out.println(word
													+ " was found starting at "
													+ startR + "," + startC
													+ " going " + directions[d]
													+ " amd ending at " + endR
													+ "," + endC);
											working = false;
											break;
										}
										if (testCol > 0 && testRow > 0) {
											--testCol;
											--testRow;
										}

										charIndex++;
										testWord += wordBlock[testRow][testCol];
									} else
										working = false;
								}

							}
						}
						d++;
						charIndex = 0;
						testRow = row;
						testCol = col;
						testWord = "";
						working = true;

					}
				}
			}
		}

	}
}
