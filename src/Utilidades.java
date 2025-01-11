import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {

    private static final Scanner sc = new Scanner(System.in);

    public static Scanner getSc() {
        return sc;
    }

    public int leerNumero(String mensaje, int min, int max) {
        int resp=min-1;
        do {
            System.out.print(mensaje);
            try {
                resp = sc.nextInt();
                sc.nextLine(); // Limpia el buffer
                if (resp < min || resp > max) System.out.println("Número fuera del rango. Vuelva a introducirlo.");
            } catch (InputMismatchException ex) {
                sc.nextLine();
                System.out.println("Entrada no válida. Vuelva a intentarlo.");
                resp=min-1;
            }
        } while (resp<min||resp>max);
        return resp;
    }

    public boolean leerSiONo(String mensaje) {
        boolean resp=false;
        String m="";
        do {
            System.out.print(mensaje+ "('S' para SÍ, 'N' para NO): ");
            m=sc.nextLine();
            if (!m.equalsIgnoreCase("S")&&!m.equalsIgnoreCase("N")) {
                System.out.println("Cadena no reconocida como posible respuesta. Vuelva a introducirla.");
            }
        } while (!m.equalsIgnoreCase("S")&&!m.equalsIgnoreCase("N"));
        if (m.equalsIgnoreCase("S")) resp=true; // TRUE será la respuesta 'SI'
        return resp;
    }

    public String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public int leerNumPositivo(String mensaje) {
        int resp=-1;
        do {
            System.out.print(mensaje);
            try {
                resp=sc.nextInt();
                sc.nextLine(); // Limpia el buffer
                if (resp<0) System.out.println("Número fuera del rango. Vuelva a introducirlo.");
            } catch (InputMismatchException ex) {
                sc.nextLine();
                System.out.println("Entrada no válida. Vuelva a intentarlo.");
                resp=-1;
            }
        } while (resp<0);
        return resp;
    }

}
