package com.spring.course.entity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int personId;

    @NotEmpty(message = "The field must be filled in!")
    @Size(max = 30, message = "The person's name must consist of less than 30 characters!")
    private String name;
    @NotEmpty(message = "The field must be filled in!")
    @Size(max = 50, message = "The person's surname must consist of less than 50 characters!")
    private String surname;
    @NotEmpty(message = "The field must be filled in!")
    @Size(max = 30, message = "The person's patronymic must consist of less than 30 characters!")
    private String patronymic;

    @DecimalMin(value = "1930", message = "The field must be greater than 0!")
    private int year;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", year=" + year +
                '}';
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
