package nueve;

import java.util.Scanner;

public class MiString {
    public static String alReves(String cadena) {
        return new StringBuilder(cadena).reverse().toString();
    }

    public static String limpiaCaracteres(String cadena, String caracteres) {
        for (char c : caracteres.toCharArray()) {
            cadena = cadena.replace(String.valueOf(c), "");
        }
        return cadena;
    }

    public static String sustituye(String cadena, char viejo, char nuevo) {
        return cadena.replace(viejo, nuevo);
    }

    public static boolean todosIguales(String cadena) {
        return cadena.chars().distinct().count() <= 1;
    }

    public static String quitaEspacios(String cadena) {
        return cadena.replaceAll("\\s+", "");
    }

    public static boolean esNumeroEntero(String cadena) {
        return cadena.matches("-?\\d+");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese una cadena:");
        String cadena = scanner.nextLine();

        System.out.println("Cadena al revés: " + alReves(cadena));

        System.out.println("Ingrese caracteres a eliminar de la cadena:");
        String caracteresAEliminar = scanner.nextLine();
        System.out.println("Cadena después de eliminar caracteres: " + limpiaCaracteres(cadena, caracteresAEliminar));

        System.out.println("Ingrese el carácter a sustituir:");
        char viejo = scanner.nextLine().charAt(0);
        System.out.println("Ingrese el nuevo carácter:");
        char nuevo = scanner.nextLine().charAt(0);
        System.out.println("Cadena después de sustituir caracteres: " + sustituye(cadena, viejo, nuevo));

        System.out.println("¿Todos los caracteres son iguales? " + todosIguales(cadena));

        System.out.println("Cadena sin espacios: " + quitaEspacios(cadena));

        System.out.println("Ingrese una cadena para verificar si es un número entero:");
        String numeroEntero = scanner.nextLine();
        System.out.println("¿Es un número entero? " + esNumeroEntero(numeroEntero));

        scanner.close();
    }
}
