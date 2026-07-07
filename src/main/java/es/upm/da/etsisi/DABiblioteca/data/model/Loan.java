package es.upm.da.etsisi.DABiblioteca.data.model;

import java.time.LocalDate;

public class Loan extends Entity {

    private LocalDate date;
    private Book book;
    private Student student;
    private boolean returned;

    public Loan(LocalDate date, Book book, Student student) {
        super(-1);
        this.date = date;
        this.book = book;
        this.student = student;
        this.returned = false;
    }

    public Loan(Book book, Student student) {
        super(-1);
        this.date = LocalDate.now();
        this.book = book;
        this.student = student;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

}
