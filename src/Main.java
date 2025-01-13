public class Main {

    public static void main(String[] args) {
        InterfazUsuario interfaz;
        String fich=null;

        try {
            if (args.length>1) fich=args[1];
            interfaz=init(fich);
            interfaz.menu();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static InterfazUsuario init (String archivo) {
        InterfazUsuario interfaz;

        if (Utilidades.leerSiONo("¿Quiere cargar la biblioteca desde un archivo?")) {
            System.out.println("\tAVISO:");
            System.out.println("\t\t-Si el archivo no existe o la ruta es incorrecta, se inicializará una biblioteca vacía.");
            System.out.println();
            if (archivo==null) {
                System.out.println("No se ha introducido ningún archivo (ver primer aviso).");
                interfaz = new InterfazUsuario();
            } else {
                interfaz= InterfazUsuario.cargarDeArchivo(archivo);
            }
        } else {
            interfaz=new InterfazUsuario();
        }

        return interfaz;
    }

}
