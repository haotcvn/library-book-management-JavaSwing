package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.SessionModel;

public class SessionService implements Querry<SessionModel> {
	public static SessionService getInstance() {
		return new SessionService();
	}
	@Override
	public ArrayList<SessionModel> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionModel selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(SessionModel obj) {
		String sql = "INSERT INTO sessions VALUES (null, ?, ?, ?, ?)";
		return executeUpdate(sql, obj.getName(), obj.getIp(), String.valueOf(obj.getDate()), String.valueOf(obj.getStatus()));
	}

	@Override
	public int update(SessionModel obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
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
	public ArrayList<SessionModel> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
		return 0;
	}

}
