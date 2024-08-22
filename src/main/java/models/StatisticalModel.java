package models;

public class StatisticalModel {
	private int numberOfBooks;
	private int numberOfBorrowed;
	
	public StatisticalModel(int numberOfBooks, int numberOfBorrowed) {
		this.numberOfBooks = numberOfBooks;
		this.numberOfBorrowed = numberOfBorrowed;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(int numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

	public int getNumberOfBorrowed() {
		return numberOfBorrowed;
	}

	public void setNumberOfBorrowed(int numberOfBorrowed) {
		this.numberOfBorrowed = numberOfBorrowed;
	}
}
