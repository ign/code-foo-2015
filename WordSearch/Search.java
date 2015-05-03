import java.util.*;
import java.io.*;
import java.awt.Point;

//COORDINATES START AT 0.  (0,0) IS THE FIRST LETTER ON THE TOP LEFT


/*Needs to have two separate files.  One with words to look for, and one with the grid.  There does need to be a space between letters in the grid, 
but there has to be no empty spaces in the words.txt file*/  

public class Search{
//grid of letters.  Will fill with rows and columns after I find the dimensions
		static char[][] grid;
		static List<String> words = new ArrayList<String>();

//MAIN//
	public static void main(String[] args) throws FileNotFoundException, IOException{
	
	//rows and columns
		int cols = 0;
		int rows = 0;
		boolean found = false;
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

		formWords(wordsToFind);
		formGrid(wordsearch);

	//Convert Array words into a List of Strings called words2
		String words2[] = new String[words.size()];
		words2 = words.toArray(words2);

	//loop through finding each word for the total number of words that exist
for(int a=0; a < words2.length; a++){
searchloop:		for(int i = 0 ; i < rows ; i++){
				for(int j = 0 ; j < cols ; j++){
					if(grid[i][j] == words2[a].charAt(0)){
						Direction d = searchAround(i,j,words2[a]);
						if(d != null){
							found = true;
							System.out.println(words2[a]+" ("+(i)+","+j+") :  " + d);
							break searchloop;	//stop looping
						}	
					}
				}
			}
		}
	}


//Copy the grid from wordsearch file into a 2d char array
	public static void formGrid(File f) throws FileNotFoundException{
		Scanner fileScanner = new Scanner(f);	
		int i = 0;
		int j = 0;
		String line = fileScanner.nextLine();
		while(!line.isEmpty()){
			Scanner scanner = new Scanner(line);
			while(scanner.hasNext()){
				grid[i][j] = scanner.next().charAt(0);
				j++;
			}
			j = 0;
			i++;
			line = fileScanner.nextLine();
		}
	}
//Copy to words from the words.txt file into an array of words
	public static void formWords(File f) throws IOException { 
		BufferedReader in = null;
        FileReader fr = null;

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
        //for (String d : words) System.out.println(d);
    } 

// Search function for only in one direction.  Used in the searchAround function to search in all directions.
	public static boolean searchAhead(Point p, Direction d, String word){
		int i = 1; 				//skip first char in word as we have already found a match 
		char[] array = word.toCharArray();
		while(i < array.length){
			if(isValid(p) && array[i] == grid[p.x][p.y]){
				p = d.fromPos(p.x,p.y);
				i++;
			}
			else
				return false; 	//mismatch in chars, word not found
		}
		return true; 			//no mismatches and traversed full word, so word found
	}
//Uses searchAhead to look through every direction for the continuation of a word.
	public static Direction searchAround(int x, int y, String word){
		for(Direction dir : Direction.values()){
			if(searchAhead(dir.fromPos(x,y), dir, word))
				return dir;
		}

		return null; //no match found
		
	}

//is the Point within the boundaries of the grid?
	public static boolean isValid(Point p){
		return p.x >= 0 && p.x < grid.length && p.y >= 0 && p.y < grid[0].length;
	}
//Java enum Direction
	public enum Direction {
	NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST;

	public Point fromPos(int x, int y) {
		switch (this) {
		case NORTH:
			return new Point(x - 1, y);
		case SOUTH:
			return new Point(x + 1, y);
		case WEST:
			return new Point(x, y - 1);
		case NORTHEAST:
			return new Point(x - 1, y + 1);
		case NORTHWEST:
			return new Point(x - 1, y - 1);
		case SOUTHEAST:
			return new Point(x + 1, y + 1);
		case SOUTHWEST:
			return new Point(x + 1, y - 1);
		default:
			return new Point(x, y + 1);
		}
	}

    }


}

