package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.ReturnModel;

public class ReturnService implements Querry<ReturnModel> {
    public static ReturnService getInstance() {
        return new ReturnService();
    }

    @Override
    public ArrayList<ReturnModel> selectAll() {
        ArrayList<ReturnModel> result = new ArrayList<>();
        String sql = "SELECT returns.returnID, returns.borrowID, returns.book, returns.reader, DATE_FORMAT(returns.date, '%d/%m/%Y') AS date, returns.status FROM returns";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                ReturnModel returnModel = new ReturnModel();
                returnModel.setReturnID(rs.getInt("returnID"));
                returnModel.setBorrowID(rs.getInt("borrowID"));
                returnModel.setBook(rs.getString("book"));
                returnModel.setReader(rs.getString("reader"));
                returnModel.setDate(rs.getString("date"));
                returnModel.setStatus(rs.getInt("status"));
                result.add(returnModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReturnService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ReturnModel selectById(int id) {
        ReturnModel returnModel = null;
        String sql = "SELECT returns.returnID, returns.borrowID, returns.book, returns.reader, DATE_FORMAT(returns.date, '%d/%m/%Y') AS date, returns.status FROM returns WHERE returnID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    returnModel = new ReturnModel();
                    returnModel.setReturnID(rs.getInt("returnID"));
                    returnModel.setBorrowID(rs.getInt("borrowID"));
                    returnModel.setBook(rs.getString("book"));
                    returnModel.setReader(rs.getString("reader"));
                    returnModel.setDate(rs.getString("date"));
                    returnModel.setStatus(rs.getInt("status"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReturnService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnModel;
    }

    @Override
    public int insert(ReturnModel obj) {
        String sql = "INSERT INTO returns VALUES (null, ?, ?, ?, ?, ?)";
        return executeUpdate(sql, String.valueOf(obj.getBorrowID()), obj.getBook(), obj.getReader(), obj.getDate(), String.valueOf(obj.getStatus()));
    }

    @Override
    public int update(ReturnModel obj) {
        String sql = "UPDATE returns SET borrowID = ?, date = ? WHERE returnID = ?";
        return executeUpdate(sql, String.valueOf(obj.getBorrowID()), obj.getDate(), String.valueOf(obj.getStatus()));
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM returns WHERE returnID = ?";
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
            Logger.getLogger(ReturnService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<ReturnModel> selectByCondition(String condition) {
        // Implement if needed
        return null;
    }

    @Override
    public int getAutoIncrement() {
        // Implement if needed
        return 0;
    }
}
