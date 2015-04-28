import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class  Test{
	public static void main(String[] args) throws FileNotFoundException{
		int cols = 0;
		File text = new File(System.getProperty("user.dir") + "/me.txt");
		Scanner numLines = new Scanner(text);
		while(numLines.hasNext()){
			numLines.next();
			cols++;
		}
		System.out.println(cols);
	}
}
