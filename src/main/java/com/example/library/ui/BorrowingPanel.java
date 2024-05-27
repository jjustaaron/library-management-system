package com.example.library.ui;

import com.example.library.dao.BorrowingDAO;
import com.example.library.model.Borrowing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class BorrowingPanel extends JPanel {
    private JTable borrowingTable;
    private DefaultTableModel borrowingTableModel;
    private BorrowingDAO borrowingDAO;

    public BorrowingPanel() {
        borrowingDAO = new BorrowingDAO();
        setLayout(new BorderLayout());

        borrowingTableModel = new DefaultTableModel(new Object[]{"Borrow ID", "Book ID", "Card ID", "Borrow Date", "Return Date", "Actual Return Date", "Is Returned"}, 0);
        borrowingTable = new JTable(borrowingTableModel);
        loadBorrowings();
        add(new JScrollPane(borrowingTable), BorderLayout.CENTER);

        JPanel borrowingButtonPanel = new JPanel();
        JButton addBorrowingButton = new JButton("Add Borrowing");
        addBorrowingButton.addActionListener(this::addBorrowing);
        JButton returnBorrowingButton = new JButton("Return Borrowing");
        returnBorrowingButton.addActionListener(this::returnBorrowing);
        borrowingButtonPanel.add(addBorrowingButton);
        borrowingButtonPanel.add(returnBorrowingButton);
        add(borrowingButtonPanel, BorderLayout.SOUTH);
    }

    private void loadBorrowings() {
        List<Borrowing> borrowings = borrowingDAO.getAllBorrowings();
        for (Borrowing borrowing : borrowings) {
            borrowingTableModel.addRow(new Object[]{borrowing.getBorrowId(), borrowing.getBookId(), borrowing.getCardId(), borrowing.getBorrowDate(), borrowing.getReturnDate(), borrowing.getActualReturnDate(), borrowing.isReturned()});
        }
    }

    private void addBorrowing(ActionEvent e) {
        // Tạo panel để nhập thông tin
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        JTextField borrowIdField = new JTextField(10);
        JTextField bookIdField = new JTextField(10);
        JTextField memberIdField = new JTextField(10);
        JTextField borrowDateField = new JTextField(10);
        JTextField returnDateField = new JTextField(10);

        JLabel borrowIdLabel = new JLabel("Borrow ID:");
        JLabel bookIdLabel = new JLabel("Book ID:");
        JLabel memberIdLabel = new JLabel("Member ID:");
        JLabel borrowDateLabel = new JLabel("Borrow Date (yyyy-MM-dd):");
        JLabel returnDateLabel = new JLabel("Return Date (yyyy-MM-dd):");

        // Thêm các thành phần vào panel
        inputPanel.add(borrowIdLabel);
        inputPanel.add(borrowIdField);
        inputPanel.add(bookIdLabel);
        inputPanel.add(bookIdField);
        inputPanel.add(memberIdLabel);
        inputPanel.add(memberIdField);
        inputPanel.add(borrowDateLabel);
        inputPanel.add(borrowDateField);
        inputPanel.add(returnDateLabel);
        inputPanel.add(returnDateField);

        // Hiển thị panel nhập liệu
        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Enter Borrowing Information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Lấy thông tin từ các trường nhập liệu
            try {
                int borrowId = Integer.parseInt(borrowIdField.getText());
                int bookId = Integer.parseInt(bookIdField.getText());
                int memberId = Integer.parseInt(memberIdField.getText());
                String borrowDateString = borrowDateField.getText();
                String returnDateString = returnDateField.getText();

                Date borrowDate = Date.valueOf(borrowDateString);
                Date returnDate = Date.valueOf(returnDateString);

                // Kiểm tra returnDate < borrowDate
                if (returnDate.before(borrowDate)) {
                    JOptionPane.showMessageDialog(this, "Return date cannot be before borrow date.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Tạo đối tượng Borrowing mới
                Borrowing borrowing = new Borrowing(borrowId, bookId, memberId, borrowDate, returnDate, null, false);

                // Gọi phương thức addBorrowing từ BorrowingDAO để thêm mới vào cơ sở dữ liệu
                borrowingDAO.addBorrowing(borrowing);

                // Xóa dữ liệu trong bảng và hiển thị lại
                borrowingTableModel.setRowCount(0); // Xóa tất cả các hàng
                loadBorrowings(); // Hiển thị lại dữ liệu mới
                JOptionPane.showMessageDialog(this, "Borrowing added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid IDs.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void returnBorrowing(ActionEvent e) {
        // Lấy thông tin về phiếu mượn cần trả sách từ bảng hoặc người dùng
        int selectedRow = borrowingTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a borrowing to return.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int borrowId = (int) borrowingTableModel.getValueAt(selectedRow, 0); // Lấy Borrow ID từ hàng đã chọn
        String returnDateString = JOptionPane.showInputDialog(this, "Enter actual return date (yyyy-MM-dd):");
        if (returnDateString == null || returnDateString.isEmpty()) {
            return;
        }

        try {
            Date actualReturnDate = Date.valueOf(returnDateString);

            // Gọi phương thức returnBorrowing từ BorrowingDAO để cập nhật thông tin trong cơ sở dữ liệu
            borrowingDAO.returnBorrowing(borrowId, actualReturnDate);

            // Xóa dữ liệu trong bảng và hiển thị lại
            borrowingTableModel.setRowCount(0); // Xóa tất cả các hàng
            loadBorrowings(); // Hiển thị lại dữ liệu mới
            JOptionPane.showMessageDialog(this, "Book returned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
