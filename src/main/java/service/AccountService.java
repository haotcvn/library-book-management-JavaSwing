package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.AccountModel;
import models.BookModel;

public class AccountService implements Querry<AccountModel> {

	public static AccountService getInstance() {
		return new AccountService();
	}

	public AccountModel login(String user) {
		AccountModel accountModel = null;
		String sql = "SELECT accounts.*, users.userID FROM accounts JOIN users ON users.accountID = accounts.accountID WHERE accounts.username = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, user);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int accountID = rs.getInt("accountID");
					int userID = rs.getInt("userID");
					String username = rs.getString("username");
					String password = rs.getString("password");
					int status = rs.getInt("status");
					accountModel = new AccountModel(accountID, userID, username, password, status);
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return accountModel;
	}

	public int updatePassword(String email, String newPassword) {
		int result = 0;
		String sql = "UPDATE accounts SET password = ? WHERE email = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, newPassword);
			pst.setString(2, email);
			result = pst.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public ArrayList<AccountModel> selectAll() {
		return null;
	}

	@Override
	public ArrayList<AccountModel> selectByCondition(String condition) {

		return null;
	}

	@Override
	public AccountModel selectById(int id) {
		AccountModel accountModel = null;
		String sql = "SELECT * FROM accounts WHERE accountID = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int accountID = rs.getInt("accountID");
					int userID = rs.getInt("userID");
					String permissionID = rs.getString("permissionID");
					String username = rs.getString("username");
					String password = rs.getString("password");
					int status = rs.getInt("status");
					accountModel = new AccountModel();
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
		}
		return accountModel;
	}

	@Override
	public int insert(AccountModel obj) {
		String sql = "INSERT INTO books VALUES (?, ?, ?, ?, ?, ?)";
		return executeUpdate(sql, "");
	}

	@Override
	public int update(AccountModel obj) {
		String sql = "UPDATE books SET categoryID = ?, publisherID = ?, name = ?, author = ?, yaerPublisher = ?, numberPages WHERE bookID = ?";
		return executeUpdate(sql, "");
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
			Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
		return 0;
	}

}
