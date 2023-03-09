package com.spring.course.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "The field must be filled in!")
    @Size(max = 30, message = "The person's name must consist of less than 30 characters!")
    @Column(name = "person_name")
    private String name;

    @NotEmpty(message = "The field must be filled in!")
    @Size(max = 50, message = "The person's surname must consist of less than 50 characters!")
    @Column(name = "person_surname")
    private String surname;

    @NotEmpty(message = "The field must be filled in!")
    @Size(max = 30, message = "The person's patronymic must consist of less than 30 characters!")
    @Column(name = "person_patronymic")
    private String patronymic;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Column(name = "date_of_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfCreation;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfCreation=" + dateOfCreation +
                ", books=" + books +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreated) {
        this.dateOfCreation = dateOfCreated;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
