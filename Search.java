import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class  Search{
	public static void main(String[] args) throws FileNotFoundException{
		int cols = 0;

		File wordsearch = new File(System.getProperty("user.dir") + "/word-search.txt");
		Scanner columns = new Scanner(wordsearch);
		
		Scanner numLines = new Scanner(columns.nextLine());
		
		while(numLines.hasNext()){
			numLines.next();
			cols++;
		}
		System.out.println(cols);
	}
}
