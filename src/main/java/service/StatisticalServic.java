package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.StatisticalModel;

public class StatisticalServic {
	public static StatisticalServic getInstance() {
		return new StatisticalServic();
	}
	
	public StatisticalModel selectValue() {
		StatisticalModel statisticalModel = null;
		String sql = "SELECT\r\n"
				+ "    (SELECT COUNT(*) FROM books) AS numberOfBooks,\r\n"
				+ "    (SELECT COUNT(*) FROM borrows WHERE status = ?) AS numberOfBorrowed";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, 1);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int numberOfBooks = rs.getInt("numberOfBooks");
					int numberOfBorrowed = rs.getInt("numberOfBorrowed");
					statisticalModel = new StatisticalModel(numberOfBooks, numberOfBorrowed);
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return statisticalModel;
	}
}
