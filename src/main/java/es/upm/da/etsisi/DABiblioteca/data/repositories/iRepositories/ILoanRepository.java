package es.upm.da.etsisi.DABiblioteca.data.repositories.iRepositories;

import es.upm.da.etsisi.DABiblioteca.data.model.Loan;

import java.time.LocalDate;
import java.util.List;

public interface ILoanRepository extends IRepository<Loan> {

    List<Loan> findByStudent(Integer studentId);

    List<Loan> findByBook(Integer bookId);

    List<Loan> findByStudentAndBook(Integer studentId, Integer bookId);

    List<Loan> findByDate(LocalDate date);

    List<Loan> listLentBooks();

}
