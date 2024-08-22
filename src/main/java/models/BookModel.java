package models;

public class BookModel {
	private int bookID;
	private int categoryID;
	private int publisherID;
	private String categoryName;
	private String publisherName;
	private String name;
	private String author;
	private int yearPublisher;
	private int numberPages;
	
	public BookModel() {}

	public BookModel(int bookID, String categoryName, String publisherName, String name, String author,
			int yearPublisher, int numberPages) {
		this.bookID = bookID;
		this.categoryName = categoryName;
		this.publisherName = publisherName;
		this.name = name;
		this.author = author;
		this.yearPublisher = yearPublisher;
		this.numberPages = numberPages;
	}

	public BookModel(int categoryID, int publisherID, String name, String author, int yearPublisher, int numberPages) {
		this.categoryID = categoryID;
		this.publisherID = publisherID;
		this.name = name;
		this.author = author;
		this.yearPublisher = yearPublisher;
		this.numberPages = numberPages;
	}

	public BookModel(int bookID, int categoryID, int publisherID, String name, String author, int yearPublisher,
			int numberPages) {
		this.bookID = bookID;
		this.categoryID = categoryID;
		this.publisherID = publisherID;
		this.name = name;
		this.author = author;
		this.yearPublisher = yearPublisher;
		this.numberPages = numberPages;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYearPublisher() {
		return yearPublisher;
	}

	public void setYearPublisher(int yearPublisher) {
		this.yearPublisher = yearPublisher;
	}

	public int getNumberPages() {
		return numberPages;
	}

	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}

	@Override
	public String toString() {
		return name.toString();
	}

}
