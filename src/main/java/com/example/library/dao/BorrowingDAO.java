package com.example.library.dao;

import com.example.library.model.Borrowing;
//import com.example.library.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowingDAO {
    public List<Borrowing> getAllBorrowings() {
        List<Borrowing> borrowings = new ArrayList<>();
        String query = "SELECT * FROM Borrowing";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Borrowing borrowing = new Borrowing(
                        resultSet.getInt("borrow_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getInt("card_id"),
                        resultSet.getDate("borrow_date"),
                        resultSet.getDate("return_date"),
                        resultSet.getDate("actual_return_date"),
                        resultSet.getBoolean("is_returned")
                );
                borrowings.add(borrowing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowings;
    }

    public void addBorrowing(Borrowing borrowing) throws SQLException {
        // Kiểm tra xem borrowId đã tồn tại chưa
        if (isBorrowIdExists(borrowing.getBorrowId())) {
            throw new SQLException("Borrow ID already exists.");
        }

        String query = "INSERT INTO Borrowing (borrow_id, book_id, card_id, borrow_date, return_date, actual_return_date, is_returned) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, borrowing.getBorrowId());
            statement.setInt(2, borrowing.getBookId());
            statement.setInt(3, borrowing.getCardId());
            statement.setDate(4, borrowing.getBorrowDate());
            statement.setDate(5, borrowing.getReturnDate());
            statement.setDate(6, borrowing.getActualReturnDate());
            statement.setBoolean(7, borrowing.isReturned());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isBorrowIdExists(int borrowId) throws SQLException {
        String query = "SELECT COUNT(*) FROM Borrowing WHERE borrow_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, borrowId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
    public void returnBorrowing(int borrowId, Date actualReturnDate) {
        String query = "UPDATE Borrowing SET actual_return_date = ?, is_returned = ? WHERE borrow_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDate(1, actualReturnDate);
            statement.setBoolean(2, true); // Đánh dấu là đã trả sách
            statement.setInt(3, borrowId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
