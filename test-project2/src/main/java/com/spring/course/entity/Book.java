package com.spring.course.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "The field must be filled in!")
    @Size(max = 110, message = "The author's name must consist of less than 110 characters!")
    @Column(name = "book_author")
    private String author;

    @NotEmpty(message = "The field must be filled in!")
    @Size(max = 30, message = "The book's name must consist of less than 30 characters!")
    @Column(name = "book_name")
    private String bookName;

    @Column(name = "date_of_release")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy")
    private Date dateOfRelease;

    @Column(name = "date_of_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfCreated;

    @Column(name = "date_of_taken")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfTaken;

    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                ", dateOfRelease=" + dateOfRelease +
                ", dateOfCreated=" + dateOfCreated +
                ", dateOfTaken=" + dateOfTaken +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public Date getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(Date dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }

    public Date getDateOfTaken() {
        return dateOfTaken;
    }

    public void setDateOfTaken(Date dateOfTaken) {
        this.dateOfTaken = dateOfTaken;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
