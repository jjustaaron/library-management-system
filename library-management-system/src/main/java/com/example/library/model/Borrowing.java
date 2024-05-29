package com.example.library.model;

import java.sql.Date;

public class Borrowing {
    private int borrowId;
    private int bookId;
    private int cardId;
    private Date borrowDate;
    private Date returnDate;
    private Date actualReturnDate;
    private boolean isReturned;

    public Borrowing(int borrowId, int bookId, int cardId, Date borrowDate, Date returnDate, Date actualReturnDate, boolean isReturned) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.cardId = cardId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.actualReturnDate = actualReturnDate;
        this.isReturned = isReturned;
    }

    // Getters and Setters
    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}
