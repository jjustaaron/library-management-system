package com.example.library.ui;

import com.example.library.dao.AuthorDAO;
import com.example.library.model.Author;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AuthorPanel extends JPanel {
    private JTable authorTable;
    private DefaultTableModel authorTableModel;
    private AuthorDAO authorDAO;

    public AuthorPanel() {
        authorDAO = new AuthorDAO();
        setLayout(new BorderLayout());

        authorTableModel = new DefaultTableModel(new Object[]{"Author ID", "Name", "Contact"}, 0);
        authorTable = new JTable(authorTableModel);
        loadAuthors();
        add(new JScrollPane(authorTable), BorderLayout.CENTER);

        JPanel authorButtonPanel = new JPanel();
        JButton addAuthorButton = new JButton("Add Author");
        addAuthorButton.addActionListener(this::addAuthor);
        JButton editAuthorButton = new JButton("Edit Author");
        editAuthorButton.addActionListener(this::editAuthor);
        JButton deleteAuthorButton = new JButton("Delete Author");
        deleteAuthorButton.addActionListener(this::deleteAuthor);
        authorButtonPanel.add(addAuthorButton);
        authorButtonPanel.add(editAuthorButton);
        authorButtonPanel.add(deleteAuthorButton);
        add(authorButtonPanel, BorderLayout.SOUTH);
    }

    private void loadAuthors() {
        List<Author> authors = authorDAO.getAllAuthors();
        for (Author author : authors) {
            authorTableModel.addRow(new Object[]{author.getAuthorId(), author.getName(), author.getContact()});
        }
    }

    private void addAuthor(ActionEvent e) {
        JTextField authorIdField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();

        Object[] fields = {
                "Author ID:", authorIdField,
                "Name:", nameField,
                "Contact:", contactField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Author", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int authorId = Integer.parseInt(authorIdField.getText());
            String name = nameField.getText();
            String contact = contactField.getText();

            Author author = new Author(authorId, name, contact);
            authorDAO.addAuthor(author);
            authorTableModel.addRow(new Object[]{authorId, name, contact});
        }
    }

    private void editAuthor(ActionEvent e) {
        int selectedRow = authorTable.getSelectedRow();
        if (selectedRow >= 0) {
            int authorId = (int) authorTableModel.getValueAt(selectedRow, 0);
            String name = (String) authorTableModel.getValueAt(selectedRow, 1);
            String contact = (String) authorTableModel.getValueAt(selectedRow, 2);

            JTextField nameField = new JTextField(name);
            JTextField contactField = new JTextField(contact);

            Object[] fields = {
                    "Name:", nameField,
                    "Contact:", contactField
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Edit Author", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                name = nameField.getText();
                contact = contactField.getText();

                Author author = new Author(authorId, name, contact);
                authorDAO.updateAuthor(author);
                authorTableModel.setValueAt(name, selectedRow, 1);
                authorTableModel.setValueAt(contact, selectedRow, 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an author to edit.");
        }
    }

    private void deleteAuthor(ActionEvent e) {
        int selectedRow = authorTable.getSelectedRow();
        if (selectedRow >= 0) {
            int authorId = (int) authorTableModel.getValueAt(selectedRow, 0);
            authorDAO.deleteAuthor(authorId);
            authorTableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an author to delete.");
        }
    }
}
