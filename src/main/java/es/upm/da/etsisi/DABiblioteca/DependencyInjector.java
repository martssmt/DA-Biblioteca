package es.upm.da.etsisi.DABiblioteca;

import es.upm.da.etsisi.DABiblioteca.data.repositories.hibernate.JPAUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DependencyInjector {

    private static final DependencyInjector instance = new DependencyInjector();

    private DependencyInjector() {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        Logger.getLogger("org.hibernate.orm.deprecation").setLevel(Level.OFF);
    }

    public static DependencyInjector getInstance() {
        return instance;
    }

    public void run(String[] args) {
        try {

        } finally {
            JPAUtil.shutdown();
        }
    }

}
