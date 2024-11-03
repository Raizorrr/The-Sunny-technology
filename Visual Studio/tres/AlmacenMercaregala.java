package tres;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

interface Producto {
    double calcularPrecio();
    String getNombre();
}

class Carne implements Producto {
    private String nombre;
    private double peso;
    private double precioKilo;
    private String origen;

    public Carne(String nombre, double peso, double precioKilo, String origen) {
        this.nombre = nombre;
        this.peso = peso;
        this.precioKilo = precioKilo;
        this.origen = origen;
    }

    public double calcularPrecio() {
        return peso * precioKilo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getOrigen() {
        return origen;
    }
}

class CarneFresca extends Carne {
    private int diasCaducidad;

    public CarneFresca(String nombre, double peso, double precioKilo, String origen, int diasCaducidad) {
        super(nombre, peso, precioKilo, origen);
        this.diasCaducidad = diasCaducidad;
    }

    public int getDiasCaducidad() {
        return diasCaducidad;
    }
}

class CarneCongelada extends Carne {
    private String empresaDistribuidora;

    public CarneCongelada(String nombre, double peso, double precioKilo, String origen, String empresaDistribuidora) {
        super(nombre, peso, precioKilo, origen);
        this.empresaDistribuidora = empresaDistribuidora;
    }

    public String getEmpresaDistribuidora() {
        return empresaDistribuidora;
    }
}

class CarneCongeladaPiezaEntera extends CarneCongelada {
    private boolean enBandeja;

    public CarneCongeladaPiezaEntera(String nombre, double peso, double precioKilo, String origen, String empresaDistribuidora, boolean enBandeja) {
        super(nombre, peso, precioKilo, origen, empresaDistribuidora);
        this.enBandeja = enBandeja;
    }

    public boolean isEnBandeja() {
        return enBandeja;
    }
}

class CarneCongeladaPiezaTroceada extends CarneCongelada {
    private int numPiezas;

    public CarneCongeladaPiezaTroceada(String nombre, double peso, double precioKilo, String origen, String empresaDistribuidora, int numPiezas) {
        super(nombre, peso, precioKilo, origen, empresaDistribuidora);
        this.numPiezas = numPiezas;
    }

    public int getNumPiezas() {
        return numPiezas;
    }
}

class Pescado implements Producto {
    private String nombre;
    private double peso;
    private double precioKilo;
    private String tipoPescado;

    public Pescado(String nombre, double peso, double precioKilo, String tipoPescado) {
        this.nombre = nombre;
        this.peso = peso;
        this.precioKilo = precioKilo;
        this.tipoPescado = tipoPescado;
    }

    public double calcularPrecio() {
        return peso * precioKilo;
    }

    public String getNombre() {
        return nombre;
    }
}

public class AlmacenMercaregala {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void listarProductos() {
        for (Producto producto : productos) {
            System.out.println("Nombre: " + producto.getNombre() + ", Precio: " + producto.calcularPrecio());
        }
    }

    public void listarProductosEnPeligro() {
        for (Producto producto : productos) {
            if (producto instanceof CarneFresca) {
                CarneFresca carneFresca = (CarneFresca) producto;
                if (carneFresca.getDiasCaducidad() < 10) {
                    System.out.println("Nombre: " + carneFresca.getNombre() + ", Días de caducidad: " + carneFresca.getDiasCaducidad());
                }
            }
        }
    }

    public double calcularPrecioMedio() {
        double sumaPrecios = 0;
        for (Producto producto : productos) {
            sumaPrecios += producto.calcularPrecio();
        }
        return productos.size() > 0 ? sumaPrecios / productos.size() : 0;
    }

    public void eliminarBandejas() {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto instanceof CarneCongeladaPiezaEntera) {
                CarneCongeladaPiezaEntera carnePiezaEntera = (CarneCongeladaPiezaEntera) producto;
                if (carnePiezaEntera.isEnBandeja()) {
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) {
        AlmacenMercaregala almacen = new AlmacenMercaregala();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Añadir producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Productos en peligro");
            System.out.println("4. Calculo precio medio");
            System.out.println("5. Eliminar bandejas");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el tipo de producto (1 para Carne Fresca, 2 para Carne Congelada, 3 para Pescado): ");
                    int tipoProducto = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer

                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Peso del producto: ");
                    double peso = scanner.nextDouble();
                    System.out.print("Precio por kilo: ");
                    double precioKilo = scanner.nextDouble();

                    if (tipoProducto == 1) {
                        System.out.print("Origen (vacuno o avícola): ");
                        String origen = scanner.next();
                        System.out.print("Días de caducidad: ");
                        int diasCaducidad = scanner.nextInt();
                        almacen.agregarProducto(new CarneFresca(nombre, peso, precioKilo, origen, diasCaducidad));
                    } else if (tipoProducto == 2) {
                        System.out.print("Origen (vacuno o avícola): ");
                        String origen = scanner.next();
                        System.out.print("Empresa distribuidora: ");
                        scanner.nextLine();
                        String empresaDistribuidora = scanner.nextLine();
                        System.out.print("¿Es pieza entera? (true para sí, false para no): ");
                        boolean piezaEntera = scanner.nextBoolean();
                        if (piezaEntera) {
                            System.out.print("¿Viene en bandeja? (true para sí, false para no): ");
                            boolean enBandeja = scanner.nextBoolean();
                            almacen.agregarProducto(new CarneCongeladaPiezaEntera(nombre, peso, precioKilo, origen, empresaDistribuidora, enBandeja));
                        } else {
                            System.out.print("Número medio de piezas: ");
                            int numPiezas = scanner.nextInt();
                            almacen.agregarProducto(new CarneCongeladaPiezaTroceada(nombre, peso, precioKilo, origen, empresaDistribuidora, numPiezas));
                        }
                    } else if (tipoProducto == 3) {
                        System.out.print("Tipo de pescado: ");
                        scanner.nextLine();
                        String tipoPescado = scanner.nextLine();
                        almacen.agregarProducto(new Pescado(nombre, peso, precioKilo, tipoPescado));
                    } else {
                        System.out.println("Tipo de producto no válido.");
                    }
                    break;

                case 2:
                    almacen.listarProductos();
                    break;

                case 3:
                    almacen.listarProductosEnPeligro();
                    break;

                case 4:
                    System.out.println("Precio medio de los productos: " + almacen.calcularPrecioMedio());
                    break;

                case 5:
                    almacen.eliminarBandejas();
                    System.out.println("Carnes congeladas en bandejas eliminadas.");
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}
