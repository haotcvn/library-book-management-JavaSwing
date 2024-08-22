package models;

public class ReturnModel {
	private int returnID;
	private int borrowID;
	private String book;
	private String reader;
	private String date;
	private int status;

	public ReturnModel() {
	}

	public ReturnModel(int borrowID, String date, int status) {
		this.borrowID = borrowID;
		this.date = date;
		this.status = status;
	}

	public ReturnModel(int returnID, int borrowID, String date, int status) {
		this.returnID = returnID;
		this.borrowID = borrowID;
		this.date = date;
		this.status = status;
	}

	
	public ReturnModel(int borrowID, String book, String reader, String date, int status) {
		this.borrowID = borrowID;
		this.book = book;
		this.reader = reader;
		this.date = date;
		this.status = status;
	}

	public int getReturnID() {
		return returnID;
	}

	public void setReturnID(int returnID) {
		this.returnID = returnID;
	}

	public int getBorrowID() {
		return borrowID;
	}

	public void setBorrowID(int borrowID) {
		this.borrowID = borrowID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader = reader;
	}
}
