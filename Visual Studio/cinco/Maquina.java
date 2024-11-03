package cinco;

import java.util.Scanner;

class GrupoMonedas {
    private int[] monedas;

    public GrupoMonedas() {
        monedas = new int[8];
    }

    public void setMonedas(int[] monedas) {
        this.monedas = monedas;
    }

    public int[] getMonedas() {
        return monedas;
    }
}

class MaquinaDeBebidas {
    private GrupoMonedas stockMonedas;

    public MaquinaDeBebidas() {
        stockMonedas = new GrupoMonedas();
    }

    public void agregarMonedas(int[] monedas) {
        for (int i = 0; i < monedas.length; i++) {
            stockMonedas.getMonedas()[i] += monedas[i];
        }
    }

    public GrupoMonedas devolverMonedas(GrupoMonedas monedasIntroducidas, double precio) {
        int totalIntroducido = calcularTotal(monedasIntroducidas);
        int totalMaquina = calcularTotal(stockMonedas);

        if (totalIntroducido < precio * 100) {
            return null;
        }

        int cambio = (int) (totalIntroducido - precio * 100);
        GrupoMonedas monedasDevueltas = new GrupoMonedas();

        for (int i = 0; i < stockMonedas.getMonedas().length; i++) {
            int valorMoneda = (int) Math.pow(10, i / 2) * (i % 2 == 0 ? 1 : 2);
            while (cambio >= valorMoneda && stockMonedas.getMonedas()[i] > 0) {
                cambio -= valorMoneda;
                monedasDevueltas.getMonedas()[i]++;
                stockMonedas.getMonedas()[i]--;
            }
        }

        return monedasDevueltas;
    }

    private int calcularTotal(GrupoMonedas grupo) {
        int total = 0;
        int[] monedas = grupo.getMonedas();
        int[] valores = {1, 2, 5, 10, 20, 50, 100, 200};

        for (int i = 0; i < monedas.length; i++) {
            total += monedas[i] * valores[i];
        }

        return total;
    }
}

public class Maquina {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MaquinaDeBebidas maquina = new MaquinaDeBebidas();

        System.out.println("Elija una bebida:");
        System.out.println("1. Bebida 1 (1.50 euros)");
        System.out.println("2. Bebida 2 (2.00 euros)");
        System.out.println("3. Bebida 3 (2.50 euros)");
        System.out.println("4. Bebida 4 (1.00 euro)");
        System.out.println("5. Bebida 5 (1.80 euros)");

        int eleccion = scanner.nextInt();
        double precio = 0;

        switch (eleccion) {
            case 1:
                precio = 1.50;
                break;
            case 2:
                precio = 2.00;
                break;
            case 3:
                precio = 2.50;
                break;
            case 4:
                precio = 1.00;
                break;
            case 5:
                precio = 1.80;
                break;
            default:
                System.out.println("Opción no válida");
                return;
        }

        int[] monedasIntroducidas = new int[8];
        System.out.println("Ingrese el número de monedas de 1, 2, 5, 10, 20, 50 céntimos y 1, 2 euros:");

        for (int i = 0; i < monedasIntroducidas.length; i++) {
            monedasIntroducidas[i] = scanner.nextInt();
        }

        GrupoMonedas grupoMonedas = new GrupoMonedas();
        grupoMonedas.setMonedas(monedasIntroducidas);
        GrupoMonedas monedasDevueltas = maquina.devolverMonedas(grupoMonedas, precio);

        if (monedasDevueltas != null) {
            System.out.println("Monedas devueltas:");
            for (int i = 0; i < monedasDevueltas.getMonedas().length; i++) {
                System.out.println(monedasDevueltas.getMonedas()[i]);
            }
        } else {
            System.out.println("No se puede devolver el cambio.");
        }
    }
}
