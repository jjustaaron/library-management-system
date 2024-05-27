package com.example.library;

import com.example.library.ui.LibraryUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryUI libraryUI = new LibraryUI();
            libraryUI.setVisible(true);
        });
    }
}
