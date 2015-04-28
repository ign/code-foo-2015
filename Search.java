import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Search{
	public static void main(String[] args) throws FileNotFoundException{
		int cols = 0;
		int rows = 0;

//reference word-search.txt file that has the wordsearch board and the words to be found
		File wordsearch = new File(System.getProperty("user.dir") + "/word-search.txt");

//Scanners to find the number of columns/rows in the word-search.txt file
		Scanner numCols = new Scanner(wordsearch);
		Scanner numRows = new Scanner(wordsearch);
		Scanner numLines = new Scanner(numCols.nextLine());

//number of columns in the wordsearch
		while(numLines.hasNext()){
			numLines.next();
			cols++;
		}
		System.out.println(cols);

//number of rows in the wordsearch. Check to see if the next line is empty, otherwise add 1 to rows.
		while(!numRows.nextLine().isEmpty()){
			rows++;
		}
		System.out.println(rows);
		
	}
}
