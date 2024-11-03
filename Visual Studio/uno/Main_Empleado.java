

import java.util.Scanner;
public class Main_Empleado {
   

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Empleado[] empleados = new Empleado[4];

 
        for (int i = 0; i < empleados.length; i++) {
            System.out.println("Ingrese los datos del empleado " + (i + 1) + ":");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();

            empleados[i] = new Empleado(nombre, telefono);
        }


        System.out.println("\nDatos de los empleados:");
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }


        System.out.println("Número total de empleados: " + Empleado.getNumeroEmpleados());


        scanner.close();
    }

}

