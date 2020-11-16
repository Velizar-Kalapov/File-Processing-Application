package FileProcessingApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;




public class FileReader {
	
	private File file;
	private Line[] listOfLines;
	private int numberOfLines;

	public FileReader(File path) {
		this.file = path;
		numberOfLines = countLines();
		this.listOfLines = new Line[numberOfLines];
		readFile(listOfLines);
		//printFile();
		
	}
	
	private void readFile(Line[] listOfLines) {
		try{
		Scanner scanner = new Scanner(this.file);
		int i = 0;
		while (scanner.hasNextLine()) {
			Line line = new Line(scanner.nextLine());
			listOfLines[i] = line;
		
			i++;
		}
		} catch(FileNotFoundException e) {
			System.out.println(e);
		}
	 }
	
	private int countLines() {
		int i = 0;
		try{
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			 i++;
			 scanner.nextLine();
		}
		
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return i;
		
	}
	
	public void swapLines(int a , int b) throws ArrayIndexOutOfBoundsException  {
		a--; 
		b--;
		if(a > this.numberOfLines || b > this.numberOfLines || a < 0 || b < 0) { 
			throw new ArrayIndexOutOfBoundsException("Line not found");
		}
		try {
			
			FileWriter writer = new FileWriter(file);
			for (int i = 0; i < listOfLines.length; i++) {
				if (i == a ){
				writer.write(listOfLines[b].getLine()+ "\n");	
			//	System.out.println(listOfLines[b].getLine());
				} else if (i == b) {
				writer.write(listOfLines[a].getLine()+ "\n");	
			//	System.out.println(listOfLines[a].getLine());
				}else {
				writer.write(listOfLines[i].getLine()+ "\n");	
			//	System.out.println(listOfLines[i].getLine());
				}
				
			} writer.close();
	    } catch(IOException e) {
			System.out.println("Error with writing to file"); 
		}
		
		
	}
	
	public void swapWordsFromIndex(int firstLineNum, int secondLineNum ,int firstWordIndex, int secondWordIndex)  {
		try {
		String tempString = listOfLines[firstLineNum].findWordByIndex(firstWordIndex);
		listOfLines[firstLineNum].setWordFromIndex(firstWordIndex, listOfLines[secondLineNum].findWordByIndex(secondWordIndex));
		listOfLines[secondLineNum].setWordFromIndex(secondWordIndex, tempString);
		} catch (emptyIndexException e) {
			System.out.println(e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Line not found" );
		}
		try {
			FileWriter writer = new FileWriter(file);
			for (Line line : listOfLines) {
				writer.write(line.getLine() + "\n");
			} writer.close();
		} catch(IOException e) {
			System.out.println( e.getMessage() + "\n Error with writing to file");
			
		} 
	}
	
	
	public void printFile() {
		for (Line line: listOfLines){
			line.printLine();
		}
	}

	public Line[] getListOfLines() {
		return this.listOfLines;
	}
	
	public int getNumberOfLines() {
		return this.numberOfLines;
	}
	
	public Line getLineFromIndex(int index) {
		for(int i = 0; i < this.countLines(); i ++) {
			if(i==index) {
				return this.listOfLines[i];
			}
		}
		return null;
	}
	
	public int longestLine() {
		int longest = 0;
		for(int i = 0; i < this.numberOfLines ; i++) {
			if (this.listOfLines[i].getWordCountInLine() > longest) {
				longest = this.listOfLines[i].getWordCountInLine();
			}
		}
		return longest;
	}
	
	
	
		
}
