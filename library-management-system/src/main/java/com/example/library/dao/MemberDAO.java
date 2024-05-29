package com.example.library.dao;

import com.example.library.model.Member;
//import com.example.library.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    public List<Member> getAllEmployees() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM Employee";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Member member = new Member(
                        resultSet.getInt("employee_id"),
                        resultSet.getString("employee_name"),
                        resultSet.getString("position"),
                        resultSet.getString("contact")
                );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public void addEmployee(Member member) {
        String query = "INSERT INTO Employee (employee_id, name, position, contact) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, member.getId());
            statement.setString(2, member.getName());
            statement.setString(3, member.getPosition());
            statement.setString(4, member.getContact());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Member member) {
        String query = "UPDATE Employee SET name = ?, position = ?, contact = ? WHERE employee_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, member.getName());
            statement.setString(2, member.getPosition());
            statement.setString(3, member.getContact());
            statement.setInt(4, member.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int employeeId) {
        String query = "DELETE FROM Employee WHERE employee_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, employeeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
