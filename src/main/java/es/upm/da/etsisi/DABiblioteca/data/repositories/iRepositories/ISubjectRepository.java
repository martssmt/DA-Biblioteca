package es.upm.da.etsisi.DABiblioteca.data.repositories.iRepositories;

import es.upm.da.etsisi.DABiblioteca.data.model.Degree;
import es.upm.da.etsisi.DABiblioteca.data.model.Subject;

import java.util.List;

public interface ISubjectRepository extends IRepository<Subject> {

    List<Subject> findByName(String name);

    List<Subject> listByDegree(Integer degreeId);

}
