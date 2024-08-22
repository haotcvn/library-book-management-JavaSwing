package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.ReaderModel;

public class ReaderService implements Querry<ReaderModel> {
    public static ReaderService getInstance() {
        return new ReaderService();
    }

    @Override
    public ArrayList<ReaderModel> selectAll() {
        ArrayList<ReaderModel> result = new ArrayList<>();
        String sql = "SELECT * FROM readers";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                ReaderModel readerModel = new ReaderModel();
                readerModel.setReaderID(rs.getInt("readerID")); 
                readerModel.setName(rs.getString("name"));
                readerModel.setAddress(rs.getString("address"));
                readerModel.setPhoneNumber(rs.getString("phoneNumber"));
                readerModel.setGender(rs.getString("gender"));
                result.add(readerModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ReaderModel selectById(int id) {
        ReaderModel readerModel = null;
        String sql = "SELECT * FROM readers WHERE readerID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    readerModel = new ReaderModel();
                    readerModel.setReaderID(rs.getInt("readerID")); 
                    readerModel.setName(rs.getString("name"));
                    readerModel.setAddress(rs.getString("address"));
                    readerModel.setPhoneNumber(rs.getString("phoneNumber"));
                    readerModel.setGender(rs.getString("gender"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return readerModel;
    }

    @Override
    public int insert(ReaderModel obj) {
        String sql = "INSERT INTO readers VALUES (null, ?, ?, ?, ?)";
        return executeUpdate(sql, obj.getName(), obj.getAddress(), obj.getPhoneNumber(), obj.getGender());
    }

    @Override
    public int update(ReaderModel obj) {
        String sql = "UPDATE readers SET name = ?, address = ?, phoneNumber = ?, gender = ? WHERE readerID = ?";
        return executeUpdate(sql, obj.getName(), obj.getAddress(), obj.getPhoneNumber(), obj.getGender() ,String.valueOf(obj.getReaderID()));
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM readers WHERE readerID = ?";
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
            Logger.getLogger(ReaderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<ReaderModel> selectByCondition(String condition) {
        // Implement if needed
        return null;
    }

    @Override
    public int getAutoIncrement() {
        // Implement if needed
        return 0;
    }
}
