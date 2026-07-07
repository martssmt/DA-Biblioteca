package es.upm.da.etsisi.DABiblioteca.data.repositories.iRepositories;

import es.upm.da.etsisi.DABiblioteca.data.model.Student;

import java.util.List;

public interface IStudentRepository extends IRepository<Student> {

    Student findByStudentId(String studentId);

    List<Student> findByName(String name);

    Student findByMail(String Mail);

    Student findByPhone(String Phone);

}
