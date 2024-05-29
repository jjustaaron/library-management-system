package com.example.library.ui;

import javax.swing.*;

public class LibraryUI extends JFrame {
    private JTabbedPane tabbedPane;

    public LibraryUI() {
        setTitle("Library Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        add(tabbedPane);

        tabbedPane.addTab("Books", new BookPanel());
        tabbedPane.addTab("Members", new MemberPanel());
        tabbedPane.addTab("Borrowings", new BorrowingPanel());
//        tabbedPane.addTab("Reports", new ReportPanel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryUI libraryUI = new LibraryUI();
            libraryUI.setVisible(true);
        });
    }
}
