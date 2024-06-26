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

        authorTableModel = new DefaultTableModel(new Object[]{"Author ID", "Name"}, 0);
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
            authorTableModel.addRow(new Object[]{author.getAuthorId(), author.getName()});
        }
    }

    private void addAuthor(ActionEvent e) {
        JTextField authorIdField = new JTextField();
        JTextField nameField = new JTextField();

        Object[] fields = {
                "Author ID:", authorIdField,
                "Name:", nameField,
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Author", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int authorId = Integer.parseInt(authorIdField.getText());
            String name = nameField.getText();

            Author author = new Author(authorId, name);
            authorDAO.addAuthor(author);
            authorTableModel.addRow(new Object[]{authorId, name});
        }
    }

    private void editAuthor(ActionEvent e) {
        int selectedRow = authorTable.getSelectedRow();
        if (selectedRow >= 0) {
            int authorId = (int) authorTableModel.getValueAt(selectedRow, 0);
            String name = (String) authorTableModel.getValueAt(selectedRow, 1);

            JTextField nameField = new JTextField(name);

            Object[] fields = {
                    "Name:", nameField,
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Edit Author", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                name = nameField.getText();

                Author author = new Author(authorId, name);
                authorDAO.updateAuthor(author);
                authorTableModel.setValueAt(name, selectedRow, 1);
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
