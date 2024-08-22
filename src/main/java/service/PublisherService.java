package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.CategoryModel;
import models.PublisherModel;

public class PublisherService implements Querry<PublisherModel> {

	public static PublisherService getInstance() {
		return new PublisherService();
	}

	@Override
	public ArrayList<PublisherModel> selectAll() {
		ArrayList<PublisherModel> result = new ArrayList<>();
		String sql = "SELECT * FROM publishers";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				int publisherID = rs.getInt("publisherID");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				PublisherModel publisherModel = new PublisherModel(publisherID, name, address, phone);
				result.add(publisherModel);
			}
		} catch (SQLException ex) {
			Logger.getLogger(PublisherService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public PublisherModel selectById(int id) {
		PublisherModel publisherModel = null;
		String sql = "SELECT * FROM publishers WHERE publisherID = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int publisherID = rs.getInt("publisherID");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					publisherModel = new PublisherModel(publisherID, name, address, phone);
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(PublisherService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return publisherModel;
	}

	@Override
	public int insert(PublisherModel obj) {
		String sql = "INSERT INTO publishers (name, address, phone) VALUES(?, ?, ?)";
		return executeUpdate(sql, obj.getPublisherName(), obj.getAddress(), obj.getPhone());
	}

	@Override
	public int update(PublisherModel obj) {
		String sql = "UPDATE publishers SET name = ?, address = ?, phone = ? WHERE publisherID = ?";
		return executeUpdate(sql, obj.getPublisherName(), obj.getAddress(), obj.getPhone(),
				String.valueOf(obj.getPublisherID()));
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM publishers WHERE publisherID = ?";
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
			Logger.getLogger(PublisherService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public ArrayList<PublisherModel> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<PublisherModel> selectByCondition(String key, String value) {
		ArrayList<PublisherModel> result = new ArrayList<>();
		String sql = "SELECT * FROM publishers";
		if (key.equals("Mã nhà XB")) {
			sql = "SELECT * FROM publishers WHERE publishers.publisherID LIKE ?";
		} else if (key.equals("Tên nhà XB")) {
			sql = "SELECT * FROM publishers WHERE publishers.name LIKE ?";
		} else if (key.equals("Địa chỉ")) {
			sql = "SELECT * FROM publishers WHERE publishers.address LIKE ?";
		}else if (key.equals("Số điện thoại")) {
			sql = "SELECT * FROM publishers WHERE publishers.phone LIKE ?";
		}else {
			sql = "SELECT * FROM publishers";
		}
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, "%" + value + "%");
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					int publisherID = rs.getInt("publisherID");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					PublisherModel publisherModel = new PublisherModel(publisherID, name, address, phone);
					result.add(publisherModel);
				}
			} catch (SQLException ex) {
				Logger.getLogger(PublisherService.class.getName()).log(Level.SEVERE, null, ex);
			}

		} catch (SQLException ex) {
			Logger.getLogger(PublisherService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
		return 0;
	}

}
