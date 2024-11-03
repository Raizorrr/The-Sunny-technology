package ocho;

class Vestido {
    private String marca;
    private String modelo;
    private double precio;

    public Vestido(String marca, String modelo, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void rebajar(double porcentaje) {
        precio -= precio * (porcentaje / 100);
    }

    @Override
    public String toString() {
        return "Vestido{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                '}';
    }
}

class Tienda {
    private Vestido[] vestidos;
    private int contador;

    public Tienda() {
        vestidos = new Vestido[10];
        contador = 0;
    }

    public void añadirVestido(Vestido vestido) {
        if (contador < 10) {
            vestidos[contador] = vestido;
            contador++;
        }
    }

    public Vestido[] getVestidos() {
        return vestidos;
    }

    public double calcularGanancia() {
        double ganancia = 0;
        for (int i = 0; i < contador; i++) {
            ganancia += vestidos[i].getPrecio();
        }
        return ganancia;
    }
}

public class vestidos {
    public static void main(String[] args) {
        Tienda tienda = new Tienda();

        Vestido vestido1 = new Vestido("Marca A", "Modelo 1", 50);
        Vestido vestido2 = new Vestido("Marca B", "Modelo 2", 75);
        Vestido vestido3 = new Vestido("Marca C", "Modelo 3", 100);
        Vestido vestido4 = new Vestido("Marca D", "Modelo 4", 120);
        Vestido vestido5 = new Vestido("Marca E", "Modelo 5", 90);

        tienda.añadirVestido(vestido1);
        tienda.añadirVestido(vestido2);
        tienda.añadirVestido(vestido3);
        tienda.añadirVestido(vestido4);
        tienda.añadirVestido(vestido5);

        System.out.println("Vestidos en la tienda:");
        for (Vestido vestido : tienda.getVestidos()) {
            if (vestido != null) {
                System.out.println(vestido);
            }
        }

        double gananciaSinRebaja = tienda.calcularGanancia();
        System.out.println("Ganancia total sin rebaja: " + gananciaSinRebaja);

        double porcentajeRebaja = 20; 
        for (Vestido vestido : tienda.getVestidos()) {
            if (vestido != null) {
                vestido.rebajar(porcentajeRebaja);
            }
        }

        double gananciaConRebaja = tienda.calcularGanancia();
        System.out.println("Ganancia total con rebaja: " + gananciaConRebaja);

        double diferencia = gananciaSinRebaja - gananciaConRebaja;
        System.out.println("Diferencia de ganancia por la rebaja: " + diferencia);
    }
}
