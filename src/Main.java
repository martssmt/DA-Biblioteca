import model.InterfazUsuario;
import view.CLI;

public class Main {

    public static void main(String[] args) {
        InterfazUsuario interfaz;
        String fich = null;

        try {
            if (args.length > 0) fich = args[0];
            interfaz = init(fich);
            interfaz.menu();
        } catch (Exception ex) {
            System.out.println("SE PRODUJO UN ERROR DURANTE LA EJECUCIÓN: "+ex.getMessage());
        }
    }

    public static InterfazUsuario init(String archivo) {
        InterfazUsuario interfaz;
        System.out.println("\tAVISO:");
        System.out.println("\t\t-Si el archivo no existe o la ruta es incorrecta, se inicializará una biblioteca vacía.\n");
        if (CLI.leerSiONo("¿Quiere cargar la biblioteca desde un archivo?")) {
            if (archivo == null) {
                System.out.println("No se ha introducido ningún archivo (ver aviso).");
                interfaz = new InterfazUsuario();
            } else {
                interfaz = InterfazUsuario.cargarDeArchivo(archivo);
            }
        } else {
            interfaz = new InterfazUsuario();
        }
        return interfaz;
    }

}
