package es.upm.da.etsisi.DABiblioteca.data.repositories.iRepositories;

import es.upm.da.etsisi.DABiblioteca.data.model.Book;

import java.util.List;

public interface IBookRepository extends IRepository<Book> {

    List<Book> findByTitle(String title);

    List<Book> findBySubject(Integer subjectId);

}
