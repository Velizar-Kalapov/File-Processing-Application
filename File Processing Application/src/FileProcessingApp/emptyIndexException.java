package FileProcessingApp;

public class emptyIndexException extends Exception {
	
	private String message;
	
	public emptyIndexException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
