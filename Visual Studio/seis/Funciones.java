package seis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Funcion {
    String nombre;
        double precio;
                private boolean[] butacas; // 60 butacas numeradas de 1 a 60
                private int butacasVendidas;
            
                public Funcion(String nombre, double precio) {
                    this.nombre = nombre;
                    this.precio = precio;
                    this.butacas = new boolean[60];
                    this.butacasVendidas = 0;
                }
            
                public String getNombre() {
                    return nombre;
                }
            
                public double getPrecio() {
                    return precio;
                }
            
                public boolean venderEntrada(int numeroButaca) {
                    if (numeroButaca < 1 || numeroButaca > 60 || butacas[numeroButaca - 1]) {
                        return false; // Butaca no válida o ya vendida
                    }
                    butacas[numeroButaca - 1] = true;
                    butacasVendidas++;
                    return true;
                }
            
                public int contarButacasDisponibles() {
                    return 60 - butacasVendidas;
                }
            
                public double recaudacion() {
                    return butacasVendidas * precio;
                }
            
                public boolean anularEntrada(int numeroButaca) {
                    if (numeroButaca < 1 || numeroButaca > 60 || !butacas[numeroButaca - 1]) {
                        return false; // Butaca no válida o no vendida
                    }
                    butacas[numeroButaca - 1] = false;
                    butacasVendidas--;
                    return true;
                }
            
                public List<Integer> getButacasVendidas() {
                    List<Integer> vendidas = new ArrayList<>();
                    for (int i = 0; i < butacas.length; i++) {
                        if (butacas[i]) {
                            vendidas.add(i + 1);
                        }
                    }
                    return vendidas;
                }
            }
            
            class Teatro {
                private String nombre;
                private HashMap<Integer, Funcion> sesiones; // Clave: sesión (1-4)
            
                public Teatro(String nombre, String direccion) {
                    this.nombre = nombre;
                    this.sesiones = new HashMap<>();
                    for (int i = 1; i <= 4; i++) {
                        sesiones.put(i, new Funcion("Funcion " + i, 10 + (i * 5))); // Precios: 15, 20, 25, 30
                    }
                }
            
                public String getNombre() {
                    return nombre;
                }
            
                public void setNombre(String nombre) {
                    this.nombre = nombre;
                }
            
                public void setNombreFuncion(int sesion, String nombre) {
                    if (sesiones.containsKey(sesion)) {
                        sesiones.get(sesion).nombre = nombre;
                }
            }
        
            public void setPrecioFuncion(int sesion, double precio) {
                if (sesiones.containsKey(sesion)) {
                    sesiones.get(sesion).precio = precio;
        }
    }

    public void consultarFunciones() {
        for (int i = 1; i <= 4; i++) {
            Funcion funcion = sesiones.get(i);
            System.out.println("Sesión " + i + ": " + funcion.getNombre() + " - Precio: " + funcion.getPrecio());
        }
    }

    public boolean venderEntrada(int sesion, int numeroButaca) {
        if (sesiones.containsKey(sesion)) {
            return sesiones.get(sesion).venderEntrada(numeroButaca);
        }
        return false;
    }

    public boolean venderEntradaAleatoria(int sesion) {
        if (sesiones.containsKey(sesion)) {
            Funcion funcion = sesiones.get(sesion);
            if (funcion.contarButacasDisponibles() == 0) {
                return false;
            }
            Random random = new Random();
            int butaca;
            do {
                butaca = random.nextInt(60) + 1;
            } while (!funcion.venderEntrada(butaca));
            return true;
        }
        return false;
    }

    public boolean anularEntrada(int sesion, int numeroButaca) {
        if (sesiones.containsKey(sesion)) {
            sesiones.get(sesion).anularEntrada(numeroButaca);
        }
                return false;
    }

    public double consultarRecaudacionPorFuncion(int sesion) {
        if (sesiones.containsKey(sesion)) {
            return sesiones.get(sesion).recaudacion();
        }
        return 0.0;
    }

    public double calcularRecaudacionPorDia() {
        double total = 0.0;
        for (Funcion funcion : sesiones.values()) {
            total += funcion.recaudacion();
        }
        return total;
    }

    public int contarButacasDisponiblesPorFuncion(int sesion) {
        if (sesiones.containsKey(sesion)) {
            return sesiones.get(sesion).contarButacasDisponibles();
        }
        return 0;
    }
}

public class Funciones {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Teatro teatro = new Teatro("Teatro Funchi", "Calle Funchi, 123");

            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Consultar funciones");
                System.out.println("2. Vender entrada para una función");
                System.out.println("3. Vender entrada aleatoria para una función");
                System.out.println("4. Anular entrada");
                System.out.println("5. Consultar recaudación por función");
                System.out.println("6. Calcular recaudación total del día");
                System.out.println("0. Salir");

                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        teatro.consultarFunciones();
                        break;
                    case 2:
                        System.out.println("Ingrese número de sesión (1-4): ");
                        int sesion = scanner.nextInt();
                        System.out.println("Ingrese número de butaca (1-60): ");
                        int butaca = scanner.nextInt();
                        if (teatro.venderEntrada(sesion, butaca)) {
                            System.out.println("Entrada vendida.");
                        } else {
                            System.out.println("No se pudo vender la entrada.");
                        }
                        break;
                    case 3:
                        System.out.println("Ingrese número de sesión (1-4): ");
                        int sesionAleatoria = scanner.nextInt();
                        if (teatro.venderEntradaAleatoria(sesionAleatoria)) {
                            System.out.println("Entrada aleatoria vendida.");
                        } else {
                            System.out.println("No hay butacas disponibles.");
                        }
                        break;
                    case 4:
                        System.out.println("Ingrese número de sesión (1-4): ");
                        int sesionAnular = scanner.nextInt();
                        System.out.println("Ingrese número de butaca (1-60): ");
                        int butacaAnular = scanner.nextInt();
                        if (teatro.anularEntrada(sesionAnular, butacaAnular)) {
                            System.out.println("Entrada anulada.");
                        } else {
                            System.out.println("No se pudo anular la entrada.");
                        }
                        break;
                    case 5:
                        System.out.println("Ingrese número de sesión (1-4): ");
                        int sesionRecaudacion = scanner.nextInt();
                        double recaudacion = teatro.consultarRecaudacionPorFuncion(sesionRecaudacion);
                        System.out.println("Recaudación de la función " + sesionRecaudacion + ": " + recaudacion);
                        break;
                    case 6:
                        double recaudacionDia = teatro.calcularRecaudacionPorDia();
                        System.out.println("Recaudación total del día: " + recaudacionDia);
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }
    }
}
