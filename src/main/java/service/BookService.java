package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.BookModel;

public class BookService implements Querry<BookModel> {
	public static BookService getInstance() {
		return new BookService();
	}

	@Override
	public ArrayList<BookModel> selectAll() {
		ArrayList<BookModel> result = new ArrayList<>();
		String sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
				+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
				+ "JOIN publishers ON publishers.publisherID = books.publisherID";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				BookModel bookModel = new BookModel();
				bookModel.setBookID(rs.getInt("bookID"));
				bookModel.setCategoryName(rs.getString("categoryName"));
				bookModel.setPublisherName(rs.getString("publisherName"));
				bookModel.setName(rs.getString("name"));
				bookModel.setAuthor(rs.getString("author"));
				bookModel.setYearPublisher(rs.getInt("yearPublisher"));
				bookModel.setNumberPages(rs.getInt("numberPages"));
				result.add(bookModel);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public BookModel selectById(int id) {
		BookModel bookModel = null;
		String sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
				+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
				+ "JOIN publishers ON publishers.publisherID = books.publisherID " + "WHERE books.bookID = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int bookID = rs.getInt("bookID");
					String categoryName = rs.getString("categoryName");
					String publisherName = rs.getString("publisherName");
					String name = rs.getString("name");
					String author = rs.getString("author");
					int yearPublisher = rs.getInt("yearPublisher");
					int numberPages = rs.getInt("numberPages");
					bookModel = new BookModel(bookID, categoryName, publisherName, name, author, yearPublisher,
							numberPages);
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return bookModel;
	}

	@Override
	public int insert(BookModel obj) {
		String sql = "INSERT INTO books (categoryID, publisherID, name, author, yearPublisher, numberPages) VALUES (?, ?, ?, ?, ?, ?)";
		return executeUpdate(sql, String.valueOf(obj.getCategoryID()), String.valueOf(obj.getPublisherID()),
				obj.getName(), obj.getAuthor(), String.valueOf(obj.getYearPublisher()),
				String.valueOf(obj.getNumberPages()));
	}

	@Override
	public int update(BookModel obj) {
		String sql = "UPDATE books SET categoryID = ?, publisherID = ?, name = ?, author = ?, yearPublisher = ?, numberPages = ? WHERE bookID = ?";
		return executeUpdate(sql, String.valueOf(obj.getCategoryID()), String.valueOf(obj.getPublisherID()),
				obj.getName(), obj.getAuthor(), String.valueOf(obj.getYearPublisher()),
				String.valueOf(obj.getNumberPages()), String.valueOf(obj.getBookID()));
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM books WHERE bookID = ?";
		return executeUpdate(sql, String.valueOf(id));
	}

	private int executeUpdate(String sql, String... parameters) {
		int result = 0;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			for (int i = 0; i < parameters.length; i++) {
				pst.setString(i + 1, parameters[i]);
			}
			result = pst.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public ArrayList<BookModel> selectByCondition(String condition) {
		return null;
	}

	public ArrayList<BookModel> selectByCondition(String key, String value) {
		ArrayList<BookModel> result = new ArrayList<>();
		String sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
				+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
				+ "JOIN publishers ON publishers.publisherID = books.publisherID";
		if (key.equals("Mã sách")) {
			sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
					+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
					+ "JOIN publishers ON publishers.publisherID = books.publisherID WHERE books.bookID LIKE ?";
		} else if (key.equals("Thể loại")) {
			sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
					+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
					+ "JOIN publishers ON publishers.publisherID = books.publisherID WHERE categories.name LIKE ?";
		} else if (key.equals("Nhà XB")) {
			sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
					+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
					+ "JOIN publishers ON publishers.publisherID = books.publisherID WHERE publishers.name LIKE ?";
		} else if (key.equals("Tên sách")) {
			sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
					+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
					+ "JOIN publishers ON publishers.publisherID = books.publisherID WHERE books.name LIKE ?";
		} else if (key.equals("Tác giả")) {
			sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
					+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
					+ "JOIN publishers ON publishers.publisherID = books.publisherID WHERE books.author LIKE ?";
		} else if (key.equals("Năm XB")) {
			sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
					+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
					+ "JOIN publishers ON publishers.publisherID = books.publisherID WHERE books.yearPublisher LIKE ?";
		} else if (key.equals("Số trang")) {
			sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
					+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
					+ "JOIN publishers ON publishers.publisherID = books.publisherID WHERE books.numberPages LIKE ?";
		} else {
			sql = "SELECT books.bookID, categories.name AS categoryName, publishers.name AS publisherName, books.name, books.author, books.yearPublisher, books.numberPages "
					+ "FROM books " + "JOIN categories ON categories.categoryID = books.categoryID "
					+ "JOIN publishers ON publishers.publisherID = books.publisherID";
		}
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, "%" + value + "%");
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					int bookID = rs.getInt("bookID");
					String categoryName = rs.getString("categoryName");
					String publisherName = rs.getString("publisherName");
					String name = rs.getString("name");
					String author = rs.getString("author");
					int yearPublisher = rs.getInt("yearPublisher");
					int numberPages = rs.getInt("numberPages");
					BookModel bookModel = new BookModel(bookID, categoryName, publisherName, name, author,
							yearPublisher, numberPages);
					result.add(bookModel);
				}
			} catch (SQLException ex) {
				Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
			}

		} catch (SQLException ex) {
			Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
		return 0;
	}

}
