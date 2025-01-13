import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {

    private static final Scanner sc = new Scanner(System.in);

    public static Scanner getSc() {
        return sc;
    }

    public static int leerNumero(String mensaje, int min, int max) {
        int resp=min-1;
        do {
            System.out.print(mensaje);
            try {
                resp = sc.nextInt();
                sc.nextLine(); // Limpia el buffer
                if (resp < min || resp > max) System.out.println("Número fuera del rango. Vuelva a introducirlo.");
            } catch (InputMismatchException ex) {
                sc.nextLine();
                System.out.println("Entrada no válida. Vuelva a intentarlo.\n");
                resp=min-1;
            }
        } while (resp<min||resp>max);
        return resp;
    }

    public static boolean leerSiONo(String mensaje) {
        boolean resp=false;
        String m="";
        do {
            System.out.print(mensaje+ "('S' para SÍ, 'N' para NO): ");
            m=sc.nextLine();
            if (!m.equalsIgnoreCase("S")&&!m.equalsIgnoreCase("N")) {
                System.out.println("Cadena no reconocida como posible respuesta. Vuelva a introducirla.\n");
            }
        } while (!m.equalsIgnoreCase("S")&&!m.equalsIgnoreCase("N"));
        if (m.equalsIgnoreCase("S")) resp=true; // TRUE será la respuesta 'SI'
        return resp;
    }

    public static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public static int leerNumPositivo(String mensaje) {
        int resp=-1;
        do {
            System.out.print(mensaje);
            try {
                resp=sc.nextInt();
                sc.nextLine(); // Limpia el buffer
                if (resp<0) System.out.println("Número fuera del rango. Vuelva a introducirlo.");
            } catch (InputMismatchException ex) {
                sc.nextLine();
                System.out.println("Entrada no válida. Vuelva a intentarlo.\n");
                resp=-1;
            }
        } while (resp<0);
        return resp;
    }

    public static String leerFecha(String mensaje) {
        boolean correcto=false;
        String resp="";
        do {
            System.out.print(mensaje);
            resp=sc.nextLine();
            String[] comprobar=resp.split("/");
            if (comprobar.length!=3) {
                try {
                    int dia = Integer.parseInt(comprobar[0]);
                    int mes = Integer.parseInt(comprobar[1]);
                    int anio = Integer.parseInt(comprobar[2]);
                    if (mes >= 1 && mes <= 12) {
                        switch (mes) {
                            case 1, 3, 5, 7, 8, 10, 12:
                                if (dia >= 1 && dia <= 31) correcto = true;
                                break;
                            case 4, 6, 9, 11:
                                if (dia >= 1 && dia <= 30) correcto = true;
                                break;
                            case 2:
                                if ((anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0)) { // bisiesto
                                    if (dia >= 1 && dia <= 29) correcto = true;
                                } else {
                                    if (dia >= 1 && dia <= 28) correcto = true;
                                }
                                break;
                        }
                    }
                } catch (NumberFormatException _) {}
            }
            if (!correcto) System.out.println("Fecha incorrecta. Vuelva a intentarlo\n");
        } while (!correcto);
        return resp;
    }

    public static String leerMatricula(String mensaje) {
        boolean correcto=false;
        String resp="";
        do {
            System.out.print(mensaje);
            resp=sc.nextLine();
            if (resp.matches("[a-zA-Z]{2}\\d{4}")) {  // Dos letras seguidas de cuatro dígitos
                correcto = true;
            } else {
                System.out.println("Matrícula incorrecta. Vuélvalo a intentar.\n");
            }
        } while (!correcto);
        return resp;
    }

    public static String leerTel(String mensaje) {
        boolean correcto=false;
        String resp="";
        do {
            System.out.print(mensaje);
            resp=sc.nextLine();
            if (resp.length()==9 && resp.matches("\\d{9}")) correcto = true;
            else System.out.println("Teléfono incorrecto. Vuélvalo a intentar.\n");
        } while (!correcto);
        return resp;
    }

    public static String leerCorreo(String mensaje) {
        boolean correcto=false;
        String resp="";
        do {
            System.out.print(mensaje);
            resp=sc.nextLine();
            if (resp.matches("@alumnos.upm.es$")||resp.matches("@upm.es$")) correcto = true;
            else System.out.println("Correo incorrecto. Vuélvalo a intentar.\n");
        } while (!correcto);
        return resp;
    }

    public static String leerFich(String mensaje) {
        System.out.print(mensaje);
        String resp=sc.nextLine();
        if (!resp.endsWith(":txt")) resp+=".txt";
        return resp;
    }

}
