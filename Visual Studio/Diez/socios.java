package Diez;

import java.util.Scanner;

class SocioGimnasio {
    private String nombre;
    private int edad;
    private String DNI;
    private char sexo;
    private double peso;
    private double altura;

    public static final char SEXO_DEFAULT = 'M';
    public static final int PESO_BAJO = -1;
    public static final int PESO_IDEAL = 0;
    public static final int SOBREPESO = 1;

    public SocioGimnasio() {
        this.nombre = "";
        this.edad = 0;
        this.DNI = generaDNI();
        this.sexo = SEXO_DEFAULT;
        this.peso = 0;
        this.altura = 0;
    }

    public SocioGimnasio(String nombre, int edad, char sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = comprobarSexo(sexo);
        this.DNI = generaDNI();
        this.peso = 0;
        this.altura = 0;
    }

    public SocioGimnasio(String nombre, int edad, char sexo, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = comprobarSexo(sexo);
        this.DNI = generaDNI();
        this.peso = peso;
        this.altura = altura;
    }

    public int calcularIMC() {
        double imc = peso / (altura * altura);
        if (imc < 18.5) {
            return PESO_BAJO;
        } else if (imc >= 18.5 && imc < 24.9) {
            return PESO_IDEAL;
        } else {
            return SOBREPESO;
        }
    }

    public boolean esMayorDeEdad() {
        return edad >= 18;
    }

    public char comprobarSexo(char sexo) {
        if (sexo != 'H' && sexo != 'M') {
            return SEXO_DEFAULT;
        }
        return sexo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
               "Edad: " + edad + "\n" +
               "DNI: " + DNI + "\n" +
               "Sexo: " + sexo + "\n" +
               "Peso: " + peso + "\n" +
               "Altura: " + altura;
    }

    private String generaDNI() {
        StringBuilder dni = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            dni.append((int) (Math.random() * 10));
        }
        char letraDNI = calcularLetraDNI(Integer.parseInt(dni.toString()));
        return dni.toString() + letraDNI;
    }

    private char calcularLetraDNI(int dniNumerico) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKET";
        return letras.charAt(dniNumerico % 23);
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}

public class socios {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SocioGimnasio[] socios = new SocioGimnasio[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("Ingrese los datos del socio " + (i + 1) + ":");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = Integer.parseInt(scanner.nextLine());
            System.out.print("Sexo (H/M): ");
            char sexo = scanner.nextLine().charAt(0);
            System.out.print("Peso: ");
            double peso = Double.parseDouble(scanner.nextLine());
            System.out.print("Altura: ");
            double altura = Double.parseDouble(scanner.nextLine());

            socios[i] = new SocioGimnasio(nombre, edad, sexo, peso, altura);
        }

        for (SocioGimnasio socio : socios) {
            System.out.println("\nInformación del socio:");
            System.out.println(socio);
            int imc = socio.calcularIMC();
            if (imc == SocioGimnasio.PESO_BAJO) {
                System.out.println("El socio está por debajo de su peso ideal.");
            } else if (imc == SocioGimnasio.PESO_IDEAL) {
                System.out.println("El socio está en su peso ideal.");
            } else {
                System.out.println("El socio tiene sobrepeso.");
            }
            System.out.println("¿Es mayor de edad? " + socio.esMayorDeEdad());
        }

        scanner.close();
    }
}
