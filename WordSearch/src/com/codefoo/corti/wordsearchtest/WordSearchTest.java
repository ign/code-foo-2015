package com.codefoo.corti.wordsearchtest;

public class WordSearchTest {
	private String gridBlock;
	private String[] grid;
	private String wordList;
	private String[] word;
	
	public WordSearchTest()
	{
		wordList = "OBAMA YES ASOON";
	
		gridBlock ="OBAMATAMA\n"
			 	 + "SMGSWGBOJ\n"
				 + "TKHVOONVX\n"
				 + "EDIEPONAZ\n"
				 + "VVSEYMNCI";
		grid = gridBlock.split("\n");
		word = wordList.split(" ");
		
	}
	
	public static void main(String[] args) {
		WordSearchTest wt = new WordSearchTest();
		wt.fill();
		wt.findA(0);
	}
	
	public void fill()
	{
		String ex = "";
		for (int i = 0; i < grid.length; i++) {
			ex = "";
			String[] g = grid[i].split("(?!^)");
			for(int ind = 0; ind < g.length; ind++){
				ex += g[ind] + " ";
			}
			if(i < word.length){
				System.out.println(ex + "       " + word[i]); 		
			}else{
			System.out.println(ex); 
			}
		}
	}
	
	public void findA(int index)
	{
		String expload = word[index];
		String[] exploadedArray = expload.split("(?!^)"); 
		String cord = "";
		
		for (int i = 0; i < exploadedArray.length; i++) {
			
		}
	}
}
