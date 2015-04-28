import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Search{
//grid of letters.  Will fill with rows and columns after I find the dimensions
		static char[][] grid;

//MAIN//
	public static void main(String[] args) throws FileNotFoundException, IOException{
	//rows and columns
		int cols = 0;
		int rows = 0;

	//reference word-search.txt file that has the wordsearch board and the words to be found
		File wordsearch = new File(System.getProperty("user.dir") + "/word-search.txt");
		File wordsToFind = new File(System.getProperty("user.dir") + "/words.txt");

	//Scanners to find the number of columns/rows in the word-search.txt file
		Scanner numCols = new Scanner(wordsearch);
		Scanner numRows = new Scanner(wordsearch);
		Scanner numLines = new Scanner(numCols.nextLine());

	//number of columns in the wordsearch
		while(numLines.hasNext()){
			numLines.next();
			cols++;
		}

	//number of rows in the wordsearch. Check to see if the next line is empty, otherwise add 1 to rows.
		while(!numRows.nextLine().isEmpty()){
			rows++;
		}
		grid = new char[rows][cols];

		System.out.println("Your word grid is " + cols + " by " + rows);
		formWords(wordsToFind);
	}


//Copy the words from word file
	public static void formWords(File f) throws IOException { 
		BufferedReader in = null;
        FileReader fr = null;
        List<String> words = new ArrayList<String>();

        try {
            fr = new FileReader("words.txt");
            in = new BufferedReader(fr);
            String str;
            while ((str = in.readLine()) != null) {
                words.add((str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
            fr.close();
        }
        for (String d : words) System.out.println(d);
    } 
}
