package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.BookModel;
import models.CategoryModel;

public class CategoryService implements Querry<CategoryModel> {
	public static CategoryService getInstance() {
		return new CategoryService();
	}
	@Override
	public ArrayList<CategoryModel> selectAll() {
		ArrayList<CategoryModel> result = new ArrayList<>();
		String sql = "SELECT * FROM categories";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				int categoryID = rs.getInt("categoryID");
				String name = rs.getString("name");
				CategoryModel categoryModel = new CategoryModel(categoryID, name);
				result.add(categoryModel);
			}
		} catch (SQLException ex) {
			Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public CategoryModel selectById(int id) {
		CategoryModel categoryModel = null;
		String sql = "SELECT * FROM categories WHERE categoryID = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int categoryID = rs.getInt("categoryID");
					String name = rs.getString("name");
					categoryModel = new CategoryModel(categoryID, name);
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return categoryModel;
	}

	@Override
	public int insert(CategoryModel obj) {
		String sql = "INSERT INTO categories (name) VALUES(?)";
		return executeUpdate(sql, obj.getCategoryName());
	}

	@Override
	public int update(CategoryModel obj) {
		String sql = "UPDATE categories SET name = ? WHERE categoryID = ?";
		return executeUpdate(sql, obj.getCategoryName(), String.valueOf(obj.getCategoryID()));
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM categories WHERE categoryID = ?";
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
			Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
	
	@Override
	public ArrayList<CategoryModel> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<CategoryModel> selectByCondition(String key, String value) {
		ArrayList<CategoryModel> result = new ArrayList<>();
		String sql = "SELECT * FROM categories";
		if (key.equals("Mã thể loại")) {
			sql = "SELECT * FROM categories WHERE categories.categoryID LIKE ?";
		} else if (key.equals("Tên thể loại")) {
			sql = "SELECT * FROM categories WHERE categories.name LIKE ?";
		} else {
			sql = "SELECT * FROM categories";
		}
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, "%" + value + "%");
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					int categoryID = rs.getInt("categoryID");
					String name = rs.getString("name");
					CategoryModel categoryModel = new CategoryModel(categoryID, name);
					result.add(categoryModel);
				}
			} catch (SQLException ex) {
				Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
			}

		} catch (SQLException ex) {
			Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
	
	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
		return 0;
	}

}
