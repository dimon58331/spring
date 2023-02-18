package com.spring.course.entity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int bookId;
    @NotEmpty(message = "The field must be filled in!")
    @Size(max = 110, message = "The author's name must consist of less than 110 characters!")
    private String author;
    @NotEmpty(message = "The field must be filled in!")
    @Size(max = 30, message = "The book's name must consist of less than 30 characters!")
    private String bookName;
    @NotEmpty(message = "The field must be filled in!")
    @DecimalMin(value = "2000", message = "The field must be greater than 2000!")
    private int releaseYear;

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }


}
