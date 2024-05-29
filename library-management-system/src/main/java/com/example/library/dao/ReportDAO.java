//package com.example.library.dao;
//
//import com.example.library.model.Book;
//import com.example.library.model.Member;
//import com.example.library.model.Borrowing;
////import com.example.library.util.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReportDAO {
//
//    /**
//     * Lấy danh sách tất cả các sách từ cơ sở dữ liệu.
//     *
//     * @return Danh sách các sách.
//     */
//    public List<Book> getAllBooks() {
//        List<Book> books = new ArrayList<>();
//        String query = "SELECT * FROM Book";
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Book book = new Book(
//                        resultSet.getInt("id"),
//                        resultSet.getString("title"),
//                        resultSet.getString("author"),
//                        resultSet.getString("genre"),
//                        resultSet.getInt("published_year")
////                        resultSet.getString("isbn")
//                );
//                books.add(book);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }
//
//    /**
//     * Lấy danh sách tất cả các thành viên từ cơ sở dữ liệu.
//     *
//     * @return Danh sách các thành viên.
//     */
//    public List<Member> getAllMembers() {
//        List<Member> members = new ArrayList<>();
//        String query = "SELECT * FROM Member";
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Member member = new Member(
//                        resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("email"),
//                        resultSet.getString("phone")
//                );
//                members.add(member);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return members;
//    }
//
//    /**
//     * Lấy danh sách các sách đang được mượn từ cơ sở dữ liệu.
//     *
//     * @return Danh sách các sách đang được mượn.
//     */
//    public List<Book> getBorrowedBooks() {
//        List<Book> borrowedBooks = new ArrayList<>();
//        String query = "SELECT DISTINCT b.* FROM Book b INNER JOIN Borrowing br ON b.id = br.book_id WHERE br.is_returned = false";
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Book book = new Book(
//                        resultSet.getInt("id"),
//                        resultSet.getString("title"),
//                        resultSet.getString("author"),
//                        resultSet.getString("genre"),
//                        resultSet.getInt("published_year")
////                        resultSet.getString("isbn")
//                );
//                borrowedBooks.add(book);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return borrowedBooks;
//    }
//}
