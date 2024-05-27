//package com.example.library.report;
//
//import com.example.library.dao.BookDAO;
//import com.example.library.model.Book;
//
//import java.util.List;
//
//public class BookReport {
//
//    private BookDAO bookDAO;
//
//    public BookReport() {
//        this.bookDAO = new BookDAO();
//    }
//
//    /**
//     * Tạo và trả về báo cáo danh sách sách.
//     *
//     * @return Chuỗi báo cáo danh sách sách.
//     */
//    public String generateBookListReport() {
//        List<Book> bookList = bookDAO.getAllBooks();
//        StringBuilder reportBuilder = new StringBuilder();
//        reportBuilder.append("------ Book List Report ------\n");
//        for (Book book : bookList) {
//            reportBuilder.append("Book ID: ").append(book.getId()).append("\n");
//            reportBuilder.append("Title: ").append(book.getTitle()).append("\n");
//            reportBuilder.append("Author: ").append(book.getAuthor()).append("\n");
//            reportBuilder.append("ISBN: ").append(book.getIsbn()).append("\n");
//            reportBuilder.append("Quantity: ").append(book.getQuantity()).append("\n");
//            reportBuilder.append("-------------------------------\n");
//        }
//        return reportBuilder.toString();
//    }
//}
