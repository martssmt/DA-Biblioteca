package es.upm.da.etsisi.DABiblioteca;

public class DependencyInjector {

    private static final DependencyInjector instance = new DependencyInjector();

    private DependencyInjector() {

    }

    public static DependencyInjector getInstance() {
        return instance;
    }

    public void run(String[] args) {

    }

}
