//package com.example.library.ui;
//
//import com.example.library.dao.ReportDAO;
//import com.example.library.model.Book;
//import com.example.library.model.Member;
//import com.example.library.model.Borrowing;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.List;
//
//public class ReportPanel extends JPanel {
//    private JTextArea reportTextArea;
//    private ReportDAO reportDAO;
//
//    public ReportPanel() {
//        reportDAO = new ReportDAO();
//
//        setLayout(new BorderLayout());
//
//        reportTextArea = new JTextArea();
//        reportTextArea.setEditable(false); // Không cho phép chỉnh sửa nội dung
//        JScrollPane scrollPane = new JScrollPane(reportTextArea);
//        add(scrollPane, BorderLayout.CENTER);
//
//        JButton generateReportButton = new JButton("Generate Report");
//        generateReportButton.addActionListener(e -> generateReport());
//        add(generateReportButton, BorderLayout.SOUTH);
//    }
//
//    private void generateReport() {
//        StringBuilder reportBuilder = new StringBuilder();
//
//        // Tạo và hiển thị báo cáo danh sách sách
//        reportBuilder.append("------ Book List Report ------\n");
//        List<Book> bookList = reportDAO.getAllBooks();
//        for (Book book : bookList) {
//            reportBuilder.append("Book ID: ").append(book.getId()).append("\n");
//            reportBuilder.append("Title: ").append(book.getTitle()).append("\n");
//            reportBuilder.append("Author: ").append(book.getAuthor()).append("\n");
//            reportBuilder.append("ISBN: ").append(book.getIsbn()).append("\n");
//            reportBuilder.append("Quantity: ").append(book.getQuantity()).append("\n");
//            reportBuilder.append("-------------------------------\n");
//        }
//
//        // Tạo và hiển thị báo cáo danh sách thành viên
//        reportBuilder.append("------ Member List Report ------\n");
//        List<Member> memberList = reportDAO.getAllMembers();
//        for (Member member : memberList) {
//            reportBuilder.append("Member ID: ").append(member.getId()).append("\n");
//            reportBuilder.append("Name: ").append(member.getName()).append("\n");
//            reportBuilder.append("Position: ").append(member.getPosition()).append("\n");
//            reportBuilder.append("Contact: ").append(member.getContact()).append("\n");
//            reportBuilder.append("-------------------------------\n");
//        }
//
//        // Tạo và hiển thị báo cáo sách đã được mượn
////        reportBuilder.append("------ Borrowed Books Report ------\n");
////        List<Borrowing> borrowedBooksList = reportDAO.generateBorrowedBooksReport();
////        for (Borrowing borrowing : borrowedBooksList) {
////            reportBuilder.append("Book ID: ").append(borrowing.getBookId()).append("\n");
////            reportBuilder.append("Borrow Date: ").append(borrowing.getBorrowDate()).append("\n");
////            reportBuilder.append("Return Date: ").append(borrowing.getReturnDate()).append("\n");
////            reportBuilder.append("Actual Return Date: ").append(borrowing.getActualReturnDate()).append("\n");
////            reportBuilder.append("-------------------------------\n");
////        }
//
//        // Hiển thị báo cáo trên TextArea
//        reportTextArea.setText(reportBuilder.toString());
//    }
//}
