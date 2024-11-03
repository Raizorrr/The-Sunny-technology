package dos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Aeropuerto {
    private String nombre;
    private String ciudad;

    public Aeropuerto(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    @Override
    public String toString() {
        return "Aeropuerto: " + nombre + " en " + ciudad;
    }
}

class AeropuertoMilitar extends Aeropuerto {
    private int numeroDePistas;
    private List<String> avionesMilitares;

    public AeropuertoMilitar(String nombre, String ciudad, int numeroDePistas) {
        super(nombre, ciudad);
        this.numeroDePistas = numeroDePistas;
        this.avionesMilitares = new ArrayList<>();
    }

    public void agregarAvionMilitar(String avion) {
        avionesMilitares.add(avion);
    }

    @Override
    public String toString() {
        return super.toString() + " [Militar] - Número de Pistas: " + numeroDePistas + ", Aviones Militares: " + avionesMilitares;
    }
}

class AeropuertoComercial extends Aeropuerto {
    private int numeroDeTerminales;
    private List<String> aerolineas;

    public AeropuertoComercial(String nombre, String ciudad, int numeroDeTerminales) {
        super(nombre, ciudad);
        this.numeroDeTerminales = numeroDeTerminales;
        this.aerolineas = new ArrayList<>();
    }

    public void agregarAerolinea(String aerolinea) {
        aerolineas.add(aerolinea);
    }

    @Override
    public String toString() {
        return super.toString() + " [Comercial] - Número de Terminales: " + numeroDeTerminales + ", Aerolíneas: " + aerolineas;
    }
}

public class GestionAeropuertos {
    private List<Aeropuerto> aeropuertos;

    public GestionAeropuertos() {
        aeropuertos = new ArrayList<>();
    }

    public void agregarAeropuerto(Aeropuerto aeropuerto) {
        aeropuertos.add(aeropuerto);
    }

    public void listarAeropuertos() {
        if (aeropuertos.isEmpty()) {
            System.out.println("No hay aeropuertos registrados.");
        } else {
            for (Aeropuerto aeropuerto : aeropuertos) {
                System.out.println(aeropuerto);
            }
        }
    }

    public void ingresarDatos() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("\nIngrese el tipo de aeropuerto (1 = Militar, 2 = Comercial): ");
            int tipo = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            System.out.print("Ingrese el nombre del aeropuerto: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese la ciudad del aeropuerto: ");
            String ciudad = scanner.nextLine();

            if (tipo == 1) {
                System.out.print("Ingrese el número de pistas para el aeropuerto militar: ");
                int numeroDePistas = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                AeropuertoMilitar militar = new AeropuertoMilitar(nombre, ciudad, numeroDePistas);

                System.out.print("¿Cuántos aviones militares desea registrar? ");
                int numAviones = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                for (int j = 0; j < numAviones; j++) {
                    System.out.print("Ingrese el nombre del avión militar " + (j + 1) + ": ");
                    String avion = scanner.nextLine();
                    militar.agregarAvionMilitar(avion);
                }

                agregarAeropuerto(militar);
            } else if (tipo == 2) {
                System.out.print("Ingrese el número de terminales para el aeropuerto comercial: ");
                int numeroDeTerminales = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                AeropuertoComercial comercial = new AeropuertoComercial(nombre, ciudad, numeroDeTerminales);

                System.out.print("¿Cuántas aerolíneas desea registrar? ");
                int numAerolineas = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                for (int j = 0; j < numAerolineas; j++) {
                    System.out.print("Ingrese el nombre de la aerolínea " + (j + 1) + ": ");
                    String aerolinea = scanner.nextLine();
                    comercial.agregarAerolinea(aerolinea);
                }

                agregarAeropuerto(comercial);
            } else {
                System.out.println("Tipo de aeropuerto no válido. Intente de nuevo.");
                i--; // Reintentar el mismo aeropuerto
            }
        }
    }

    public static void main(String[] args) {
        GestionAeropuertos gestion = new GestionAeropuertos();
        gestion.ingresarDatos();
        System.out.println("\n--- Lista de Aeropuertos ---");
        gestion.listarAeropuertos();
    }
}
