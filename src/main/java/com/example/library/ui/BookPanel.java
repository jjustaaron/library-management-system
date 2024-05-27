package com.example.library.ui;

import com.example.library.dao.BookDAO;
import com.example.library.model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BookPanel extends JPanel {
    private JTable bookTable;
    private DefaultTableModel bookTableModel;
    private BookDAO bookDAO;

    public BookPanel() {
        bookDAO = new BookDAO();
        setLayout(new BorderLayout());

        bookTableModel = new DefaultTableModel(new Object[]{"ID", "Title", "Author", "ISBN", "Quantity"}, 0);
        bookTable = new JTable(bookTableModel);
        loadBooks();
        add(new JScrollPane(bookTable), BorderLayout.CENTER);

        JPanel bookButtonPanel = new JPanel();
        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(this::addBook);
        JButton editBookButton = new JButton("Edit Book");
        editBookButton.addActionListener(this::editBook);
        JButton deleteBookButton = new JButton("Delete Book");
        deleteBookButton.addActionListener(this::deleteBook);
        bookButtonPanel.add(addBookButton);
        bookButtonPanel.add(editBookButton);
        bookButtonPanel.add(deleteBookButton);
        add(bookButtonPanel, BorderLayout.SOUTH);
    }

    private void loadBooks() {
        List<Book> books = bookDAO.getAllBooks();
        for (Book book : books) {
            bookTableModel.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getQuantity()});
        }
    }

    private void addBook(ActionEvent e) {
        JTextField idField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField isbnField = new JTextField();
        JTextField quantityField = new JTextField();

        Object[] fields = {
                "ID:", idField,
                "Title:", titleField,
                "Author:", authorField,
                "ISBN:", isbnField,
                "Quantity:", quantityField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            int quantity = Integer.parseInt(quantityField.getText());

            Book book = new Book(id, title, author, isbn, quantity);
            bookDAO.addBook(book);
            bookTableModel.addRow(new Object[]{id, title, author, isbn, quantity});
        }
    }

    private void editBook(ActionEvent e) {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) bookTableModel.getValueAt(selectedRow, 0);
            String title = (String) bookTableModel.getValueAt(selectedRow, 1);
            String author = (String) bookTableModel.getValueAt(selectedRow, 2);
            String isbn = (String) bookTableModel.getValueAt(selectedRow, 3);
            int quantity = (int) bookTableModel.getValueAt(selectedRow, 4);

            JTextField titleField = new JTextField(title);
            JTextField authorField = new JTextField(author);
            JTextField isbnField = new JTextField(isbn);
            JTextField quantityField = new JTextField(String.valueOf(quantity));

            Object[] fields = {
                    "Title:", titleField,
                    "Author:", authorField,
                    "ISBN:", isbnField,
                    "Quantity:", quantityField
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Edit Book", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                title = titleField.getText();
                author = authorField.getText();
                isbn = isbnField.getText();
                quantity = Integer.parseInt(quantityField.getText());

                Book book = new Book(id, title, author, isbn, quantity);
                bookDAO.updateBook(book);
                bookTableModel.setValueAt(title, selectedRow, 1);
                bookTableModel.setValueAt(author, selectedRow, 2);
                bookTableModel.setValueAt(isbn, selectedRow, 3);
                bookTableModel.setValueAt(quantity, selectedRow, 4);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a book to edit.");
        }
    }

    private void deleteBook(ActionEvent e) {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) bookTableModel.getValueAt(selectedRow, 0);
            bookDAO.deleteBook(id);
            bookTableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a book to delete.");
        }
    }
}
