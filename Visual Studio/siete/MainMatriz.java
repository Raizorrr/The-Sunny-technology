package siete;

import java.util.Random;

class Matriz {
    private int[][] matriz;
    private int tamaño;

    public Matriz(int tamaño) {
        this.tamaño = tamaño;
        matriz = new int[tamaño][tamaño];
    }

    public void asignarDatos() {
        Random rand = new Random();
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                matriz[i][j] = rand.nextInt(9) + 1;
            }
        }
    }

    public void suma(Matriz m) {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                this.matriz[i][j] += m.matriz[i][j];
            }
        }
    }

    public void producto(Matriz m) {
        int[][] resultado = new int[tamaño][tamaño];
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                resultado[i][j] = 0;
                for (int k = 0; k < tamaño; k++) {
                    resultado[i][j] += this.matriz[i][k] * m.matriz[k][j];
                }
            }
        }
        this.matriz = resultado;
    }

    public Matriz traspuesta() {
        Matriz transpuesta = new Matriz(tamaño);
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                transpuesta.matriz[j][i] = this.matriz[i][j];
            }
        }
        return transpuesta;
    }

    public void mostrar() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class MainMatriz {
    public static void main(String[] args) {
        Matriz matriz1 = new Matriz(3);
        Matriz matriz2 = new Matriz(3);

        matriz1.asignarDatos();
        matriz2.asignarDatos();

        System.out.println("Contenido de la matriz 1:");
        matriz1.mostrar();
        
        System.out.println("Contenido de la matriz 2:");
        matriz2.mostrar();

        matriz1.producto(matriz2);
        System.out.println("Resultado de multiplicar la matriz 1 por la matriz 2:");
        matriz1.mostrar();

        matriz1 = new Matriz(3); // Reiniciar matriz1 para la suma
        matriz1.asignarDatos(); // Rellenar matriz1 nuevamente para la suma
        matriz1.suma(matriz2);
        System.out.println("Resultado de sumar la matriz 1 con la matriz 2:");
        matriz1.mostrar();

        Matriz traspuesta = matriz1.traspuesta();
        System.out.println("Traspuesta de la matriz 1:");
        traspuesta.mostrar();
    }
}
