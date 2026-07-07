package es.upm.da.etsisi.DABiblioteca.data.repositories.iRepositories;

import es.upm.da.etsisi.DABiblioteca.data.model.Entity;

import java.util.List;

public interface IRepository<T extends Entity> {

    void add(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> listAll();

    T findById(int id);

}
