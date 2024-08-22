package models;

public class BorrowModel {
	private int borrowID;
	private int bookID;
	private String bookName;
	private ReaderModel readerModel;
	private String date;
	private int status;

	public BorrowModel() {
	}

	public BorrowModel(int bookID, ReaderModel readerModel, String date, int status) {
		this.bookID = bookID;
		this.readerModel = readerModel;
		this.date = date;
		this.status = status;
	}

	public BorrowModel(int borrowID, int bookID, ReaderModel readerModel, String date, int status) {
		this.borrowID = borrowID;
		this.bookID = bookID;
		this.readerModel = readerModel;
		this.date = date;
		this.status = status;
	}

	
	public BorrowModel(int borrowID, String bookName, ReaderModel readerModel, String date, int status) {
		this.borrowID = borrowID;
		this.bookName = bookName;
		this.readerModel = readerModel;
		this.date = date;
		this.status = status;
	}

	public int getBorrowID() {
		return borrowID;
	}

	public void setBorrowID(int borrowID) {
		this.borrowID = borrowID;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public ReaderModel getReaderModel() {
		return readerModel;
	}

	public void setReaderModel(ReaderModel readerModel) {
		this.readerModel = readerModel;
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

//	@Override
//	public String toString() {
//		return String.valueOf(borrowID).toString();
//	}
}
