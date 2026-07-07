package es.upm.da.etsisi.DABiblioteca.data.repositories.iRepositories;

import es.upm.da.etsisi.DABiblioteca.data.model.Admin;

import java.util.List;

public interface IAdminRepository extends IRepository<Admin> {

    List<Admin> findByUsername(String username);

}
