package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.BorrowModel;
import models.ReaderModel;

public class BorrowService implements Querry<BorrowModel> {
	public static BorrowService getInstance() {
		return new BorrowService();
	}

	@Override
	public ArrayList<BorrowModel> selectAll() {
		ArrayList<BorrowModel> result = new ArrayList<>();
		String sql = "SELECT borrows.borrowID, books.name AS bookName, readers.name AS readerName, readers.address, readers.phoneNumber, DATE_FORMAT(borrows.date, '%d/%m/%Y') AS date, borrows.status "
				+ "FROM borrows "
				+ "JOIN books ON books.bookID = borrows.bookID "
				+ "JOIN readers ON readers.readerID = borrows.readerID WHERE borrows.status = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, 1);
			try (ResultSet rs = pst.executeQuery()){
				while (rs.next()) {
					BorrowModel borrowModel = new BorrowModel();
					ReaderModel readerModel = new ReaderModel();
					
					readerModel.setName(rs.getString("readerName"));
					readerModel.setAddress(rs.getString("address"));
					readerModel.setPhoneNumber(rs.getString("phoneNumber"));
					
					borrowModel.setBorrowID(rs.getInt("borrowID"));
					borrowModel.setBookName(rs.getString("bookName"));
					borrowModel.setReaderModel(readerModel);
					borrowModel.setDate(rs.getString("date"));
					result.add(borrowModel);
				}
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
	
	public ArrayList<BorrowModel> selectStatusOff() {
		ArrayList<BorrowModel> result = new ArrayList<>();
		String sql = "SELECT borrows.borrowID, books.name AS bookName, readers.name AS readerName, readers.address, readers.phoneNumber, DATE_FORMAT(borrows.date, '%d/%m/%Y') AS date, borrows.status "
				+ "FROM borrows "
				+ "JOIN books ON books.bookID = borrows.bookID "
				+ "JOIN readers ON readers.readerID = borrows.readerID WHERE borrows.status = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, 0);
			try (ResultSet rs = pst.executeQuery()){
				while (rs.next()) {
					BorrowModel borrowModel = new BorrowModel();
					ReaderModel readerModel = new ReaderModel();
					
					readerModel.setName(rs.getString("readerName"));
					readerModel.setAddress(rs.getString("address"));
					readerModel.setPhoneNumber(rs.getString("phoneNumber"));
					
					borrowModel.setBorrowID(rs.getInt("borrowID"));
					borrowModel.setBookName(rs.getString("bookName"));
					borrowModel.setReaderModel(readerModel);
					borrowModel.setDate(rs.getString("date"));
					result.add(borrowModel);
				}
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public BorrowModel selectById(int id) {
		BorrowModel borrowModel = null;
		String sql = "SELECT borrows.borrowID, books.name AS bookName, readers.name AS readerName, readers.address, readers.phoneNumber, DATE_FORMAT(borrows.date, '%d/%m/%Y') AS date, borrows.status "
				+ "FROM borrows " + "JOIN books ON books.bookID = borrows.bookID "
				+ "JOIN readers ON readers.readerID = borrows.readerID WHERE borrows.borrowID = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					borrowModel = new BorrowModel();
					ReaderModel readerModel = new ReaderModel();

					readerModel.setName(rs.getString("readerName"));
					readerModel.setAddress(rs.getString("address"));
					readerModel.setPhoneNumber(rs.getString("phoneNumber"));

					borrowModel.setBorrowID(rs.getInt("borrowID"));
					borrowModel.setBookName(rs.getString("bookName"));
					borrowModel.setReaderModel(readerModel);
					borrowModel.setDate(rs.getString("date"));
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return borrowModel;
	}

	@Override
	public int insert(BorrowModel obj) {
		String sql = "INSERT INTO borrows VALUES (null, ?, ?, ?, ?, ?)";
		return executeUpdate(sql, String.valueOf(obj.getBookID()), String.valueOf(obj.getReaderModel().getReaderID()), String.valueOf(1),
				obj.getDate(), String.valueOf(obj.getStatus()));
	}

	@Override
	public int update(BorrowModel obj) {
		String sql = "UPDATE borrows SET bookID = ?, readerID = ?, date = ?, status = ? WHERE borrowID = ?";
		return executeUpdate(sql, String.valueOf(obj.getBookID()), String.valueOf(obj.getReaderModel().getReaderID()),
				obj.getDate(), String.valueOf(obj.getStatus()), String.valueOf(obj.getBorrowID()));
	}

	public int updateStatusOff(int id) {
		String sql = "UPDATE borrows SET status = ? WHERE borrowID = ?";
		return executeUpdate(sql, String.valueOf(0), String.valueOf(id));
	}
	
	@Override
	public int delete(int id) {
		String sql = "DELETE FROM borrows WHERE borrowID = ?";
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
	public ArrayList<BorrowModel> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
		return 0;
	}

}
