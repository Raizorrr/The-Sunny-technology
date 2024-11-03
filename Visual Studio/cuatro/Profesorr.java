package cuatro;

import java.util.Scanner;

class Profesor {
    protected String nombre;
    protected int edad;
    protected int añosConsolidados;

    public Profesor(String nombre, int edad, int añosConsolidados) {
        this.nombre = nombre;
        this.edad = edad;
        this.añosConsolidados = añosConsolidados;
    }

    @Override
    public String toString() {
        return "Profesor: " + nombre + ", Edad: " + edad + ", Años Consolidados: " + añosConsolidados;
    }
}

class ProfesorEmerito extends Profesor {
    private int añosEmerito;

    public ProfesorEmerito(String nombre, int edad, int añosConsolidados, int añosEmerito) {
        super(nombre, edad, añosConsolidados);
        this.añosEmerito = añosEmerito;
    }

    public double obtenerSalarioBase() {
        // Acceso directo a añosConsolidados, permitido por ser protected en la superclase
        return 925 + añosConsolidados * 12.25 + 23.40 * añosEmerito;
    }

    @Override
    public String toString() {
        return super.toString() + ", Años como Emérito: " + añosEmerito + ", Salario Base: " + obtenerSalarioBase();
    }
}

public class Profesorr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos al usuario
        System.out.print("Ingrese el nombre del profesor: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la edad del profesor: ");
        int edad = scanner.nextInt();

        System.out.print("Ingrese los años consolidados del profesor: ");
        int añosConsolidados = scanner.nextInt();

        System.out.print("Ingrese los años como emérito del profesor: ");
        int añosEmerito = scanner.nextInt();

        // Crear una instancia de ProfesorEmerito con los datos ingresados
        ProfesorEmerito profesor = new ProfesorEmerito(nombre, edad, añosConsolidados, añosEmerito);

        // Mostrar la información del profesor
        System.out.println("\n--- Información del Profesor Emérito ---");
        System.out.println(profesor);
        
        scanner.close();
    }
}
