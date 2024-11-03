
public class Empleado {
    private String nombre;
    private String telefono;
    private static int numeroEmpleados = 0;

 
    public Empleado(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
        numeroEmpleados++; 
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public static int getNumeroEmpleados() {
        return numeroEmpleados;
    }


    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Teléfono: " + telefono;
    }
}
