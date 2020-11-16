package FileProcessingApp;

import java.util.StringJoiner;

public class Line {

	private String line;
	private String[] wordsInLine;
	
	public Line(String lineFromFile) {
		this.line = lineFromFile;
		this.wordsInLine = line.split("\\s+");
	}
	
	
	public void printLine() {
		for (int i = 0 ; i < this.wordsInLine.length ; i++) {
			System.out.print(this.wordsInLine[i] + " ");
		}
		System.out.println("");
	}
	
	public String[] getWordsInLine() {
		return this.wordsInLine;
	}
	
	public int getWordCountInLine() {
		return this.wordsInLine.length;
	}
	
	
	public String findWordByIndex(int index) throws emptyIndexException{
		for (int i = 0 ; i < this.wordsInLine.length ; i++) {
			if (index == i) {
				return this.wordsInLine[i];
			}
		}
		throw new emptyIndexException("No word with matching index found.");
	}
	
	public void setWordFromIndex(int index, String wordToSwapWith ) throws emptyIndexException{
		for (int i = 0 ; i < this.wordsInLine.length ; i++) {
			if (index == i) {
				this.wordsInLine[i] = wordToSwapWith;
				setLineFromArr();
				return;
			}
		}
		throw new emptyIndexException("No word with matching index found.");
	}
	
	public String getLine() {
		return this.line;
	}


	private void setLineFromArr() {
		StringJoiner joiner = new StringJoiner(" ");
		for (String word : this.wordsInLine) {
			joiner.add(word);
		}
		this.setLine(joiner.toString());
	}
	
	public void setLine(String line) {
		this.line = line;
	}
	
}
