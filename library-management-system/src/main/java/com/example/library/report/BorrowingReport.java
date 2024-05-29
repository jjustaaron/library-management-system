//package com.example.library.report;
//
//import com.example.library.dao.BorrowingDAO;
//import com.example.library.model.Borrowing;
//
//import java.util.List;
//
//public class BorrowingReport {
//
//    private BorrowingDAO borrowingDAO;
//
//    public BorrowingReport() {
//        this.borrowingDAO = new BorrowingDAO();
//    }
//
//    /**
//     * Tạo và trả về báo cáo danh sách phiếu mượn sách.
//     *
//     * @return Chuỗi báo cáo danh sách phiếu mượn sách.
//     */
//    public String generateBorrowingListReport() {
//        List<Borrowing> borrowingList = borrowingDAO.getAllBorrowings();
//        StringBuilder reportBuilder = new StringBuilder();
//        reportBuilder.append("------ Borrowing List Report ------\n");
//        for (Borrowing borrowing : borrowingList) {
//            reportBuilder.append("Borrow ID: ").append(borrowing.getBorrowId()).append("\n");
//            reportBuilder.append("Book ID: ").append(borrowing.getBookId()).append("\n");
//            reportBuilder.append("Card ID: ").append(borrowing.getCardId()).append("\n");
//            reportBuilder.append("Borrow Date: ").append(borrowing.getBorrowDate()).append("\n");
//            reportBuilder.append("Return Date: ").append(borrowing.getReturnDate()).append("\n");
//            reportBuilder.append("Actual Return Date: ").append(borrowing.getActualReturnDate()).append("\n");
//            reportBuilder.append("Is Returned: ").append(borrowing.isReturned()).append("\n");
//            reportBuilder.append("-------------------------------\n");
//        }
//        return reportBuilder.toString();
//    }
//}
